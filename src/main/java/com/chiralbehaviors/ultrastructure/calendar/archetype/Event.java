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
package com.chiralbehaviors.ultrastructure.calendar.archetype;

import java.math.BigDecimal;
import java.time.Instant;

import com.chiralbehaviors.CoRE.meta.Model;
import com.chiralbehaviors.CoRE.network.Aspect;
import com.chiralbehaviors.CoRE.network.Facet;
import com.chiralbehaviors.CoRE.time.Interval;
import com.chiralbehaviors.CoRE.time.IntervalAttribute;
import com.chiralbehaviors.ultrastructure.calendar.workspace.CalendarWorkspace;

/**
 * @author hparry
 *
 */
public class Event {

    private Interval                           interval;
    @SuppressWarnings("unused")
    private Model                              model;
    private CalendarWorkspace                  workspace;
    private Aspect<Interval>                   isGregorianCalendar;
    private Facet<Interval, IntervalAttribute> facet;

    @SuppressWarnings("unchecked")
    public Event(String name, String description, Instant endDate,
                 CalendarWorkspace workspace, Model model) {
        this.model = model;
        this.workspace = workspace;
        long now = Instant.now().toEpochMilli();
        long duration = endDate.toEpochMilli() - now;
        isGregorianCalendar = new Aspect<Interval>(
                                                   model.getKernel().getIsA(),
                                                   workspace.getGregorianCalendar());
        facet = model.getIntervalModel().create(name,
                                                description,
                                                BigDecimal.valueOf(now),
                                                workspace.getMillisSinceEpoch(),
                                                BigDecimal.valueOf(duration),
                                                workspace.getMilliseconds(),
                                                isGregorianCalendar);
        this.interval = facet.asRuleform();
        setEndDate(endDate);
    }
    
    @SuppressWarnings("unchecked")
    public Event(String name, String description, Instant startDate, Instant endDate,
                 CalendarWorkspace workspace, Model model) {
        this.model = model;
        this.workspace = workspace;
        long duration = endDate.toEpochMilli() - startDate.toEpochMilli();
        isGregorianCalendar = new Aspect<Interval>(
                                                   model.getKernel().getIsA(),
                                                   workspace.getGregorianCalendar());
        facet = model.getIntervalModel().create(name,
                                                description,
                                                BigDecimal.valueOf(startDate.toEpochMilli()),
                                                workspace.getMillisSinceEpoch(),
                                                BigDecimal.valueOf(duration),
                                                workspace.getMilliseconds(),
                                                isGregorianCalendar);
        this.interval = facet.asRuleform();
        setEndDate(endDate);
    }

    public Interval getInterval() {
        return interval;
    }

    public Instant getStartDate() {
        Instant date = Instant.ofEpochMilli(interval.getStart().longValue());
        return date;
    }

    public Instant getEndDate() {
        return Instant.ofEpochMilli(facet.getValue(workspace.getEndDate()).getNumericValue().longValue());
    }

    public void setEndDate(Instant date) {
        facet.getValue(workspace.getEndDate()).setNumericValue(BigDecimal.valueOf(date.toEpochMilli()));
        long duration = date.toEpochMilli() - interval.getStart().longValue();
        interval.setDuration(BigDecimal.valueOf(duration));
        interval.setDurationUnit(workspace.getMilliseconds());
    }
}
