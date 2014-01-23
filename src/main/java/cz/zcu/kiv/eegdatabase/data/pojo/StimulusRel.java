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
 *   StimulusRel.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.data.pojo;

// Generated 2.12.2013 0:56:28 by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * StimulusRel generated by hbm2java
 */
@Entity
@Table(name = "STIMULUS_REL")
public class StimulusRel implements java.io.Serializable {

	private StimulusRelId stimulusRelId;
	private Scenario scenario;
	private Stimulus stimulus;
	private StimulusType stimulusType;

	public StimulusRel() {
	}

	public StimulusRel(StimulusRelId stimulusRelId, Scenario scenario,
			Stimulus stimulus, StimulusType stimulusType) {
		this.stimulusRelId = stimulusRelId;
		this.scenario = scenario;
		this.stimulus = stimulus;
		this.stimulusType = stimulusType;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "scenarioId", column = @Column(name = "SCENARIO_ID", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "stimulusId", column = @Column(name = "STIMULUS_ID", nullable = false, precision = 22, scale = 0)) })
	public StimulusRelId getStimulusRelId() {
		return this.stimulusRelId;
	}

	public void setStimulusRelId(StimulusRelId stimulusRelId) {
		this.stimulusRelId = stimulusRelId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SCENARIO_ID", nullable = false, insertable = false, updatable = false)
	public Scenario getScenario() {
		return this.scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STIMULUS_ID", nullable = false, insertable = false, updatable = false)
	public Stimulus getStimulus() {
		return this.stimulus;
	}

	public void setStimulus(Stimulus stimulus) {
		this.stimulus = stimulus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STIMULUS_TYPE_ID", nullable = false)
	public StimulusType getStimulusType() {
		return this.stimulusType;
	}

	public void setStimulusType(StimulusType stimulusType) {
		this.stimulusType = stimulusType;
	}

}
