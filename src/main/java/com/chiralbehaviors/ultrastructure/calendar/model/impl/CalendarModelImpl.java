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
package com.chiralbehaviors.ultrastructure.calendar.model.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.chiralbehaviors.CoRE.agency.Agency;
import com.chiralbehaviors.CoRE.attribute.Attribute;
import com.chiralbehaviors.CoRE.attribute.unit.Unit;
import com.chiralbehaviors.CoRE.meta.IntervalModel;
import com.chiralbehaviors.CoRE.meta.Model;
import com.chiralbehaviors.CoRE.network.Aspect;
import com.chiralbehaviors.CoRE.network.Facet;
import com.chiralbehaviors.CoRE.network.Relationship;
import com.chiralbehaviors.CoRE.time.Interval;
import com.chiralbehaviors.CoRE.time.IntervalAttribute;
import com.chiralbehaviors.CoRE.time.IntervalAttributeAuthorization;
import com.chiralbehaviors.CoRE.time.IntervalNetwork;
import com.chiralbehaviors.ultrastructure.calendar.model.CalendarModel;
import com.chiralbehaviors.ultrastructure.calendar.workspace.CalendarWorkspace;

/**
 * @author hparry
 *
 */
public class CalendarModelImpl implements CalendarModel {

    private EntityManager       em;
    private CalendarWorkspace   ws;
    private Model               model;
    private IntervalModel       intModel;

    private static final String GET_EVENTS_WITHIN = "SELECT i FROM Interval i, IntervalNetwork inet"
                                                    + " WHERE inet.relationship = :relationship"
                                                    + " AND inet.child = :child"
                                                    + " AND i.start >= :start"
                                                    + " AND inet.parent = i"
                                                    + " AND i.start + i.duration <= :end";

    /**
     * @param em
     */
    public CalendarModelImpl(EntityManager em, Model model, CalendarWorkspace ws) {

        this.model = model;
        this.em = em;
        this.intModel = model.getIntervalModel();
        this.ws = ws;
    }

    /* (non-Javadoc)
     * @see com.chiralbehaviors.ultrastructure.calendar.model.CalendarModel#getEventsWithin(com.chiralbehaviors.CoRE.time.Interval)
     */
    @Override
    public List<Interval> getEventsWithin(Interval period) {
        TypedQuery<Interval> query = em.createQuery(GET_EVENTS_WITHIN,
                                                         Interval.class);
        query.setParameter("relationship", model.getKernel().getIsA());
        query.setParameter("child", ws.getGregorianCalendar());
        query.setParameter("start", period.getStart());
        query.setParameter("end", period.getStart().longValue()
                                  + period.getDuration().longValue());
        return query.getResultList();
    }

