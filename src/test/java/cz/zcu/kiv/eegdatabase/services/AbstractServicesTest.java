/*******************************************************************************
 * This file is part of the EEG-database project
 *
 * ==========================================
 *
 * Copyright (C) 2013 by University of West Bohemia (http://www.zcu.cz/en/)
 *
 * ***********************************************************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * ***********************************************************************************************************************
 *
 * AbstractServicesTest.java, 2014/07/18 00:01 Jan Stebetak
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.services;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

/**
 * Created by Honza on 18.7.14.
 */

@ContextConfiguration(locations = {"classpath:/test-context.xml"})
public abstract class AbstractServicesTest extends AbstractTransactionalTestNGSpringContextTests {

}
