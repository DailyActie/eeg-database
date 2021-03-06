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
 *   FormPropertyPanel.java, 2015/02/26 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.wui.ui.experiments.metadata;

import java.util.List;

import odml.core.Property;
import odml.core.Value;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import cz.zcu.kiv.eegdatabase.wui.ui.experiments.metadata.template.PropertyValueDropDownChoiceModel;
import cz.zcu.kiv.eegdatabase.wui.ui.experiments.metadata.template.PropertyValueModel;

public class FormPropertyPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private TextField textField;
    private DropDownChoice<Value> choice;

    public FormPropertyPanel(String id, IModel<Property> model) {
        super(id, new CompoundPropertyModel<Property>(model));

        setupLabels();
        setupFormComponents(model);
    }

    private void setupFormComponents(IModel<Property> model) {

        textField = new TextField("textfield", new PropertyValueModel(model.getObject()));
        add(textField);

        choice = new DropDownChoice("select", new PropertyValueDropDownChoiceModel(model.getObject()), new PropertyModel<List<Value>>(model.getObject(), "values"));
        add(choice);

        boolean singleValue = model.getObject().getValues() != null && model.getObject().getValues().size() > 1 ? false : true;
        textField.setVisibilityAllowed(singleValue);
        choice.setVisibilityAllowed(!singleValue);

        textField.setLabel(new Model<String>(model.getObject().getName()));
        choice.setLabel(new Model<String>(model.getObject().getName()));

        boolean required = model.getObject().getGuiHelper().getRequired();
        textField.setRequired(required);
        choice.setRequired(required);

    }

    private void setupLabels() {

        add(new Label("name"));
        add(new Label("definition"));
    }

}
