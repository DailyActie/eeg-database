/*******************************************************************************
 * This file is part of the EEG-database project
 * 
 *   ==========================================
 *  
 *   Copyright (C) 2013 by University of West Bohemia (http://www.zcu.cz/en/)
 *  
 *  ***********************************************************************************************************************
 *  
 *   Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 *   the License. You may obtain a copy of the License at
 *  
 *       http://www.apache.org/licenses/LICENSE-2.0
 *  
 *   Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 *   an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 *   specific language governing permissions and limitations under the License.
 *  
 *  ***********************************************************************************************************************
 *  
 *   ExperimentDownloadProvider.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.wui.core.experiments;

import cz.zcu.kiv.eegdatabase.data.pojo.*;
import cz.zcu.kiv.eegdatabase.logic.controller.experiment.MetadataCommand;
import cz.zcu.kiv.eegdatabase.logic.zip.ZipGenerator;
import cz.zcu.kiv.eegdatabase.wui.app.session.EEGDataBaseSession;
import cz.zcu.kiv.eegdatabase.wui.components.utils.FileUtils;
import cz.zcu.kiv.eegdatabase.wui.core.experimentpackage.ExperimentPackageService;
import cz.zcu.kiv.eegdatabase.wui.core.file.FileDTO;
import cz.zcu.kiv.eegdatabase.wui.core.file.FileService;
import cz.zcu.kiv.eegdatabase.wui.core.history.HistoryService;
import cz.zcu.kiv.eegdatabase.wui.core.license.LicenseService;
import cz.zcu.kiv.eegdatabase.wui.core.person.PersonService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Provider for experiment download. Provider get data from page and prepared file for download.
 * 
 * @author Jakub Rinkes
 * 
 */
public class ExperimentDownloadProvider {

    protected Log log = LogFactory.getLog(getClass());

    ExperimentsService service;

    PersonService personService;

    FileService fileService;

    HistoryService historyService;

    ExperimentPackageService packageService;

    ZipGenerator zipGenerator;

    LicenseService licenseService;

    @Required
    public void setService(ExperimentsService service) {
        this.service = service;
    }

    @Required
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @Required
    public void setZipGenerator(ZipGenerator zipGenerator) {
        this.zipGenerator = zipGenerator;
    }

    @Required
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Required
    public void setHistoryService(HistoryService historyService) {
        this.historyService = historyService;
    }

    @Required
    public void setPackageService(ExperimentPackageService packageService) {
        this.packageService = packageService;
    }

