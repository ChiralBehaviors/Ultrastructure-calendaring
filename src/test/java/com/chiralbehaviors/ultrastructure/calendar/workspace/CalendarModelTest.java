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

import java.time.Instant;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.chiralbehaviors.CoRE.meta.models.AbstractModelTest;
import com.chiralbehaviors.CoRE.time.Interval;
import com.chiralbehaviors.ultrastructure.calendar.archetype.Event;
import com.chiralbehaviors.ultrastructure.calendar.model.CalendarModel;
import com.chiralbehaviors.ultrastructure.calendar.model.impl.CalendarModelImpl;

import static org.junit.Assert.*;

/**
 * @author hparry
 *
 */
public class CalendarModelTest extends AbstractModelTest {

    private static CalendarWorkspaceBootstrap calWs;

    @BeforeClass
    public static void init() {
        calWs = new CalendarWorkspaceBootstrap(model);
        calWs.createWorkspace();
    }

    @Test
    public void testGetEventsWithin() {
        CalendarModel cm = new CalendarModelImpl(em, model, calWs);

        Instant now = Instant.now();

        Instant beforeNow = Instant.ofEpochMilli(now.toEpochMilli() - 1000);
        Instant afterNow = Instant.ofEpochMilli(now.toEpochMilli() + 1000);

        em.getTransaction().begin();
        Event before = new Event(
                                 "before now",
                                 null,
                                 beforeNow,
                                 Instant.ofEpochMilli(beforeNow.toEpochMilli() + 20),
                                 calWs, model);
        Event after = new Event(
                               "after now",
                               null,
                               afterNow,
                               Instant.ofEpochMilli(afterNow.toEpochMilli() + 20),
                               calWs, model);
        Event nowish = new Event(
                                 "now",
                                 null,
                                 now,
                                 Instant.ofEpochMilli(now.toEpochMilli() + 20),
                                 calWs, model);
        em.getTransaction().commit();
        
        Event period = new Event(
                                 "target period",
                                 null,
                                 beforeNow,
                                 Instant.ofEpochMilli(beforeNow.toEpochMilli() + 20),
                                 calWs, model);
        List<Interval> containment = cm.getEventsWithin(period.getInterval());
        assertEquals(1, containment.size());
    }

}
