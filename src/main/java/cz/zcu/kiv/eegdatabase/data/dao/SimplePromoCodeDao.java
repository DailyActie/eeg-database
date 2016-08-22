package cz.zcu.kiv.eegdatabase.data.dao;

import cz.zcu.kiv.eegdatabase.data.pojo.PromoCode;
import cz.zcu.kiv.eegdatabase.wui.core.MembershipPlanType;

import java.sql.Timestamp;
import java.util.Date;
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
 * SimplePromoCodeDao, 2015/03/23 22:50 administrator
 * <p/>
 * ********************************************************************************************************************
 */

public class SimplePromoCodeDao extends SimpleGenericDao<PromoCode,Integer> implements PromoCodeDao {

    public SimplePromoCodeDao() {
        super(PromoCode.class);
    }

    public List<PromoCode> getAvailableGroupPromoCodes()  {
        String query = "select m from PromoCode m where m.type = :promoCodeType and m.valid = 'TRUE'";

        List<PromoCode> ret = this.getSession().createQuery(query).setParameter("promoCodeType", MembershipPlanType.GROUP.getType()).list(); //set parameters
        return ret;
    }

    public List<PromoCode> getAvailablePersonPromoCodes()  {
        String query = "select m from PromoCode m where m.type = :promoCodeType and m.valid = 'TRUE'";

        List<PromoCode> ret = this.getSession().createQuery(query).setParameter("promoCodeType", MembershipPlanType.PERSON.getType()).list(); //set parameters
        return ret;
    }

    public PromoCode getPromoCodeById(Integer id)  {
        String query = "select m from PromoCode m where m.promoCodeId = :id";
        PromoCode ret = (PromoCode) this.getSession().createQuery(query).setParameter("id",id).uniqueResult();
        return ret;
    }

    public PromoCode getPromoCodeByKeyword(String keyWord)  {
        Timestamp time = new Timestamp(new Date().getTime());

        String query = "select m from PromoCode m where m.keyword = :keyword and m.valid='TRUE' and :time between m.from and m.to";
        PromoCode ret = (PromoCode) this.getSession().createQuery(query).setParameter("keyword",keyWord).setParameter("time",time).uniqueResult();
        return ret;
    }

}
