package cz.zcu.kiv.eegdatabase.wui.core.signalProcessing;

import cz.zcu.kiv.eegdatabase.data.dao.ExperimentDao;
import cz.zcu.kiv.eegdatabase.data.dao.PersonDao;
import cz.zcu.kiv.eegdatabase.data.dao.ServiceResultDao;
import cz.zcu.kiv.eegdatabase.data.pojo.DataFile;
import cz.zcu.kiv.eegdatabase.data.pojo.Experiment;
import cz.zcu.kiv.eegdatabase.data.pojo.Person;
import cz.zcu.kiv.eegdatabase.data.pojo.ServiceResult;
import cz.zcu.kiv.eegdatabase.logic.signal.ChannelInfo;
import cz.zcu.kiv.eegdatabase.logic.signal.DataTransformer;
import cz.zcu.kiv.eegdatabase.logic.util.SignalProcessingUtils;
import cz.zcu.kiv.eegdatabase.webservices.EDPClient.MethodParameters;
import cz.zcu.kiv.eegdatabase.webservices.EDPClient.ProcessService;
import cz.zcu.kiv.eegdatabase.webservices.EDPClient.SupportedFormat;
import cz.zcu.kiv.eegdatabase.wui.core.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: stebjan
 * Date: 11.12.13
 * Time: 14:11
 * To change this template use File | Settings | File Templates.
 */
public class SignalProcessingServiceImpl extends GenericServiceImpl<Experiment, Integer>
        implements SignalProcessingService {
    @Autowired
    private ExperimentDao experimentDao;
    @Autowired
    private DataTransformer transformer;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private ServiceResultDao resultDao;
    private ProcessService eegService;


    @Override
    public List<String> getAvailableMethods() {

        return eegService.getAvailableMethods();
    }

    @Transactional
    @Override
    public List<String> getSuitableHeaders(int experimentId) {
        Experiment e = experimentDao.read(experimentId);
        return SignalProcessingUtils.getHeaders(e);
    }

    @Override
    public List<MethodParameters> getMethodParameters(String methodName) {
        return eegService.getMethodParameters(SupportedFormat.KIV_FORMAT, methodName);
    }

    @Override
    @Transactional
    public List<ChannelInfo> getChannelInfo(int experimentId, String header) throws SQLException {
        readHeader(experimentId, header);
        return transformer.getChannelInfo();
    }

    @Override
    @Transactional
    public List<cz.zcu.kiv.eegdatabase.webservices.EDPClient.DataFile>
    getDataFiles(int experimentId, String header) {
        List<cz.zcu.kiv.eegdatabase.webservices.EDPClient.DataFile> dataFiles =
                new ArrayList<cz.zcu.kiv.eegdatabase.webservices.EDPClient.DataFile>();
        readHeader(experimentId, header);
        String dataFileName = transformer.getProperties().get("CI").get("DataFile");
        dataFileName = dataFileName.replace(" ", "_");
        cz.zcu.kiv.eegdatabase.webservices.EDPClient.DataFile headerFile = new
                cz.zcu.kiv.eegdatabase.webservices.EDPClient.DataFile();
        cz.zcu.kiv.eegdatabase.webservices.EDPClient.DataFile dataFile = new
                cz.zcu.kiv.eegdatabase.webservices.EDPClient.DataFile();
        Experiment e = experimentDao.read(experimentId);
        for (DataFile file : e.getDataFiles()) {
            if (file.getFilename().equals(header + ".vhdr")) {
                headerFile.setFileName(header + ".vhdr");
                try {
                    headerFile.setFile(file.getFileContent().getBytes(1, (int)file.getFileContent().length()));
                }catch (SQLException ex)
                {
                    Logger.getLogger(SignalProcessingServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    throw new RuntimeException(ex);

                }
                dataFiles.add(headerFile);
            }
            if (file.getFilename().equals(dataFileName)) {
                dataFile.setFileName(dataFileName);
                try {
                    dataFile.setFile(file.getFileContent().getBytes(1, (int)file.getFileContent().length()));
                }catch (SQLException ex)
                {
                    Logger.getLogger(SignalProcessingServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    throw new RuntimeException(ex);

                }
                dataFiles.add(dataFile);
            }
        }

        return dataFiles;
    }

    @Override
    public byte[] processService(List<cz.zcu.kiv.eegdatabase.webservices.EDPClient.DataFile> files,
                                 SupportedFormat format, String methodName, List<String> params) {
        return eegService.processData(files, format, methodName, params);
    }

    @Override
    public Person getLoggedPerson() {
        return personDao.getLoggedPerson();
    }

    @Override
    public void updateResult(ServiceResult result) {
        resultDao.update(result);
    }

    @Override
    public void createResult(ServiceResult result) {
        resultDao.create(result);
    }

    @Transactional
    private void readHeader(int experimentId, String header) {
        if (transformer.isHeaderRed()) {
            return;
        }
        Experiment e = experimentDao.read(experimentId);
        for (DataFile file : e.getDataFiles()) {
            if (file.getFilename().equals(header + ".vhdr")) {
                try {
                    transformer.readVhdr(file.getFileContent().getBytes(1, (int)file.getFileContent().length()));
                } catch (SQLException ex) {
                    Logger.getLogger(SignalProcessingServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    throw new RuntimeException(ex);
                }
                return;
            }
        }

    }

    public ProcessService getEegService() {
        return eegService;
    }

    public void setEegService(ProcessService eegService) {
        this.eegService = eegService;
    }

}