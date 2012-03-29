package cz.zcu.kiv.eegdatabase.data.pojo;
// Generated 19.1.2010 23:18:53 by Hibernate Tools 3.2.1.GA

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Person generated by hbm2java
 */
@Entity
@Indexed
@Analyzer(impl = StandardAnalyzer.class)
@javax.persistence.Table(name = "PERSON")
public class Person implements Serializable, Comparable<Person> {

    @DocumentId
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PERSON_ID")
    private int personId;
    @Column(name = "GIVENNAME")
    private String givenname;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "DATE_OF_BIRTH")
    private Timestamp dateOfBirth;
    @Column(name = "GENDER")
    private char gender;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "REGISTRATION_DATE")
    private Timestamp registrationDate;
    @Column(name = "AUTHENTICATION")
    private String authenticationHash;
    @Column(name = "CONFIRMED")
    private boolean confirmed;
    @Fields({
            @Field(index = Index.TOKENIZED), //same property indexed multiple times
            @Field(name = "note"),
            @Field(store = Store.YES)}) //use a different field name
    @Column(name = "NOTE")
    private String note;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "AUTHORITY")
    private String authority;
    @ManyToOne
    @JoinColumn(name = "DEFAULT_GROUP_ID")
    private ResearchGroup defaultGroup;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Experiment> experiments = new HashSet<Experiment>(0);
    @OneToMany(mappedBy = "person")
    private Set<ResearchGroupMembership> researchGroupMemberships = new HashSet<ResearchGroupMembership>(0);
    @OneToMany(mappedBy = "person")
    private Set<Scenario> scenarios = new HashSet<Scenario>(0);
    @OneToMany(mappedBy = "personByOwnerId")
    private Set<Experiment> experimentsForOwnerId = new HashSet<Experiment>(0);
    @OneToMany(mappedBy = "personBySubjectPersonId")
    private Set<Experiment> experimentsForSubjectPersonId = new HashSet<Experiment>(0);
    @OneToMany(mappedBy = "person")
    private Set<PersonOptParamVal> personOptParamVals = new HashSet<PersonOptParamVal>(0);
    @OneToMany(mappedBy = "person")
    private Set<ResearchGroup> researchGroups = new HashSet<ResearchGroup>(0);
    @OneToMany(mappedBy = "person")
    private Set<GroupPermissionRequest> requests = new HashSet<GroupPermissionRequest>(0);
    @OneToMany(mappedBy = "person")
    private Set<History> histories = new HashSet<History>(0);
    @ManyToMany(mappedBy = "subscribers")
    private Set<Article> articlesSubscribtions = new HashSet<Article>(0);
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<ResearchGroup> articlesGroupSubscribtions = new HashSet<ResearchGroup>(0);
    @OneToMany(mappedBy = "owner")
    private Set<ServiceResult> results = new HashSet<ServiceResult>(0);
    @Column(name = "FB_UID")
    private String facebookId;
    @Column(name = "LATERALITY")
    private char laterality;
    @ManyToOne
    @JoinColumn(name = "EDUCATION_LEVEL_ID")
    private EducationLevel educationLevel;
    @Column(name = "ORA_ROWSCN", insertable = false, updatable = false)
    private long scn;

    public Person() {
    }

    public Person(int personId, String surname, char gender) {
        this.personId = personId;
        this.surname = surname;
        this.gender = gender;
    }

    public Person(int personId, String givenname, String surname, Timestamp dateOfBirth, char gender, String phoneNumber, String note, String username, String password, String authority, Set<Experiment> experiments, Set<ResearchGroupMembership> researchGroupMemberships, Set<Scenario> scenarios, Set<Experiment> experimentsForOwnerId, Set<Experiment> experimentsForSubjectPersonId, Set<PersonOptParamVal> personOptParamVals, Set<ResearchGroup> researchGroups) {
        this.personId = personId;
        this.givenname = givenname;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.note = note;
        this.username = username;
        this.password = password;
        this.authority = authority;
        this.experiments = experiments;
        this.researchGroupMemberships = researchGroupMemberships;
        this.scenarios = scenarios;
        this.experimentsForOwnerId = experimentsForOwnerId;
        this.experimentsForSubjectPersonId = experimentsForSubjectPersonId;
        this.personOptParamVals = personOptParamVals;
        this.researchGroups = researchGroups;
    }

    public int getPersonId() {
        return this.personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getGivenname() {
        return this.givenname;
    }

    public void setGivenname(String givenname) {
        this.givenname = givenname;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Timestamp getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(Timestamp dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public char getGender() {
        return this.gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return this.username;//currently username IS the email
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public long getScn() {
        return scn;
    }

    public Set<Experiment> getExperiments() {
        return this.experiments;
    }

    public void setExperiments(Set<Experiment> experiments) {
        this.experiments = experiments;
    }

    public Set<ResearchGroupMembership> getResearchGroupMemberships() {
        return this.researchGroupMemberships;
    }

    public void setResearchGroupMemberships(Set<ResearchGroupMembership> researchGroupMemberships) {
        this.researchGroupMemberships = researchGroupMemberships;
    }

    public Set<Scenario> getScenarios() {
        return this.scenarios;
    }

    public void setScenarios(Set<Scenario> scenarios) {
        this.scenarios = scenarios;
    }

    public Set<Experiment> getExperimentsForOwnerId() {
        return this.experimentsForOwnerId;
    }

    public void setExperimentsForOwnerId(Set<Experiment> experimentsForOwnerId) {
        this.experimentsForOwnerId = experimentsForOwnerId;
    }

    public Set<Experiment> getExperimentsForSubjectPersonId() {
        return this.experimentsForSubjectPersonId;
    }

    public void setExperimentsForSubjectPersonId(Set<Experiment> experimentsForSubjectPersonId) {
        this.experimentsForSubjectPersonId = experimentsForSubjectPersonId;
    }

    public Set<PersonOptParamVal> getPersonOptParamVals() {
        return this.personOptParamVals;
    }

    public void setPersonOptParamVals(Set<PersonOptParamVal> personOptParamVals) {
        this.personOptParamVals = personOptParamVals;
    }

    public Set<ResearchGroup> getResearchGroups() {
        return this.researchGroups;
    }

    public void setResearchGroups(Set<ResearchGroup> researchGroups) {
        this.researchGroups = researchGroups;
    }


    public ResearchGroup getDefaultGroup() {
        return defaultGroup;
    }

    public void setDefaultGroup(ResearchGroup defaultGroup) {
        this.defaultGroup = defaultGroup;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getAuthenticationHash() {
        return authenticationHash;
    }

    public void setAuthenticationHash(String authenticationHash) {
        this.authenticationHash = authenticationHash;
    }

    public Set<History> getHistories() {
        return histories;
    }

    public void setHistories(Set<History> histories) {
        this.histories = histories;
    }

    public Set<GroupPermissionRequest> getRequests() {
        return requests;
    }

    public void setRequests(Set<GroupPermissionRequest> requests) {
        this.requests = requests;
    }

    public Set<Article> getArticlesSubscribtions() {
        return articlesSubscribtions;
    }

    public void setArticlesSubscribtions(Set<Article> articlesSubscribtions) {
        this.articlesSubscribtions = articlesSubscribtions;
    }

    public Set<ResearchGroup> getArticlesGroupSubscribtions() {
        return articlesGroupSubscribtions;
    }

    public void setArticlesGroupSubscribtions(Set<ResearchGroup> articlesGroupSubscribtions) {
        this.articlesGroupSubscribtions = articlesGroupSubscribtions;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public Set<ServiceResult> getResults() {
        return results;
    }

    public void setResults(Set<ServiceResult> results) {
        this.results = results;
    }

    public int compareTo(Person person){
        return this.surname.compareTo(person.getSurname());

    }

    public char getLaterality() {
        return laterality;
    }

    public void setLaterality(char laterality) {
        this.laterality = laterality;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }
}


