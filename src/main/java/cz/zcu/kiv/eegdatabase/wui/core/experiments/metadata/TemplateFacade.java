package cz.zcu.kiv.eegdatabase.wui.core.experiments.metadata;

import java.util.List;

import odml.core.Section;
import cz.zcu.kiv.eegdatabase.data.pojo.Template;
import cz.zcu.kiv.eegdatabase.wui.core.GenericFacade;

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
 * TemplateFacade, 2014/07/08 14:44 Prokop
 * <p/>
 * ********************************************************************************************************************
 */
public interface TemplateFacade extends GenericFacade<Template, Integer> {

    public List<Template> getTemplatesByPerson(int personId);

    public List<Template> getDefaultTemplates();

    /**
     * Finds all default and user's templates
     * @param personId id of a user
     * @return default + user's templates
     */
    public List<Template> getUsableTemplates(int personId);

    public Template getTemplateByPersonAndName(int personId, String name);

    public boolean isDefault(int id);

    public boolean canSaveName(String name, int personId);
    
    public List<Section> getListOfAvailableODMLSections();
    
    public void migrateSQLToES();

    public boolean createSystemTemplate(Section section);
}