    /* (non-Javadoc)
     * @see com.chiralbehaviors.ultrastructure.calendar.model.CalendarModel#getEventsOverlapping(com.chiralbehaviors.CoRE.time.Interval)
     */
    @Override
    public List<Interval> getEventsOverlapping(Interval period) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param name
     * @param description
     * @param start
     * @param startUnit
     * @param aspect
     * @param aspects
     * @return
     * @see com.chiralbehaviors.CoRE.meta.IntervalModel#create(java.lang.String,
     *      java.lang.String, java.math.BigDecimal,
     *      com.chiralbehaviors.CoRE.attribute.unit.Unit,
     *      com.chiralbehaviors.CoRE.network.Aspect,
     *      com.chiralbehaviors.CoRE.network.Aspect[])
     */
    public Facet<Interval, IntervalAttribute> create(String name,
                                                     String description,
                                                     BigDecimal start,
                                                     Unit startUnit,
                                                     Aspect<Interval> aspect,
                                                     @SuppressWarnings( "unchecked") Aspect<Interval>... aspects) {
        return intModel.create(name, description, start, startUnit, aspect,
                               aspects);
    }

    /**
     * @param name
     * @param description
     * @param aspect
     * @return
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#create(java.lang.String,
     *      java.lang.String, com.chiralbehaviors.CoRE.network.Aspect)
     */
    public Facet<Interval, IntervalAttribute> create(String name,
                                                     String description,
                                                     Aspect<Interval> aspect) {
        return intModel.create(name, description, aspect);
    }

    /**
     * @param name
     * @param description
     * @param start
     * @param startUnit
     * @param duration
     * @param durationUnit
     * @param aspect
     * @param aspects
     * @return
     * @see com.chiralbehaviors.CoRE.meta.IntervalModel#create(java.lang.String,
     *      java.lang.String, java.math.BigDecimal,
     *      com.chiralbehaviors.CoRE.attribute.unit.Unit, java.math.BigDecimal,
     *      com.chiralbehaviors.CoRE.attribute.unit.Unit,
     *      com.chiralbehaviors.CoRE.network.Aspect,
     *      com.chiralbehaviors.CoRE.network.Aspect[])
     */
    public Facet<Interval, IntervalAttribute> create(String name,
                                                     String description,
                                                     BigDecimal start,
                                                     Unit startUnit,
                                                     BigDecimal duration,
                                                     Unit durationUnit,
                                                     Aspect<Interval> aspect,
                                                     @SuppressWarnings("unchecked") Aspect<Interval>... aspects) {
        return intModel.create(name, description, start, startUnit, duration,
                               durationUnit, aspect, aspects);
    }

    /**
     * @param name
     * @param description
     * @param aspect
     * @param aspects
     * @return
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#create(java.lang.String,
     *      java.lang.String, com.chiralbehaviors.CoRE.network.Aspect,
     *      com.chiralbehaviors.CoRE.network.Aspect[])
     */
    public Interval create(String name, String description,
                           Aspect<Interval> aspect,
                           @SuppressWarnings("unchecked") Aspect<Interval>... aspects) {
        return intModel.create(name, description, aspect, aspects);
    }

    /**
     * @param name
     * @param description
     * @return
     * @see com.chiralbehaviors.CoRE.meta.IntervalModel#newDefaultInterval(java.lang.String,
     *      java.lang.String)
     */
    public Interval newDefaultInterval(String name, String description) {
        return intModel.newDefaultInterval(name, description);
    }

    /**
     * @param parent
     * @param r
     * @return
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#getSingleChild(com.chiralbehaviors.CoRE.ExistentialRuleform,
     *      com.chiralbehaviors.CoRE.network.Relationship)
     */
    public Interval getSingleChild(Interval parent, Relationship r) {
        return intModel.getSingleChild(parent, r);
    }

    /**
     * @return
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#getUnlinked()
     */
    public List<Interval> getUnlinked() {
        return intModel.getUnlinked();
    }

    /**
     * @return
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#getUsedRelationships()
     */
    public List<Relationship> getUsedRelationships() {
        return intModel.getUsedRelationships();
    }

    /**
     * @param aspect
     * @param attributes
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#authorize(com.chiralbehaviors.CoRE.network.Aspect,
     *      com.chiralbehaviors.CoRE.attribute.Attribute[])
     */
    public void authorize(Aspect<Interval> aspect, Attribute... attributes) {
        intModel.authorize(aspect, attributes);
    }

    /**
     * @param prototype
     * @return
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#create(com.chiralbehaviors.CoRE.ExistentialRuleform)
     */
    public Interval create(Interval prototype) {
        return intModel.create(prototype);
    }

    /**
     * @param id
     * @return
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#find(java.util.UUID)
     */
    public Interval find(UUID id) {
        return intModel.find(id);
    }

    /**
     * @return
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#findAll()
     */
    public List<Interval> findAll() {
        return intModel.findAll();
    }

    /**
     * @param attribute
     * @param groupingAgency
     * @return
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#getAllowedValues(com.chiralbehaviors.CoRE.attribute.Attribute,
     *      com.chiralbehaviors.CoRE.agency.Agency)
     */
    public <ValueType> List<ValueType> getAllowedValues(Attribute attribute,
                                                        Agency groupingAgency) {
        return intModel.getAllowedValues(attribute, groupingAgency);
    }

    /**
     * @param attribute
     * @param aspect
     * @return
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#getAllowedValues(com.chiralbehaviors.CoRE.attribute.Attribute,
     *      com.chiralbehaviors.CoRE.network.Aspect)
     */
    public <ValueType> List<ValueType> getAllowedValues(Attribute attribute,
                                                        Aspect<Interval> aspect) {
        return intModel.getAllowedValues(attribute, aspect);
    }

    /**
     * @param groupingAgency
     * @return
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#getAttributeAuthorizations(com.chiralbehaviors.CoRE.agency.Agency)
     */
    public List<IntervalAttributeAuthorization> getAttributeAuthorizations(Agency groupingAgency) {
        return intModel.getAttributeAuthorizations(groupingAgency);
    }

    /**
     * @param groupingAgency
     * @param attribute
     * @return
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#getAttributeAuthorizations(com.chiralbehaviors.CoRE.agency.Agency,
     *      com.chiralbehaviors.CoRE.attribute.Attribute)
     */
    public List<IntervalAttributeAuthorization> getAttributeAuthorizations(Agency groupingAgency,
                                                                           Attribute attribute) {
        return intModel.getAttributeAuthorizations(groupingAgency, attribute);
    }

    /**
     * @param aspect
     * @return
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#getAttributeAuthorizations(com.chiralbehaviors.CoRE.network.Aspect)
     */
    public List<IntervalAttributeAuthorization> getAttributeAuthorizations(Aspect<Interval> aspect) {
        return intModel.getAttributeAuthorizations(aspect);
    }

    /**
     * @param aspect
     * @param attribute
     * @return
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#getAttributeAuthorizations(com.chiralbehaviors.CoRE.network.Aspect,
     *      com.chiralbehaviors.CoRE.attribute.Attribute)
     */
    public List<IntervalAttributeAuthorization> getAttributeAuthorizations(Aspect<Interval> aspect,
                                                                           Attribute attribute) {
        return intModel.getAttributeAuthorizations(aspect, attribute);
    }

    /**
     * @param ruleform
     * @param groupingAgency
     * @return
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#getAttributesClassifiedBy(com.chiralbehaviors.CoRE.ExistentialRuleform,
     *      com.chiralbehaviors.CoRE.agency.Agency)
     */
    public List<IntervalAttribute> getAttributesClassifiedBy(Interval ruleform,
                                                             Agency groupingAgency) {
        return intModel.getAttributesClassifiedBy(ruleform, groupingAgency);
    }

    /**
     * @param ruleform
     * @param aspect
     * @return
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#getAttributesClassifiedBy(com.chiralbehaviors.CoRE.ExistentialRuleform,
     *      com.chiralbehaviors.CoRE.network.Aspect)
     */
    public List<IntervalAttribute> getAttributesClassifiedBy(Interval ruleform,
                                                             Aspect<Interval> aspect) {
        return intModel.getAttributesClassifiedBy(ruleform, aspect);
    }

    /**
     * @param ruleform
     * @param groupingAgency
     * @return
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#getAttributesGroupedBy(com.chiralbehaviors.CoRE.ExistentialRuleform,
     *      com.chiralbehaviors.CoRE.agency.Agency)
     */
    public List<IntervalAttribute> getAttributesGroupedBy(Interval ruleform,
                                                          Agency groupingAgency) {
        return intModel.getAttributesGroupedBy(ruleform, groupingAgency);
    }

    /**
     * @param parent
     * @param relationship
     * @return
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#getChild(com.chiralbehaviors.CoRE.ExistentialRuleform,
     *      com.chiralbehaviors.CoRE.network.Relationship)
     */
    public Interval getChild(Interval parent, Relationship relationship) {
        return intModel.getChild(parent, relationship);
    }

    /**
     * @param parent
     * @param relationship
     * @return
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#getChildren(com.chiralbehaviors.CoRE.ExistentialRuleform,
     *      com.chiralbehaviors.CoRE.network.Relationship)
     */
    public List<Interval> getChildren(Interval parent, Relationship relationship) {
        return intModel.getChildren(parent, relationship);
    }

    /**
     * @param ruleform
     * @param aspect
     * @return
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#getFacet(com.chiralbehaviors.CoRE.ExistentialRuleform,
     *      com.chiralbehaviors.CoRE.network.Aspect)
     */
    public Facet<Interval, IntervalAttribute> getFacet(Interval ruleform,
                                                       Aspect<Interval> aspect) {
        return intModel.getFacet(ruleform, aspect);
    }

    /**
     * @param parent
     * @param relationship
     * @return
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#getImmediateChild(com.chiralbehaviors.CoRE.ExistentialRuleform,
     *      com.chiralbehaviors.CoRE.network.Relationship)
     */
    public Interval getImmediateChild(Interval parent, Relationship relationship) {
        return intModel.getImmediateChild(parent, relationship);
    }

    /**
     * @param parent
     * @param relationship
     * @return
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#getImmediateChildren(com.chiralbehaviors.CoRE.ExistentialRuleform,
     *      com.chiralbehaviors.CoRE.network.Relationship)
     */
    public List<Interval> getImmediateChildren(Interval parent,
                                               Relationship relationship) {
        return intModel.getImmediateChildren(parent, relationship);
    }

    /**
     * @param parent
     * @return
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#getImmediateNetworkEdges(com.chiralbehaviors.CoRE.ExistentialRuleform)
     */
    public Collection<IntervalNetwork> getImmediateNetworkEdges(Interval parent) {
        return intModel.getImmediateNetworkEdges(parent);
    }

    /**
     * @param parent
     * @return
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#getImmediateRelationships(com.chiralbehaviors.CoRE.ExistentialRuleform)
     */
    public Collection<Relationship> getImmediateRelationships(Interval parent) {
        return intModel.getImmediateRelationships(parent);
    }

    /**
     * @param parent
     * @param relationship
     * @return
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#getInGroup(com.chiralbehaviors.CoRE.ExistentialRuleform,
     *      com.chiralbehaviors.CoRE.network.Relationship)
     */
    public List<Interval> getInGroup(Interval parent, Relationship relationship) {
        return intModel.getInGroup(parent, relationship);
    }

    /**
     * @param parents
     * @param relationships
     * @param children
     * @return
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#getInterconnections(java.util.Collection,
     *      java.util.Collection, java.util.Collection)
     */
    public List<IntervalNetwork> getInterconnections(Collection<Interval> parents,
                                                     Collection<Relationship> relationships,
                                                     Collection<Interval> children) {
        return intModel.getInterconnections(parents, relationships, children);
    }

    /**
     * @param parent
     * @param relationship
     * @return
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#getNotInGroup(com.chiralbehaviors.CoRE.ExistentialRuleform,
     *      com.chiralbehaviors.CoRE.network.Relationship)
     */
    public List<Interval> getNotInGroup(Interval parent,
                                        Relationship relationship) {
        return intModel.getNotInGroup(parent, relationship);
    }

    /**
     * @param parent
     * @return
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#getTransitiveRelationships(com.chiralbehaviors.CoRE.ExistentialRuleform)
     */
    public Collection<Relationship> getTransitiveRelationships(Interval parent) {
        return intModel.getTransitiveRelationships(parent);
    }

    /**
     * @param parent
     * @param relationship
     * @param child
     * @return
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#isAccessible(com.chiralbehaviors.CoRE.ExistentialRuleform,
     *      com.chiralbehaviors.CoRE.network.Relationship,
     *      com.chiralbehaviors.CoRE.ExistentialRuleform)
     */
    public boolean isAccessible(Interval parent, Relationship relationship,
                                Interval child) {
        return intModel.isAccessible(parent, relationship, child);
    }

    /**
     * @param parent
     * @param r
     * @param child
     * @param updatedBy
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#link(com.chiralbehaviors.CoRE.ExistentialRuleform,
     *      com.chiralbehaviors.CoRE.network.Relationship,
     *      com.chiralbehaviors.CoRE.ExistentialRuleform,
     *      com.chiralbehaviors.CoRE.agency.Agency)
     */
    public void link(Interval parent, Relationship r, Interval child,
                     Agency updatedBy) {
        intModel.link(parent, r, child, updatedBy);
    }

    /**
     * 
     * @see com.chiralbehaviors.CoRE.meta.NetworkedModel#propagate()
     */
    public void propagate() {
        intModel.propagate();
    }

}
