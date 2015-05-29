package cz.zcu.kiv.eegdatabase.wui.core.experimentLicense;

import cz.zcu.kiv.eegdatabase.data.pojo.Experiment;
import cz.zcu.kiv.eegdatabase.data.pojo.ExperimentLicence;
import cz.zcu.kiv.eegdatabase.data.pojo.License;
import cz.zcu.kiv.eegdatabase.wui.core.GenericFacade;

/**
 * Created by Lichous on 28.4.15.
 */
public interface ExperimentLicenseFacade extends GenericFacade<ExperimentLicence, Integer> {

    public void remove(Experiment experiment, License license);
}
