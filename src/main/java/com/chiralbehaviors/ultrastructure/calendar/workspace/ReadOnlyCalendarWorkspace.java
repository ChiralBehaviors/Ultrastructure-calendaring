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

import com.chiralbehaviors.CoRE.attribute.Attribute;
import com.chiralbehaviors.CoRE.attribute.unit.Unit;
import com.chiralbehaviors.CoRE.network.Relationship;
import com.chiralbehaviors.CoRE.time.Interval;

/**
 * @author hparry
 *
 */
public class ReadOnlyCalendarWorkspace implements CalendarWorkspace {

    protected Attribute endDate;
    protected Interval  gregorianCalendar;
    protected Unit      milliseconds;
    protected Unit      millisSinceEpoch;
    protected Relationship inRecurrence;
    protected Relationship hasOccurrence;

    /* (non-Javadoc)
     * @see com.chiralbehaviors.ultrastructure.calendar.workspace.CalendarWorkspace#getMillisSinceEpoch()
     */
    @Override
    public Unit getMillisSinceEpoch() {
        return millisSinceEpoch;
    }

    /* (non-Javadoc)
     * @see com.chiralbehaviors.ultrastructure.calendar.workspace.CalendarWorkspace#getMilliseconds()
     */
    @Override
    public Unit getMilliseconds() {
        return milliseconds;
    }

    /* (non-Javadoc)
     * @see com.chiralbehaviors.ultrastructure.calendar.workspace.CalendarWorkspace#getGregorianCalendar()
     */
    @Override
    public Interval getGregorianCalendar() {
        return gregorianCalendar;
    }

    /* (non-Javadoc)
     * @see com.chiralbehaviors.ultrastructure.calendar.workspace.CalendarWorkspace#getEndDate()
     */
    @Override
    public Attribute getEndDate() {
        return endDate;
    }

    /* (non-Javadoc)
     * @see com.chiralbehaviors.ultrastructure.calendar.workspace.CalendarWorkspace#getInRecurrence()
     */
    @Override
    public Relationship getInRecurrence() {
        return inRecurrence;
    }

    /* (non-Javadoc)
     * @see com.chiralbehaviors.ultrastructure.calendar.workspace.CalendarWorkspace#getHasOccurrence()
     */
    @Override
    public Relationship getHasOccurrence() {
        return hasOccurrence;
    }

}
