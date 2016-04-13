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
 *   ExperimentsDetailPage.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.wui.ui.experiments;

import cz.zcu.kiv.eegdatabase.data.pojo.DataFile;
import cz.zcu.kiv.eegdatabase.data.pojo.Experiment;
import cz.zcu.kiv.eegdatabase.data.pojo.ExperimentLicence;
import cz.zcu.kiv.eegdatabase.data.pojo.Person;
import cz.zcu.kiv.eegdatabase.wui.app.EEGDataBaseApplication;
import cz.zcu.kiv.eegdatabase.wui.app.session.EEGDataBaseSession;
import cz.zcu.kiv.eegdatabase.wui.components.menu.button.ButtonPageMenu;
import cz.zcu.kiv.eegdatabase.wui.components.page.BasePage;
import cz.zcu.kiv.eegdatabase.wui.components.page.MenuPage;
import cz.zcu.kiv.eegdatabase.wui.components.table.TimestampLabel;
import cz.zcu.kiv.eegdatabase.wui.components.table.ViewLinkPanel;
import cz.zcu.kiv.eegdatabase.wui.components.utils.PageParametersUtils;
import cz.zcu.kiv.eegdatabase.wui.components.utils.ResourceUtils;
import cz.zcu.kiv.eegdatabase.wui.components.utils.StringUtils;
import cz.zcu.kiv.eegdatabase.wui.core.Gender;
import cz.zcu.kiv.eegdatabase.wui.core.experimentLicense.ExperimentLicenseFacade;
import cz.zcu.kiv.eegdatabase.wui.core.experiments.ExperimentsFacade;
import cz.zcu.kiv.eegdatabase.wui.core.file.FileFacade;
import cz.zcu.kiv.eegdatabase.wui.core.license.LicenseFacade;
import cz.zcu.kiv.eegdatabase.wui.core.security.SecurityFacade;
import cz.zcu.kiv.eegdatabase.wui.ui.data.AddDataFilePage;
import cz.zcu.kiv.eegdatabase.wui.ui.data.DataFileDetailPage;
import cz.zcu.kiv.eegdatabase.wui.ui.experiments.components.ExperimentBuyDownloadLinkPanel;
import cz.zcu.kiv.eegdatabase.wui.ui.experiments.metadata.MetadataFormPage;
import cz.zcu.kiv.eegdatabase.wui.ui.experiments.metadata.ViewMetadataSectionPanel;
import cz.zcu.kiv.eegdatabase.wui.ui.licenses.LicenseDetailPage;
import cz.zcu.kiv.eegdatabase.wui.ui.people.PersonDetailPage;
import cz.zcu.kiv.eegdatabase.wui.ui.scenarios.ScenarioDetailPage;
import odml.core.Section;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxCallListener;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.EnumLabel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Page of detail on experiment. 
 * 
 * @author Jakub Rinkes
 * @author Jakub Krauz
 */
@AuthorizeInstantiation(value = { "ROLE_READER", "ROLE_USER", "ROLE_EXPERIMENTER", "ROLE_ADMIN" })
public class ExperimentsDetailPage extends MenuPage {

    private static final long serialVersionUID = 957980612639804114L;

    @SpringBean
    ExperimentsFacade facade;
    
    @SpringBean
    FileFacade fileFacade;

    @SpringBean
    SecurityFacade security;

    @SpringBean
    LicenseFacade licenseFacade;

    @SpringBean
    ExperimentLicenseFacade experimentLicenseFacade;

    public ExperimentsDetailPage(PageParameters parameters) {

        int experimentId = parseParameters(parameters);

        setupComponents(experimentId);
    }

