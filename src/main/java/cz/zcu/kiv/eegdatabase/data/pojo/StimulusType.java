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
 *   StimulusType.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.data.pojo;

// Generated 2.12.2013 0:56:28 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * StimulusType generated by hbm2java
 */
@Entity
@Table(name = "STIMULUS_TYPE")
public class StimulusType implements java.io.Serializable {

	private int stimulusTypeId;
	private String description;
	private Set<StimulusRel> stimulusRels = new HashSet<StimulusRel>(0);
	private Set<ResearchGroup> researchGroups = new HashSet<ResearchGroup>(0);

	public StimulusType() {
	}

	public StimulusType(String description, Set<StimulusRel> stimulusRels,
			Set<ResearchGroup> researchGroups) {
		this.description = description;
		this.stimulusRels = stimulusRels;
		this.researchGroups = researchGroups;
	}

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "STIMULUS_TYPE_ID", nullable = false, precision = 22, scale = 0)
	public int getStimulusTypeId() {
		return this.stimulusTypeId;
	}

	public void setStimulusTypeId(int stimulusTypeId) {
		this.stimulusTypeId = stimulusTypeId;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "stimulusType")
	public Set<StimulusRel> getStimulusRels() {
		return this.stimulusRels;
	}

	public void setStimulusRels(Set<StimulusRel> stimulusRels) {
		this.stimulusRels = stimulusRels;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "STIMULUS_TYPE_GROUP_REL", joinColumns = { @JoinColumn(name = "STIMULUS_TYPE_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "RESEARCH_GROUP_ID", nullable = false, updatable = false) })
	public Set<ResearchGroup> getResearchGroups() {
		return this.researchGroups;
	}

	public void setResearchGroups(Set<ResearchGroup> researchGroups) {
		this.researchGroups = researchGroups;
	}

}
