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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Test;

import com.chiralbehaviors.CoRE.time.Interval;
import com.chiralbehaviors.ultrastructure.calendar.archetype.DateArchetype;
import com.chiralbehaviors.ultrastructure.calendar.transformer.CalendarTransformer;

/**
 * @author hparry
 *
 */
public class CalendarTranslationTest {
    
    @Test
    public void testTranslate() {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(1996, Calendar.JANUARY, 1);
        
        Interval date = new Interval("Jan 1, 1996", null, null);
        CalendarTransformer transformer = mock(CalendarTransformer.class);
        when(transformer.getInterval(cal)).thenReturn(date);
        DateArchetype arch = mock(DateArchetype.class);
        when(arch.getDayOfMonth()).thenReturn(new Interval("1", null, null));
        when(arch.getMonth()).thenReturn(new Interval("January", null, null));
        when(arch.getYear()).thenReturn(new Interval("1996", null, null));
    }

}
