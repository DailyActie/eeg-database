package cz.zcu.kiv.eegdatabase.data.dao;

import cz.zcu.kiv.eegdatabase.data.pojo.DataFile;
import cz.zcu.kiv.eegdatabase.data.pojo.Experiment;
import cz.zcu.kiv.eegdatabase.data.pojo.Person;
import cz.zcu.kiv.eegdatabase.logic.controller.search.SearchRequest;

import java.io.Serializable;
import java.util.List;

/**
 * DAO for fetching and saving experiments.
 *
 * @author Jindrich Pergler
 */
public interface ExperimentDao<T, PK extends Serializable> extends GenericDao<T, PK> {

    public List<DataFile> getDataFilesWhereExpId(int experimentId);

    public List<DataFile> getDataFilesWhereId(int dataFileId);

    public Experiment getExperimentForDetail(int experimentId);

    public List<Experiment> getExperimentsWhereOwner(Person person, int limit);
    public List<Experiment> getExperimentsWhereOwner(Person person, int start, int limit);
    int getCountForExperimentsWhereOwner(Person loggedUser);

    public List<Experiment> getExperimentsWhereSubject(Person person, int limit);
    public List<Experiment> getExperimentsWhereSubject(Person person, int start, int limit);
    public int getCountForExperimentsWhereSubject(Person person);

    public int getCountForAllExperimentsForUser(Person person);

    public List<Experiment> getAllExperimentsForUser(Person person, int start, int count);

    public List<Experiment> getRecordsNewerThan(long oracleScn, int personId);

    public List<Experiment> getExperimentSearchResults(List<SearchRequest> requests, int personId);

    public List<Experiment> getVisibleExperiments(int personId, int start, int limit);

    public int getVisibleExperimentsCount(int personId);
}
