package com.yourorg;

import org.openrewrite.ExecutionContext;
import org.openrewrite.Recipe;
import org.openrewrite.TreeVisitor;
import org.openrewrite.java.JavaIsoVisitor;
import org.openrewrite.java.tree.J;

public class HowToUseVisitors extends Recipe {
    @Override
    public String getDisplayName() {
        return "How to use Visitors";
    }

    @Override
    public String getDescription() {
        return "Learn more about OpenRewrite visitors.";
    }

    @Override
    public TreeVisitor<?, ExecutionContext> getVisitor() {
        return new JavaIsoVisitor<>() {
            @Override
            public J.MethodDeclaration visitMethodDeclaration(J.MethodDeclaration method, ExecutionContext executionContext) {
                if(method.getSimpleName().equals("test") && method.getMethodType() != null) {
                    return method.withName(method.getName().withSimpleName("theAnswerToLifeUniverseAndEverything"))
                    .withMethodType(method.getMethodType().withName("theAnswerToLifeUniverseAndEverything"));
                }
                return super.visitMethodDeclaration(method, executionContext);
            }

            // @Override // top level root LST element, place breakpoint and see cu
            // public J.CompilationUnit visitCompilationUnit(J.CompilationUnit cu, ExecutionContext executionContext) {
            //     // look at statements, at 0, element , u see MethodDeclaration
            //     return super.visitCompilationUnit(cu, executionContext);
            // }
        };
    }
}