    @Required
    public void setLicenseService(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    /**
     * Method get data from web page, preprocess them for generator and generate zip file with content.
     * 
     * @param exp
     * @param mc
     * @param files
     * @param params
     * @return
     */
    @Transactional
    public FileDTO generate(Experiment exp, MetadataCommand mc, Collection<DataFile> files, Map<Integer, Set<FileMetadataParamVal>> params) {
        File file = null;
        try {
            Experiment experiment = service.getExperimentForDetail(exp.getExperimentId());
            String scenarioName = experiment.getScenario().getTitle();

            Set<DataFile> newFiles = prepareDataFilesWithParameters(files, params);

            // prepared history log
            createHistoryRecordAboutDownload(experiment, personService.getLoggedPerson());

            License license = licenseService.getLicenseForPurchasedExperiment(exp.getExperimentId(), EEGDataBaseSession.get().getLoggedUser().getPersonId());
            byte[] licenseFile = licenseService.getLicenseAttachmentContent(license.getLicenseId());
            file = zipGenerator.generate(experiment, mc, newFiles, licenseFile, license.getAttachmentFileName());

            FileDTO dto = new FileDTO();
            dto.setFile(file);

            if (scenarioName != null)
                dto.setFileName(scenarioName.replaceAll("\\s", "_") + ".zip");
            else
                dto.setFileName("Experiment_data_" + exp.getExperimentId() + ".zip");

            return dto;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            FileUtils.deleteQuietly(file);
            return null;

        } finally {
            FileUtils.deleteOnExitQuietly(file);
        }

    }

    @Transactional
    public FileDTO generatePackageFile(ExperimentPackage pckg, MetadataCommand mc, License license, List<Experiment> selectList,
                                       Person loggedUser, DownloadPackageManager manager) {

        ZipOutputStream zipOutputStream = null;
        FileOutputStream fileOutputStream = null;
        File tempZipFile = null;
        ZipInputStream in = null;
        File file = null;

        try {
            FileDTO dto = new FileDTO();
            dto.setFileName(pckg.getName().replaceAll("\\s", "_") + ".zip");

            // create temp zip file
            tempZipFile = File.createTempFile("experimentDownload_", ".zip");
            // open stream to temp zip file
            fileOutputStream = new FileOutputStream(tempZipFile);
            // prepare zip stream
            zipOutputStream = new ZipOutputStream(fileOutputStream);

            for (Experiment tmp : selectList) {


                Experiment exp = service.getExperimentForDetail(tmp.getExperimentId());
                String experimentDirPrefix = "";

                // create directory for each experiment.
                String scenarioName = exp.getScenario().getTitle();
                if (scenarioName != null) {
                    experimentDirPrefix = "Experiment_" + exp.getExperimentId() + "_" + scenarioName.replaceAll("\\s", "_") + "/";
                } else
                    experimentDirPrefix = "Experiment_data_" + exp.getExperimentId() + "/";
                // generate temp zip file with experiment
                byte[] licenseFile = licenseService.getLicenseAttachmentContent(license.getLicenseId());
                file = zipGenerator.generate(exp, mc, exp.getDataFiles(), licenseFile, license.getAttachmentFileName());
                in = new ZipInputStream(new FileInputStream(file));
                ZipEntry entryIn = null;

                // copy unziped experiment in package zip file.
                // NOTE: its easier solution copy content of one zip in anoter instead create directory structure via java.io.File.
                while ((entryIn = in.getNextEntry()) != null) {
                    zipOutputStream.putNextEntry(new ZipEntry(experimentDirPrefix + entryIn.getName()));
                    IOUtils.copyLarge(in, zipOutputStream);
                    zipOutputStream.closeEntry();
                }

                // mark all temp files for package for delete on exit
                FileUtils.deleteOnExitQuietly(file);
                IOUtils.closeQuietly(in);
                FileUtils.deleteQuietly(file);

                createHistoryRecordAboutDownload(exp, loggedUser);
                synchronized (this) {
                    manager.setNumberOfDownloadedExperiments(manager.getNumberOfDownloadedExperiments() + 1);
                }
            }

            dto.setFile(tempZipFile);
            
            // no problem detected - close all streams and mark file for delete on exit.
            // file is deleted after download action
            FileUtils.deleteOnExitQuietly(tempZipFile);
            IOUtils.closeQuietly(zipOutputStream);
            IOUtils.closeQuietly(fileOutputStream);

            return dto;
        } catch (Exception e) {

            log.error(e.getMessage(), e);

            // problem detected - close all streams, mark files for delete on exit and try delete them.
            IOUtils.closeQuietly(zipOutputStream);
            IOUtils.closeQuietly(fileOutputStream);
            FileUtils.deleteOnExitQuietly(tempZipFile);
            FileUtils.deleteOnExitQuietly(file);
            FileUtils.deleteQuietly(tempZipFile);
            FileUtils.deleteQuietly(file);

            return null;
        }
    }

    private void createHistoryRecordAboutDownload(Experiment experiment, Person user) {

        Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        History history = new History();
        log.debug("Setting downloading metadata");
        history.setExperiment(experiment);
        log.debug("Setting user");
        history.setPerson(user);
        log.debug("Setting time of download");
        history.setDateOfDownload(currentTimestamp);
        log.debug("Saving download history");
        historyService.create(history);
    }

    private Set<DataFile> prepareDataFilesWithParameters(Collection<DataFile> files, Map<Integer, Set<FileMetadataParamVal>> params) {

        Set<DataFile> newFiles = new HashSet<DataFile>();
        // prepared files for generator
        if (files != null || params != null) {
            // list selected files and prepare new files which we use for generated zip file.
            for (DataFile item : files) {
                // fill new file from selected file
                DataFile newItem = new DataFile();
                newItem.setDataFileId(item.getDataFileId());
                newItem.setExperiment(item.getExperiment());
                newItem.setFileContent(fileService.read(item.getDataFileId()).getFileContent());
                newItem.setFilename(item.getFilename());
                newItem.setMimetype(item.getMimetype());
                newItem.setDescription(item.getDescription());

                // create list of parameters which we use for generated zip file
                Set<FileMetadataParamVal> newVals = new HashSet<FileMetadataParamVal>();
                // get from map of selected parameters collection for actual file
                Set<FileMetadataParamVal> list = params.get(item.getDataFileId());
                if (list != null) {
                    for (FileMetadataParamVal paramVal : list) {
                        newVals.add(paramVal);
                    }
                }

                newItem.setFileMetadataParamVals(newVals);
                newFiles.add(newItem);
            }
        }
        return newFiles;
    }
}
