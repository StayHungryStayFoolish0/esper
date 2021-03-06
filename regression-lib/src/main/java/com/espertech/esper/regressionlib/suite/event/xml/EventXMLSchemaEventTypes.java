/*
 ***************************************************************************************
 *  Copyright (C) 2006 EsperTech, Inc. All rights reserved.                            *
 *  http://www.espertech.com/esper                                                     *
 *  http://www.espertech.com                                                           *
 *  ---------------------------------------------------------------------------------- *
 *  The software in this package is published under the terms of the GPL license       *
 *  a copy of which has been included with this distribution in the license.txt file.  *
 ***************************************************************************************
 */
package com.espertech.esper.regressionlib.suite.event.xml;

import com.espertech.esper.common.client.EventPropertyDescriptor;
import com.espertech.esper.common.client.EventType;
import com.espertech.esper.common.internal.support.SupportEventTypeAssertionUtil;
import com.espertech.esper.regressionlib.framework.RegressionEnvironment;
import com.espertech.esper.regressionlib.framework.RegressionExecution;

import static org.junit.Assert.assertEquals;

public class EventXMLSchemaEventTypes implements RegressionExecution {

    public void run(RegressionEnvironment env) {
        String stmtSelectWild = "@name('s0') select * from TestTypesEvent";
        env.compileDeploy(stmtSelectWild).addListener("s0");
        EventType type = env.statement("s0").getEventType();
        SupportEventTypeAssertionUtil.assertConsistency(type);

        Object[][] types = new Object[][]{
            {"attrNonPositiveInteger", Integer.class},
            {"attrNonNegativeInteger", Integer.class},
            {"attrNegativeInteger", Integer.class},
            {"attrPositiveInteger", Integer.class},
            {"attrLong", Long.class},
            {"attrUnsignedLong", Long.class},
            {"attrInt", Integer.class},
            {"attrUnsignedInt", Integer.class},
            {"attrDecimal", Double.class},
            {"attrInteger", Integer.class},
            {"attrFloat", Float.class},
            {"attrDouble", Double.class},
            {"attrString", String.class},
            {"attrShort", Short.class},
            {"attrUnsignedShort", Short.class},
            {"attrByte", Byte.class},
            {"attrUnsignedByte", Byte.class},
            {"attrBoolean", Boolean.class},
            {"attrDateTime", String.class},
            {"attrDate", String.class},
            {"attrTime", String.class}};

        for (int i = 0; i < types.length; i++) {
            String name = types[i][0].toString();
            EventPropertyDescriptor desc = type.getPropertyDescriptor(name);
            Class expected = (Class) types[i][1];
            assertEquals("Failed for " + name, expected, desc.getPropertyType());
        }

        env.undeployAll();
    }
}
