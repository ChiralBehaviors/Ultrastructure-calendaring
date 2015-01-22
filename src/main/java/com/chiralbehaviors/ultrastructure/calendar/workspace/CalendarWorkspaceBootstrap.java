/**
 * Copyright (c) 2014 Chiral Behaviors, LLC, all rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chiralbehaviors.ultrastructure.calendar.workspace;

import javax.persistence.EntityManager;

import com.chiralbehaviors.CoRE.attribute.Attribute;
import com.chiralbehaviors.CoRE.attribute.ValueType;
import com.chiralbehaviors.CoRE.attribute.unit.Unit;
import com.chiralbehaviors.CoRE.kernel.Kernel;
import com.chiralbehaviors.CoRE.meta.Model;
import com.chiralbehaviors.CoRE.network.Aspect;
import com.chiralbehaviors.CoRE.network.Relationship;
import com.chiralbehaviors.CoRE.time.Interval;

/**
 * @author hparry
 *
 */
public class CalendarWorkspaceBootstrap extends ReadOnlyCalendarWorkspace {

    private Model         model;
    private EntityManager em;
    private Kernel        kernel;

    public CalendarWorkspaceBootstrap(Model model) {
        this.model = model;
        this.em = model.getEntityManager();
        this.kernel = model.getKernel();
    }

    public void createWorkspace() {
        createUnits();
        createAttributes();
        createIntervals();
        createRelationships();
        authorizeAttributes();
    }

    /**
     * 
     */
    private void createRelationships() {
        inRecurrence = new Relationship("InRecurrence", "Single occurrence A is in recurrence B", kernel.getCore());
        em.persist(inRecurrence);
        
        hasOccurrence = new Relationship("HasOccurrence", "Recurrence pattern B has occurrence A", kernel.getCore());
        em.persist(hasOccurrence);
        
        inRecurrence.setInverse(hasOccurrence);
        hasOccurrence.setInverse(inRecurrence);
        
    }

    /**
     * 
     */
    private void authorizeAttributes() {
        model.getIntervalModel().authorize(new Aspect<Interval>(
                                                                kernel.getIsA(),
                                                                gregorianCalendar),
                                           endDate);

    }

    /**
     * 
     */
    private void createIntervals() {
        gregorianCalendar = model.getIntervalModel().newDefaultInterval("Gregorian Calendar",
                                                                        "The Gregorian Calendar interval supertype");
    }

    /**
     * 
     */
    private void createAttributes() {
        endDate = new Attribute("End Date", "The end date attribute",
                                kernel.getCore());
        endDate.setValueType(ValueType.NUMERIC);
        em.persist(endDate);

    }

    /**
     * 
     */
    private void createUnits() {
        milliseconds = new Unit("Milliseconds", "The milliseconds unit",
                                kernel.getCore());
        em.persist(milliseconds);

        millisSinceEpoch = new Unit(
                                    "MillisSinceEpoch",
                                    "The unit representing the number of milliseconds since the epoch",
                                    kernel.getCore());
        em.persist(millisSinceEpoch);
    }

}
