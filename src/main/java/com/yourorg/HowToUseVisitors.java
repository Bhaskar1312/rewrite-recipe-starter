package com.yourorg;

import org.openrewrite.ExecutionContext;
import org.openrewrite.Recipe;
import org.openrewrite.TreeVisitor;
import org.openrewrite.java.JavaIsoVisitor;

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

        };
    }
}
