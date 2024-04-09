package com.yourorg;

import static org.openrewrite.java.Assertions.java;

import org.junit.jupiter.api.Test;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;

public class HowToUseVisitorsTest implements RewriteTest {

    @Override
    public void defaults(RecipeSpec spec) {
        spec.recipe(new HowToUseVisitors());
    }

    @Test
    void sumAllTheIntegers() {
        //language=java
        rewriteRun(
          java(
            """
                    class Test {
                        int test() {
                            return 21+21;
                        }
                    }
                  """,
            """
                    class Test {
                        void theAnswerToLifeUniverseAndEverything() {
                            return 42;
                        }
                    }
                  """
          )
        );
    }
}