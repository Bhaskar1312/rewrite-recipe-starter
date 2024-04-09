package com.yourorg;

import org.openrewrite.Cursor;
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
                J.MethodDeclaration m = super.visitMethodDeclaration(method, executionContext);
                // if(method.getSimpleName().equals("test") && method.getMethodType() != null) {
                if(getCursor().getMessage("sum", 0) == 42) {
                    return m.withName(m.getName().withSimpleName("theAnswerToLifeUniverseAndEverything"))
                    .withMethodType(m.getMethodType().withName("theAnswerToLifeUniverseAndEverything"));
                }

                return m;
            }

            // visitLiteral, intercepts literals 21+21

            @Override
            public J.Literal visitLiteral(J.Literal literal, ExecutionContext executionContext) {
                Object value = literal.getValue();
                if(value instanceof Integer) {
                    Cursor cursor = getCursor();
                    // Cursor{Literal->Binary->Return->JRightPadded(element=return 21+21, after=Space(comments=<0 comments>, whitespace=''))->Block->MethodDeclaration->JRightPadded(element=MethodDeclaration{Test{name=theAnswerToLifeUniverseAndEverything,return=int,parameters=[]}}, after=Space(comments=<0 comments>, whitespace=''))->Block->ClassDeclaration->CompilationUnit->root}
                    Cursor method = cursor.dropParentUntil(J.MethodDeclaration.class::isInstance);
                    Integer sum = method.getMessage("sum", 0);
                    method.putMessage("sum", sum+(Integer)value);
                }



                return super.visitLiteral(literal, executionContext);
            }


            // @Override // top level root LST element, place breakpoint and see cu
            // public J.CompilationUnit visitCompilationUnit(J.CompilationUnit cu, ExecutionContext executionContext) {
            //     // look at statements, at 0, element , u see MethodDeclaration
            //     return super.visitCompilationUnit(cu, executionContext);
            // }
        };
    }
}
