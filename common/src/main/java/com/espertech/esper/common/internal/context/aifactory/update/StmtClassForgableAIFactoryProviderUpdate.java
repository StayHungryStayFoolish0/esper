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
package com.espertech.esper.common.internal.context.aifactory.update;

import com.espertech.esper.common.internal.bytecodemodel.base.CodegenClassScope;
import com.espertech.esper.common.internal.bytecodemodel.base.CodegenMethod;
import com.espertech.esper.common.internal.bytecodemodel.base.CodegenMethodScope;
import com.espertech.esper.common.internal.bytecodemodel.base.CodegenPackageScope;
import com.espertech.esper.common.internal.compile.stage3.StmtClassForgableAIFactoryProviderBase;
import com.espertech.esper.common.internal.context.aifactory.core.SAIFFInitializeSymbol;
import com.espertech.esper.common.internal.context.module.EPStatementInitServices;

import static com.espertech.esper.common.internal.bytecodemodel.model.expression.CodegenExpressionBuilder.localMethod;
import static com.espertech.esper.common.internal.context.aifactory.core.SAIFFInitializeSymbol.REF_STMTINITSVC;

public class StmtClassForgableAIFactoryProviderUpdate extends StmtClassForgableAIFactoryProviderBase {
    private final StatementAgentInstanceFactoryUpdateForge forge;

    public StmtClassForgableAIFactoryProviderUpdate(String className, CodegenPackageScope packageScope, StatementAgentInstanceFactoryUpdateForge forge) {
        super(className, packageScope);
        this.forge = forge;
    }

    protected Class typeOfFactory() {
        return StatementAgentInstanceFactoryUpdate.class;
    }

    protected CodegenMethod codegenConstructorInit(CodegenMethodScope parent, CodegenClassScope classScope) {
        SAIFFInitializeSymbol saiffInitializeSymbol = new SAIFFInitializeSymbol();
        CodegenMethod method = parent.makeChildWithScope(typeOfFactory(), this.getClass(), saiffInitializeSymbol, classScope).addParam(EPStatementInitServices.class, REF_STMTINITSVC.getRef());
        method.getBlock().methodReturn(localMethod(forge.initializeCodegen(method, saiffInitializeSymbol, classScope)));
        return method;
    }
}