    private void setupComponents(final int experimentId) {

        setPageTitle(ResourceUtils.getModel("pageTitle.experimentDetail"));

        add(new ButtonPageMenu("leftMenu", ExperimentsPageLeftMenu.values()));

        final Experiment experiment = facade.getExperimentForDetail(experimentId);

        add(new Label("experimentId", experiment.getExperimentId()+""));
        add(new Label("privateExperiment", experiment.isPrivateExperiment()+""));
        add(new Label("scenario.title", experiment.getScenario().getTitle()));

        add(new TimestampLabel("startTime", experiment.getStartTime(), StringUtils.DATE_TIME_FORMAT_PATTER));
        add(new TimestampLabel("endTime", experiment.getEndTime(), StringUtils.DATE_TIME_FORMAT_PATTER));
        
        Person personBySubjectPersonId = experiment.getPersonBySubjectPersonId();
        add(new TimestampLabel("dateOfBirth", personBySubjectPersonId != null ? personBySubjectPersonId.getDateOfBirth() : null, StringUtils.DATE_TIME_FORMAT_PATTER_ONLY_YEAR));
        add(new EnumLabel<Gender>("gender", (personBySubjectPersonId != null ? Gender.getGenderByShortcut(personBySubjectPersonId.getGender()) : null)));
        
        BookmarkablePageLink<Void> personLink = new BookmarkablePageLink<Void>("personLink", PersonDetailPage.class, PageParametersUtils.getDefaultPageParameters(personBySubjectPersonId != null ? personBySubjectPersonId.getPersonId() : -1));
        personLink.setVisibilityAllowed(personBySubjectPersonId != null && security.userCanViewPersonDetails(personBySubjectPersonId.getPersonId()));
        add(personLink);
        
        BookmarkablePageLink<Void> scenarioLink = new BookmarkablePageLink<Void>("scenarioLink", ScenarioDetailPage.class, PageParametersUtils.getDefaultPageParameters(experiment.getScenario().getScenarioId()));
        add(scenarioLink);
        
        boolean coexperiment = security.userIsOwnerOrCoexperimenter(experimentId) || security.isAdmin();
        BookmarkablePageLink<Void> addFileLink = new BookmarkablePageLink<Void>("addFileLink", AddDataFilePage.class, PageParametersUtils.getDefaultPageParameters(experimentId));
        BookmarkablePageLink<Void> editExpLink = new BookmarkablePageLink<Void>("editExpLink", ExperimentFormPage.class, PageParametersUtils.getDefaultPageParameters(experimentId));
        BookmarkablePageLink<Void> metadataLink = new BookmarkablePageLink<Void>("metadataLink", MetadataFormPage.class, PageParametersUtils.getDefaultPageParameters(experimentId));
        
        ExperimentBuyDownloadLinkPanel downloadExpLink = new ExperimentBuyDownloadLinkPanel("downloadExpLink", experiment, new Model<ExperimentLicence>());
        downloadExpLink.setVisibilityAllowed(experiment.getExperimentPackageConnections().isEmpty());
        // TODO add license choice to allow the "Add to cart" link, then delete the following line
        downloadExpLink.setVisible(EEGDataBaseSession.get().isExperimentPurchased(experiment.getExperimentId()));
        
        add(addFileLink.setVisibilityAllowed(coexperiment), 
            editExpLink.setVisibilityAllowed(coexperiment), 
            metadataLink.setVisibilityAllowed(coexperiment), 
            downloadExpLink);
        
        /* XXX #66 Java Heap Space Exception : working with big data file in memory.
            final ExperimentSignalViewCanvasPanel experimentViewPanel = new ExperimentSignalViewCanvasPanel("view", experiment);
         */

        //Removing experimenters section
        PropertyModel<List<Section>> model = new PropertyModel<List<Section>>(experiment.getElasticExperiment().getMetadata(), "sections");
        List<Section> list = model.getObject();
        Section toRemove = null;
        if (list != null) {
            for (Section s: list) {
                if (s.getName().equals("Experimentators")) {
                    toRemove = s;
                    break;
                }
            }
            list.remove(toRemove);
        }


        // PropertyListView<Section> metadata = new PropertyListView<Section>("sections", new PropertyModel<List<Section>>(experiment.getElasticExperiment().getMetadata(), "sections")) {
        PropertyListView<Section> metadata = new PropertyListView<Section>("sections", new ListModel<Section>(list)) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<Section> item) {

                item.add(new ViewMetadataSectionPanel("section", item.getModel()));


            }
        };
        
        metadata.setVisible(metadata.getViewSize() > 0);
        add(metadata);

        PropertyListView<ExperimentLicence> licenseList = new PropertyListView<ExperimentLicence>("licenseList", new ListModel<ExperimentLicence>(new ArrayList<ExperimentLicence>(experiment.getExperimentLicences()))) {
            
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<ExperimentLicence> item) {
                item.add(new Label("license.title"));
                item.add(new Label("price"));
                item.add(new Label("license.licenseType"));
                //item.add(new Label("license.attachmentFileName"));
                item.add(new ViewLinkPanel("detail", LicenseDetailPage.class, "license.licenseId", item.getModel(), ResourceUtils.getModel("link.detail")));
                item.add(new AjaxLink<Void>("deleteLink") {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onClick(AjaxRequestTarget target) {

                        experimentLicenseFacade.remove(experiment, item.getModelObject().getLicense());

                        setResponsePage(ExperimentsDetailPage.class, PageParametersUtils.getDefaultPageParameters(experimentId));
                    }

                    @Override
                    protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
                        super.updateAjaxAttributes(attributes);

                        AjaxCallListener ajaxCallListener = new AjaxCallListener();
                        ajaxCallListener.onPrecondition("return confirm('" + ResourceUtils.getString("text.delete.license", item.getModelObject().getLicense().getTitle()) + "');");
                        attributes.getAjaxCallListeners().add(ajaxCallListener);
                    }

                    @Override
                    public boolean isVisible() {
                        boolean isOwner = experiment.getPersonByOwnerId().getPersonId() == EEGDataBaseSession.get().getLoggedUser().getPersonId();
                        boolean isAdmin = security.isAdmin();
                        boolean isGroupAdmin = security.userIsAdminInGroup(experiment.getResearchGroup().getResearchGroupId());
                        return isAdmin || isOwner || isGroupAdmin;
                    }

                });
            }
        };
        
        add(licenseList);

        PropertyListView<DataFile> files = new PropertyListView<DataFile>("files", new ListModel<DataFile>(new ArrayList<DataFile>(experiment.getDataFiles()))) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<DataFile> item) {
                
                item.add(new Label("filename"));
                item.add(new Label("description"));
                item.add(new ViewLinkPanel("detail", DataFileDetailPage.class, "dataFileId", item.getModel(), ResourceUtils.getModel("link.detail")));
                item.add(new AjaxLink<Void>("deleteLink") {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onClick(AjaxRequestTarget target) {

                        fileFacade.delete(item.getModelObject());

                        setResponsePage(ExperimentsDetailPage.class, PageParametersUtils.getDefaultPageParameters(experimentId));
                    }

                    @Override
                    protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
                        super.updateAjaxAttributes(attributes);

                        AjaxCallListener ajaxCallListener = new AjaxCallListener();
                        ajaxCallListener.onPrecondition("return confirm('" + ResourceUtils.getString("text.delete.datafile", item.getModelObject().getFilename()) + "');");
                        attributes.getAjaxCallListeners().add(ajaxCallListener);
                    }
                    
                    @Override
                    public boolean isVisible() {
                        boolean isOwner = experiment.getPersonByOwnerId().getPersonId() == EEGDataBaseSession.get().getLoggedUser().getPersonId();
                        boolean isAdmin = security.isAdmin();
                        boolean isGroupAdmin = security.userIsAdminInGroup(experiment.getResearchGroup().getResearchGroupId());
                        return isAdmin || isOwner || isGroupAdmin;
                    }

                });

            }
        };

        add(files);

        final WebMarkupContainer container = new WebMarkupContainer("container");
        container.setOutputMarkupId(true);
        container.setOutputMarkupPlaceholderTag(true);
        container.setVisibilityAllowed(true);
        
        /* XXX #66 Java Heap Space Exception : working with big data file in memory.
            container.add(experimentViewPanel);
         */
        
        add(container);
    }

    private int parseParameters(PageParameters parameters) {

        StringValue value = parameters.get(BasePage.DEFAULT_PARAM_ID);
        if (value.isNull() || value.isEmpty())
            throw new RestartResponseAtInterceptPageException(EEGDataBaseApplication.get().getHomePage());
        return value.toInt();
    }

}
