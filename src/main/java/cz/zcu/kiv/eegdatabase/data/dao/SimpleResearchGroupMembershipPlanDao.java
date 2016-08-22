package cz.zcu.kiv.eegdatabase.data.dao;

import cz.zcu.kiv.eegdatabase.data.pojo.ResearchGroup;
import cz.zcu.kiv.eegdatabase.data.pojo.ResearchGroupMembershipPlan;

import java.util.List;

/**
 * ********************************************************************************************************************
 * <p/>
 * This file is part of the eegdatabase project
 * <p/>
 * ==========================================
 * <p/>
 * Copyright (C) 2015 by University of West Bohemia (http://www.zcu.cz/en/)
 * <p/>
 * **********************************************************************************************************************
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * <p/>
 * **********************************************************************************************************************
 * <p/>
 * SimpleResearchGroupMembershipPlanDao, 2015/03/23 23:06 administrator
 * <p/>
 * ********************************************************************************************************************
 */
public class SimpleResearchGroupMembershipPlanDao extends SimpleGenericDao<ResearchGroupMembershipPlan,Integer> implements ResearchGroupMembershipPlanDao {

    public SimpleResearchGroupMembershipPlanDao() {
        super(ResearchGroupMembershipPlan.class);
    }

    @Override
    public List<ResearchGroupMembershipPlan> getGroupMembershipPlans(ResearchGroup researchGroup) {
        String query = "select m from ResearchGroupMembershipPlan m where m.researchGroup = :group";

        List<ResearchGroupMembershipPlan> ret = this.getSession().createQuery(query).setParameter("group",researchGroup).list(); //set parameters
        return ret;
    }

    @Override
    public boolean isPlanUsed(int membershipPlanId) {

        String query = "select membershipPlan from ResearchGroupMembershipPlan m where m.membershipPlan.membershipId = :plan";
        return (!this.getSession().createQuery(query).setParameter("plan",membershipPlanId).list().isEmpty());
    }

}
