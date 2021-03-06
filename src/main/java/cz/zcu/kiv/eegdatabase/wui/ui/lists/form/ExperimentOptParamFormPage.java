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
 *   ExperimentOptParamFormPage.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.wui.ui.lists.form;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;

import cz.zcu.kiv.eegdatabase.data.pojo.ExperimentOptParamDef;
import cz.zcu.kiv.eegdatabase.data.pojo.ExperimentOptParamDefGroupRel;
import cz.zcu.kiv.eegdatabase.data.pojo.ExperimentOptParamDefGroupRelId;
import cz.zcu.kiv.eegdatabase.data.pojo.ResearchGroup;
import cz.zcu.kiv.eegdatabase.wui.app.session.EEGDataBaseSession;
import cz.zcu.kiv.eegdatabase.wui.components.menu.button.ButtonPageMenu;
import cz.zcu.kiv.eegdatabase.wui.components.page.MenuPage;
import cz.zcu.kiv.eegdatabase.wui.components.utils.PageParametersUtils;
import cz.zcu.kiv.eegdatabase.wui.components.utils.ResourceUtils;
import cz.zcu.kiv.eegdatabase.wui.core.CoreConstants;
import cz.zcu.kiv.eegdatabase.wui.core.experiments.param.ExperimentsOptParamFacade;
import cz.zcu.kiv.eegdatabase.wui.core.group.ResearchGroupFacade;
import cz.zcu.kiv.eegdatabase.wui.ui.lists.ListExperimentOptParamPage;
import cz.zcu.kiv.eegdatabase.wui.ui.lists.ListsLeftPageMenu;

/**
 * Page add / edit action of experiment opt parameters.
 * 
 * @author Jakub Rinkes
 *
 */
@AuthorizeInstantiation(value = { "ROLE_READER", "ROLE_USER", "ROLE_EXPERIMENTER", "ROLE_ADMIN" })
public class ExperimentOptParamFormPage extends MenuPage {

    private static final long serialVersionUID = -7254696725369039226L;

    protected Log log = LogFactory.getLog(getClass());

    @SpringBean
    ExperimentsOptParamFacade facade;

    @SpringBean
    ResearchGroupFacade groupFacade;

    public ExperimentOptParamFormPage(PageParameters parameters) {

        StringValue groupParam = parameters.get(PageParametersUtils.GROUP_PARAM);
        StringValue experimentParam = parameters.get(DEFAULT_PARAM_ID);

        if (groupParam.isNull() || groupParam.isEmpty())
            throw new RestartResponseAtInterceptPageException(ListExperimentOptParamPage.class);

        add(new ButtonPageMenu("leftMenu", ListsLeftPageMenu.values()));

        if (experimentParam.isNull() || experimentParam.isEmpty())
            setupAddComponents(groupParam.toInt());
        else
            setupEditComponents(groupParam.toInt(), experimentParam.toInt());
    }

    private void setupAddComponents(int researchGroupId) {
        setPageTitle(ResourceUtils.getModel("pageTitle.addExperimentParameters"));
        add(new Label("title", ResourceUtils.getModel("pageTitle.addExperimentParameters")));

        ExperimentOptParamDef newExperiment = new ExperimentOptParamDef();

        add(new ExperimentOptParamForm("form", new CompoundPropertyModel<ExperimentOptParamDef>(newExperiment), new Model<ResearchGroup>(getGroup(researchGroupId)), getFeedback(), facade));
    }

    private void setupEditComponents(int researchGroupId, int experimentId) {
        setPageTitle(ResourceUtils.getModel("pageTitle.editExperimentOptionalParameterDefinition"));
        add(new Label("title", ResourceUtils.getModel("pageTitle.editExperimentOptionalParameterDefinition")));

        add(new ExperimentOptParamForm("form", new CompoundPropertyModel<ExperimentOptParamDef>(facade.read(experimentId)),
                new Model<ResearchGroup>(getGroup(researchGroupId)), getFeedback(), facade));
    }

    private ResearchGroup getGroup(int researchGroupId) {
        ResearchGroup group;
        if (researchGroupId == CoreConstants.DEFAULT_ITEM_ID)
            group = new ResearchGroup(CoreConstants.DEFAULT_ITEM_ID, EEGDataBaseSession.get().getLoggedUser(), ResourceUtils.getString("label.defaultExperimentOptParamDef"), "-");
        else
            group = groupFacade.read(researchGroupId);
        return group;
    }

    private class ExperimentOptParamForm extends Form<ExperimentOptParamDef> {

        private static final long serialVersionUID = 1L;

