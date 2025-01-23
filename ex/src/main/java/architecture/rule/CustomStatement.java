package architecture.rule;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class CustomStatement extends Statement {

    private final Statement base;
    private final Description description;

    public CustomStatement(Statement base, Description description) {
        this.base = base;
        this.description = description;
    }

    @Override
    public void evaluate() throws Throwable {
        //
        System.out.println(this.getClass().getSimpleName() + " " + description.getMethodName() + " has started");
        try {
            base.evaluate();    //  원래의 테스트를 진행한다.
        } finally {
            System.out.println(this.getClass().getSimpleName() + " " + description.getMethodName() + " has finished");
        }
    }
}