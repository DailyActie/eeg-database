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
 *   ExperimentPackage.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.data.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


/**
 *
 * @author bydga
 */
@Entity
@Table(name = "EXPERIMENT_PACKAGE")
public class ExperimentPackage implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EXPERIMENT_PACKAGE_ID")
	private int experimentPackageId;
	
	@ManyToOne
    @JoinColumn(name = "RESEARCH_GROUP")
	private ResearchGroup researchGroup;
	
	@Column(name = "NAME")
	private String name;

	@OneToMany(mappedBy= "experimentPackage")
	private Set<ExperimentPackageLicense> experimentPackageLicenses;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "experimentPackage")
    private Set<Keywords> keywords = new HashSet<Keywords>(0);

	@OneToMany(mappedBy = "experimentPackage")
	private Set<ExperimentPackageConnection> experimentPackageConnections;

	public int getExperimentPackageId() {
		return experimentPackageId;
	}

	public void setExperimentPackageId(int experimentPackageId) {
		this.experimentPackageId = experimentPackageId;
	}

	public ResearchGroup getResearchGroup() {
		return researchGroup;
	}

	public void setResearchGroup(ResearchGroup researchGroup) {
		this.researchGroup = researchGroup;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ExperimentPackageLicense> getExperimentPackageLicenses() {
		return experimentPackageLicenses;
	}

	public void setExperimentPackageLicenses(Set<ExperimentPackageLicense> experimentPackageLicenses) {
		this.experimentPackageLicenses = experimentPackageLicenses;
	}

	public Set<ExperimentPackageConnection> getExperimentPackageConnections() {
		return experimentPackageConnections;
	}

    public Set<Keywords> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<Keywords> keywords) {
        this.keywords = keywords;
    }

    public void setExperimentPackageConnections(Set<ExperimentPackageConnection> experimentPackageConnections) {
		this.experimentPackageConnections = experimentPackageConnections;

	}
	
}
