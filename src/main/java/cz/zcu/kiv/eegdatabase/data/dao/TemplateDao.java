package cz.zcu.kiv.eegdatabase.data.dao;

import cz.zcu.kiv.eegdatabase.data.pojo.Template;

import java.util.List;

/**
 * ********************************************************************************************************************
 * <p/>
 * This file is part of the eegdatabase project
 * <p/>
 * ==========================================
 * <p/>
 * Copyright (C) 2014 by University of West Bohemia (http://www.zcu.cz/en/)
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
 * TemplateDao, 2014/07/02 11:18 Prokop
 * <p/>
 * ********************************************************************************************************************
 */
public interface TemplateDao extends GenericDao<Template, Integer> {

    public List<Template> getTemplatesByPerson(int personId);

    public List<Template> getDefaultTemplates();

    public Template getTemplateByPersonAndName(int personId, String name);

    public boolean canSaveName(String name, int personId);
}
