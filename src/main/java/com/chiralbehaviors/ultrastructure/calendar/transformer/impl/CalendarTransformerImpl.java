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
package com.chiralbehaviors.ultrastructure.calendar.transformer.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.chiralbehaviors.CoRE.kernel.Kernel;
import com.chiralbehaviors.CoRE.time.Interval;
import com.chiralbehaviors.ultrastructure.calendar.transformer.CalendarTransformer;
import com.chiralbehaviors.ultrastructure.calendar.workspace.CalendarWorkspace;

/**
 * @author hparry
 *
 */
public class CalendarTransformerImpl implements CalendarTransformer {
    
    private CalendarWorkspace workspace;
    private Kernel kernel;

    public static final String DEFAULT_DATE_FORMAT = "yyyy.MM.dd HH:mm:ss:SSS z";
    /* (non-Javadoc)
     * @see com.chiralbehaviors.ultrastructure.calendar.transformer.CalendarTransformer#getInterval(java.util.Calendar)
     */
    public Interval getInterval(Calendar calendar) {
        Interval existing = find(calendar);
        if (existing != null) {
            return existing;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        Interval interval = new Interval(sdf.format(calendar.getTime()));
        long dateInMillis = calendar.getTimeInMillis();
        interval.setStart(BigDecimal.valueOf(dateInMillis));
        interval.setStartUnit(workspace.getMillisSinceEpoch());
        interval.setDuration(null);
        interval.setDurationUnit(kernel.getNotApplicableUnit());
        return interval;
    }

    /**
     * @param calendar
     * @return
     */
    private Interval find(Calendar calendar) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.chiralbehaviors.ultrastructure.calendar.transformer.CalendarTransformer#getCalendar(com.chiralbehaviors.CoRE.time.Interval)
     */
    public Calendar getCalendar(Interval interval) {
        // TODO Auto-generated method stub
        return null;
    }
    
    

}
