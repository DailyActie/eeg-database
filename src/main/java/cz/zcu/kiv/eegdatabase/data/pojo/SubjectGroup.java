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
 *   SubjectGroup.java, 2013/10/02 00:01 Jakub Rinkes
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * SubjectGroup generated by hbm2java
 */
@Entity
@Table(name = "SUBJECT_GROUP")
public class SubjectGroup implements java.io.Serializable {

	private int subjectGroupId;
	private String title;
	private String description;
	private Set<Experiment> experiments = new HashSet<Experiment>(0);

	public SubjectGroup() {
	}

	public SubjectGroup(String title, String description,
			Set<Experiment> experiments) {
		this.title = title;
		this.description = description;
		this.experiments = experiments;
	}

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "SUBJECT_GROUP_ID", nullable = false, precision = 22, scale = 0)
	public int getSubjectGroupId() {
		return this.subjectGroupId;
	}

	public void setSubjectGroupId(int subjectGroupId) {
		this.subjectGroupId = subjectGroupId;
	}

	@Column(name = "TITLE")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subjectGroup")
	public Set<Experiment> getExperiments() {
		return this.experiments;
	}

	public void setExperiments(Set<Experiment> experiments) {
		this.experiments = experiments;
	}

}
