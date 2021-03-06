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
 *   AdministrationPageLeftMenu.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.wui.ui.administration;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import cz.zcu.kiv.eegdatabase.wui.components.menu.button.IButtonPageMenu;
import cz.zcu.kiv.eegdatabase.wui.components.page.MenuPage;
import cz.zcu.kiv.eegdatabase.wui.components.utils.PageParametersUtils;
import cz.zcu.kiv.eegdatabase.wui.ui.experiments.metadata.template.ListTemplatePage;

/**
 * Enumeration of left page menu for administration section.
 * @author Jakub Rinkes
 *
 */
public enum AdministrationPageLeftMenu implements IButtonPageMenu {

    CHANGE_USER_ROLE_PAGE(AdminManageUserRolePage.class, "menuItem.manageRoles", null),
    MANAGE_PERSON(AdminManagePersonPage.class, "menuItem.managePerson", null),
	MANAGE_RESEARCH_GROUP_PAGE(ManageResearchGroupPage.class, "menuItem.manageResearchGroup", null),
    MEMBERSHIP_PLANS(AdminManageMembershipPlansPage.class,"menuItem.manageMembershipPlan",null),
    MANAGE_LICENSES(AdminManageLicensesPage.class, "menuItem.manageLicenses", null),
	MANAGE_SYSTEM_TEMPLATE(ListTemplatePage.class, "pageTitle.template.system", PageParametersUtils.getDefaultPageParameters(0)),
	//ODML_MIGRATION_PAGE(ODMLMigrationPage.class, "pageTitle.migration.short", null)
    ;

    private Class<? extends MenuPage> pageClass;
    private String pageTitleKey;
    private PageParameters parameters;

    private AdministrationPageLeftMenu(Class<? extends MenuPage> pageClass, String pageTitleKey, PageParameters parameters) {
        this.pageClass = pageClass;
        this.pageTitleKey = pageTitleKey;
        this.parameters = parameters;
    }

    @Override
    public Class<? extends MenuPage> getPageClass() {
        return pageClass;
    }

    @Override
    public String getPageTitleKey() {
        return pageTitleKey;
    }

    @Override
    public PageParameters getPageParameters() {
        return parameters;
    }
}
