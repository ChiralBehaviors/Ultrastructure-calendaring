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

import com.chiralbehaviors.CoRE.attribute.unit.Unit;
import com.chiralbehaviors.CoRE.network.Relationship;
import com.chiralbehaviors.CoRE.time.Interval;

/**
 * @author hparry
 *
 */
public interface CalendarWorkspace {
    
    //units
    Interval getMillisecond();
    Interval getSecond();
    Interval getMinute();
    Interval getHour();
    Interval getDay();
    Interval getYear();
    
    //Days of week
    Interval getSunday();
    Interval getMonday();
    Interval getTuesday();
    Interval getWednesday();
    Interval getThursday();
    Interval getFriday();
    Interval getSaturday();
    
    //Months
    Interval getJanuary();
    Interval getFebruary();
    Interval getMarch();
    Interval getApril();
    Interval getMay();
    Interval getJune();
    Interval getJuly();
    Interval getAugust();
    Interval getSeptember();
    Interval getOctober();
    Interval getNovember();
    Interval getDecember();
    
    //relationships
    Relationship getOnDay();
    Relationship getDayOf();
    Relationship getInMonth();
    Relationship getMonthOf();
    Relationship getInYear();
    Relationship getYearOf();

    //units
    Unit getMillisSinceEpoch();
    Unit getMilliseconds();
    

}
