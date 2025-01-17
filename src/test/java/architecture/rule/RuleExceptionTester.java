package architecture.rule;

import architecture.Calculator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RuleExceptionTester {

    @Rule   //   public 필드나 인스턴스에만 붙일 수 있다.
    public ExpectedException expectedException = ExpectedException.none();

    private final Calculator calculator = new Calculator();

    @Test
    public void expectIllegalArgumentException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("음수의 제곱근을 구할 수 없다");
        calculator.sqrt(-1);
    }

    @Test
    public void expectArithmeticException() {
        expectedException.expect(ArithmeticException.class);
        expectedException.expectMessage("0으로 나눌 수 없다");
        calculator.divide(1, 0);
    }
}
