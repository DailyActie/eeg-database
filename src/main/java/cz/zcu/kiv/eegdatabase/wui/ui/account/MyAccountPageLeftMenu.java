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
 *   MyAccountPageLeftMenu.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.wui.ui.account;

import cz.zcu.kiv.eegdatabase.wui.components.menu.button.IButtonPageMenu;
import cz.zcu.kiv.eegdatabase.wui.components.page.MenuPage;
import cz.zcu.kiv.eegdatabase.wui.ui.order.ListOrderPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Enumeration of left page menu for account section.
 * 
 * @author Jakub Rinkes
 *
 */
public enum MyAccountPageLeftMenu implements IButtonPageMenu {

    ACCOUNT_OVERVIEW(AccountOverViewPage.class, "menuItem.myAccount.overview", null),
    CHANGE_PASSWORD(ChangePasswordPage.class, "menuItem.myAccount.changePassword", null),
    SOCIAL(SocialNetworksPage.class, "menuItem.myAccount.social", null),
    MY_ORDERS(ListOrderPage.class, "pageTitle.order.myOrders", null),
    MEMBERSHIP_PLANS(ListOfMembershipPlansPersonPage.class, "pageTitle.myAccount.membershipPlans", null),
    ;

    private Class<? extends MenuPage> pageClass;
    private String pageTitleKey;
    private PageParameters parameters;

    private MyAccountPageLeftMenu(Class<? extends MenuPage> pageClass, String pageTitleKey, PageParameters parameters) {
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
