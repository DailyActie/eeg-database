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
 *   FileMetadataParamDefGroupRelId.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.data.pojo;

// Generated 2.12.2013 0:56:28 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * FileMetadataParamDefGroupRelId generated by hbm2java
 */
@Embeddable
public class FileMetadataParamDefGroupRelId implements java.io.Serializable {

	private int fileMetadataParamDefId;
	private int researchGroupId;

	public FileMetadataParamDefGroupRelId() {
	}

	public FileMetadataParamDefGroupRelId(int fileMetadataParamDefId,
			int researchGroupId) {
		this.fileMetadataParamDefId = fileMetadataParamDefId;
		this.researchGroupId = researchGroupId;
	}

	@Column(name = "FILE_METADATA_PARAM_DEF_ID", nullable = false, precision = 22, scale = 0)
	public int getFileMetadataParamDefId() {
		return this.fileMetadataParamDefId;
	}

	public void setFileMetadataParamDefId(int fileMetadataParamDefId) {
		this.fileMetadataParamDefId = fileMetadataParamDefId;
	}

	@Column(name = "RESEARCH_GROUP_ID", nullable = false, precision = 22, scale = 0)
	public int getResearchGroupId() {
		return this.researchGroupId;
	}

	public void setResearchGroupId(int researchGroupId) {
		this.researchGroupId = researchGroupId;
	}

}
