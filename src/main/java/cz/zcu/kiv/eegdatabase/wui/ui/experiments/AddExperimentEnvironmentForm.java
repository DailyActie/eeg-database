package cz.zcu.kiv.eegdatabase.wui.ui.experiments;

import cz.zcu.kiv.eegdatabase.data.pojo.*;
import cz.zcu.kiv.eegdatabase.wui.components.utils.ResourceUtils;
import cz.zcu.kiv.eegdatabase.wui.core.common.HardwareFacade;
import cz.zcu.kiv.eegdatabase.wui.core.common.PharmaceuticalFacade;
import cz.zcu.kiv.eegdatabase.wui.core.common.SoftwareFacade;
import cz.zcu.kiv.eegdatabase.wui.core.common.WeatherFacade;
import cz.zcu.kiv.eegdatabase.wui.core.experiments.DiseaseFacade;
import cz.zcu.kiv.eegdatabase.wui.ui.experiments.modals.*;
import org.apache.wicket.Page;
import org.apache.wicket.PageReference;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.AjaxFormValidatingBehavior;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.GenericFactory;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.GenericModel;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.GenericValidator;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.RepeatableInputPanel;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.time.Duration;
import org.hibernate.sql.Select;
import sun.net.www.content.text.Generic;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddExperimentEnvironmentForm extends Form<Experiment> {

    @SpringBean
    private WeatherFacade weatherFacade;
    @SpringBean
    private HardwareFacade hardwareFacade;
    @SpringBean
    private SoftwareFacade softwareFacade;
    @SpringBean
    private DiseaseFacade diseaseFacade;
    @SpringBean
    private PharmaceuticalFacade pharmaceuticalFacade;

    private Experiment experiment;
    public ListMultipleChoice hw = null;
    private ListMultipleChoice sw = null;
    private DropDownChoice<String> weather = null;
    private TextField temperature = null;
    private TextField<String> environmentNote;
    final int SELECT_ROWS = 5;

    private List<Hardware> hwListForModel;
    private List<Software> swListForModel;
    private Weather weatherForModel;
    private Integer integerForModel;
    private String environmentNoteForModel;
    private GenericModel<Weather> weatherModel;
    private List<GenericModel<Disease>> diseases;
    private List<GenericModel<Pharmaceutical>> pharmaceuticals;

    public AddExperimentEnvironmentForm(String id, Experiment experiment){
        super(id);
        this.experiment = experiment;

        addHardware();
        addSoftware();
        addWeather();
        addTemperature();
        addEnvironmentNote();
        addDisease();
        addPharmaceutical();

        createModalWindows();
    }

    private void addHardware(){
        hwListForModel = new ArrayList<Hardware>();
        hw = new ListMultipleChoice("hardwares", new GenericModel(hwListForModel), getHardwares()).setMaxRows(SELECT_ROWS);
        hw.setLabel(ResourceUtils.getModel("label.hardware"));
        hw.setRequired(true);

        ComponentFeedbackMessageFilter hwFilter = new ComponentFeedbackMessageFilter(hw);
        final FeedbackPanel hwFeedback = new FeedbackPanel("hwFeedback", hwFilter);
        hwFeedback.setOutputMarkupId(true);
        hw.add(new AjaxFeedbackUpdatingBehavior("blur", hwFeedback));

        add(hw);
        add(hwFeedback);
    }

    private void addSoftware(){
        swListForModel = new ArrayList<Software>();
        sw = new ListMultipleChoice("softwares", new GenericModel(swListForModel), getSoftwares()).setMaxRows(SELECT_ROWS);
        sw.setLabel(ResourceUtils.getModel("label.software"));
        sw.setRequired(true);

        ComponentFeedbackMessageFilter swFilter = new ComponentFeedbackMessageFilter(sw);
        final FeedbackPanel swFeedback = new FeedbackPanel("swFeedback", swFilter);
        swFeedback.setOutputMarkupId(true);
        sw.add(new AjaxFeedbackUpdatingBehavior("blur", swFeedback));

        add(sw);
        add(swFeedback);
    }

    private void addWeather(){
        weatherForModel = new Weather();
        weatherModel = new GenericModel<Weather>(weatherForModel);
        weather = new DropDownChoice("weather", weatherModel, getWeathers());
        weather.setRequired(true);
        weather.setLabel(ResourceUtils.getModel("label.weather"));
        weather.setNullValid(true);
        weather.add(new AjaxFormComponentUpdatingBehavior("onchange") {
            @Override
            protected void onUpdate(AjaxRequestTarget ajaxRequestTarget) {
                weatherForModel = weatherModel.getObject();
            }
        });

        ComponentFeedbackMessageFilter weatherFilter = new ComponentFeedbackMessageFilter(weather);
        final FeedbackPanel weatherFeedback = new FeedbackPanel("weatherFeedback", weatherFilter);
        weatherFeedback.setOutputMarkupId(true);
        weather.add(new AjaxFeedbackUpdatingBehavior("blur", weatherFeedback));

        add(weather);
        add(weatherFeedback);
    }

    private void addTemperature(){
        temperature = new TextField("temperature", new Model(integerForModel), Integer.class);
        temperature.setRequired(true);
        temperature.setLabel(ResourceUtils.getModel("label.temperature"));

        ComponentFeedbackMessageFilter temperatureFilter = new ComponentFeedbackMessageFilter(temperature);
        final FeedbackPanel temperatureFeedback = new FeedbackPanel("temperatureFeedback", temperatureFilter);
        temperatureFeedback.setOutputMarkupId(true);
        temperature.add(new AjaxFeedbackUpdatingBehavior("blur", temperatureFeedback));

        add(temperature);
        add(temperatureFeedback);
    }

    private void addEnvironmentNote(){
        final Model environmentNoteModel = new Model(environmentNoteForModel);
        environmentNote = new TextField<String>("environmentNote", environmentNoteModel, String.class);
        environmentNote.setLabel(ResourceUtils.getModel("label.environmentNote"));
        environmentNote.add(new AjaxFormComponentUpdatingBehavior("onchange") {
            @Override
            protected void onUpdate(AjaxRequestTarget ajaxRequestTarget) {
                environmentNoteForModel = environmentNoteModel.getObject().toString();
            }
        });
        add(environmentNote);
    }

    private void addDisease() {
        GenericFactory<Disease> factory = new GenericFactory<Disease>(Disease.class);
        GenericValidator<Disease> validator = new GenericValidator<Disease>(diseaseFacade);

        RepeatableInputPanel<Disease> repeatable =
                new RepeatableInputPanel<Disease>("diseases", factory,
                        validator, diseaseFacade);
        diseases = repeatable.getData();
        add(repeatable);
    }

    private void addPharmaceutical() {
        GenericFactory<Pharmaceutical> factory = new GenericFactory<Pharmaceutical>(Pharmaceutical.class);
        GenericValidator<Pharmaceutical> validator = new GenericValidator<Pharmaceutical>(pharmaceuticalFacade);

        RepeatableInputPanel<Pharmaceutical> repeatable =
                new RepeatableInputPanel<Pharmaceutical>("pharmaceuticals", factory,
                        validator, pharmaceuticalFacade);
        pharmaceuticals = repeatable.getData();
        add(repeatable);
    }

    private void createModalWindows(){
        addModalWindowAndButton(this, "addDiseaseModal", "add-disease",
                "addDisease", AddDiseasePage.class.getName());

        addModalWindowAndButton(this, "addPharmaModal", "add-pharma",
                "addPharma", AddPharmaceuticalsPage.class.getName());

        addModalWindowAndButton(this, "addHWModal", "add-hw",
                "addHW", AddHardwarePage.class.getName());

        addModalWindowAndButton(this, "addSWModal", "add-sw",
                "addSW", AddSoftwarePage.class.getName());

        addModalWindowAndButton(this, "addWeatherModal", "add-weather",
                "addWeather", AddWeatherPage.class.getName());
    }

    public void validateForm(){
        validate();
    }

    public boolean isValid(){
        validate();
        return !hasError();
    }

    private List<Weather> getWeathers(){
        return weatherFacade.getAllRecords();
    }

    private List<Software> getSoftwares(){
        return softwareFacade.getAllRecords();
    }

    private List<Hardware> getHardwares(){
        return hardwareFacade.getAllRecords();
    }

    private void addModalWindowAndButton(final Form form, String modalWindowName, String cookieName,
                                         final String buttonName, final String targetClass){

        final ModalWindow addedWindow;
        form.add(addedWindow = new ModalWindow(modalWindowName));
        addedWindow.setCookieName(cookieName);
        addedWindow.setPageCreator(new ModalWindow.PageCreator() {

            @Override
            public Page createPage() {
                try{
                    Constructor<?> cons = null;
                    cons = Class.forName(targetClass).getConstructor(
                    PageReference.class, ModalWindow.class);

                    return (Page) cons.newInstance(
                            getPage().getPageReference(), addedWindow);
                }catch (NoSuchMethodException e){
                    e.printStackTrace();
                }catch (IllegalAccessException e){
                    e.printStackTrace();
                }catch (InstantiationException e){
                    e.printStackTrace();
                }catch (InvocationTargetException e){
                    e.printStackTrace();
                }catch (ClassNotFoundException e){
                    e.printStackTrace();
                }
                return null;
            }
        });

        AjaxButton ajaxButton = new AjaxButton(buttonName, this)
        {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form){
                addedWindow.show(target);
            }
        };
        ajaxButton.add(new AjaxEventBehavior("onclick") {
            @Override
            protected void onEvent(AjaxRequestTarget target) {
                addedWindow.show(target);
            }
        });
        form.add(ajaxButton);
    }

    /**
     * It takes data from the model and based on them get valid data.
     */
    public void save() {
        validate();
        if(!hasError()) {
            experiment.setHardwares(getSet(this.hwListForModel));
            experiment.setSoftwares(getSet(this.swListForModel));
            experiment.setWeather(this.weatherForModel);
            experiment.setTemperature(Integer.parseInt(this.temperature.getModelObject().toString()));
            experiment.setEnvironmentNote(this.environmentNote.getModelObject());
            experiment.setDiseases(getSet(this.diseases));
            experiment.setPharmaceuticals(getSet(this.pharmaceuticals));
        }
    }

    private Set getSet(List objects) {
        Set result = new HashSet();
        for(Object object: objects) {
            result.add(((GenericModel) object).getObject());
        }
        return result;
    }
}