        public ExperimentOptParamForm(String id, IModel<ExperimentOptParamDef> model, final Model<ResearchGroup> groupModel,
                final FeedbackPanel feedback, final ExperimentsOptParamFacade facade) {
            super(id, model);

            TextField<String> group = new TextField<String>("group", new PropertyModel<String>(groupModel.getObject(), "title"));
            group.setEnabled(false);
            group.setLabel(ResourceUtils.getModel("label.researchGroup"));

            TextField<String> paramName = new TextField<String>("paramName");
            paramName.setLabel(ResourceUtils.getModel("label.parameterName"));
            paramName.setRequired(true);

            TextField<String> paramDataType = new TextField<String>("paramDataType");
            paramDataType.setLabel(ResourceUtils.getModel("label.parameterDataType"));
            paramDataType.setRequired(true);

            AjaxButton submit = new AjaxButton("submit", ResourceUtils.getModel("button.save"), this) {

                private static final long serialVersionUID = 1L;

                @Override
                protected void onSubmit(AjaxRequestTarget target, Form<?> form) {

                    this.setEnabled(false);
                    target.add(this);
                    target.add(feedback);
                    
                    ExperimentOptParamDef experimentOptParam = ExperimentOptParamForm.this.getModelObject();

                    int experimentOptParamId = experimentOptParam.getExperimentOptParamDefId();
                    ResearchGroup group = groupModel.getObject();
                    int researchGroupId = group.getResearchGroupId();

                    if (experimentOptParamId > 0) {
                        // Editing one
                        log.debug("Editing existing experimentOptParamDef object.");
                        facade.update(experimentOptParam);
//                        if (facade.isDefault(experimentOptParamId)) {
//
//                            if (researchGroupId != CoreConstants.DEFAULT_ITEM_ID) {
//                                // new experimentOptParamDef
//                                ExperimentOptParamDef newExperimentOptParamDef = new ExperimentOptParamDef();
//                                newExperimentOptParamDef.setDefaultNumber(0);
//                                newExperimentOptParamDef.setParamName(experimentOptParam.getParamName());
//                                newExperimentOptParamDef.setParamDataType(experimentOptParam.getParamDataType());
//                                int newId = facade.create(newExperimentOptParamDef);
//                                ExperimentOptParamDefGroupRel rel = facade.getGroupRel(experimentOptParamId, researchGroupId);
//                                // delete old rel, create new one
//                                ExperimentOptParamDefGroupRelId newRelId = new ExperimentOptParamDefGroupRelId();
//                                ExperimentOptParamDefGroupRel newRel = new ExperimentOptParamDefGroupRel();
//                                newRelId.setExperimentOptParamDefId(newId);
//                                newRelId.setResearchGroupId(researchGroupId);
//                                newRel.setId(newRelId);
//                                newRel.setExperimentOptParamDef(newExperimentOptParamDef);
//                                newRel.setResearchGroup(group);
//                                facade.deleteGroupRel(rel);
//                                facade.createGroupRel(newRel);
//                            } else {
//                                if (!facade.hasGroupRel(experimentOptParamId) && facade.canDelete(experimentOptParamId)) {
//                                    facade.update(experimentOptParam);
//                                } else {
//                                    getFeedback().error(ResourceUtils.getString("text.itemInUse"));
//                                    this.setEnabled(true);
//                                    return;
//                                }
//                            }
//                        } else {

//                        }
                    } else {
                        // Creating new
                        if (researchGroupId == CoreConstants.DEFAULT_ITEM_ID) {

                            log.debug("Creating new default experimentOptParamDef object.");
                            facade.createDefaultRecord(experimentOptParam);
                        } else {
                            log.debug("Creating new group experimentOptParamDef object.");
                            int pkExperimentOptParamDef = facade.create(experimentOptParam);

                            ExperimentOptParamDefGroupRelId experimentOptParamDefGroupRelId = new ExperimentOptParamDefGroupRelId(pkExperimentOptParamDef, researchGroupId);
                            ExperimentOptParamDefGroupRel experimentOptParamDefGroupRel = new ExperimentOptParamDefGroupRel(experimentOptParamDefGroupRelId, group, experimentOptParam);
                            facade.createGroupRel(experimentOptParamDefGroupRel);
                        }

                    }

                    setResponsePage(ListExperimentOptParamPage.class);
                }

                @Override
                protected void onError(AjaxRequestTarget target, Form<?> form) {
                    target.add(feedback);
                }
            };

            add(submit, group, paramName, paramDataType);
        }

    }
}
