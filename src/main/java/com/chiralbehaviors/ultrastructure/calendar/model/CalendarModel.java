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
package com.chiralbehaviors.ultrastructure.calendar.model;

import java.util.List;

import com.chiralbehaviors.CoRE.meta.IntervalModel;
import com.chiralbehaviors.CoRE.time.Interval;
import com.google.ical.iter.RecurrenceIterator;
import com.google.ical.values.TimeValue;

/**
 * @author hparry
 *
 */
public interface CalendarModel extends IntervalModel {
    
    List<Interval> getEventsWithin(Interval period);
    
    List<Interval> getEventsOverlapping(Interval period);
    
    Interval createRecurringEvent(String name, String description, TimeValue time, long durationInMillis, RecurrenceIterator iter);

}
