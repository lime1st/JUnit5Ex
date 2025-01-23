package architecture.rule;

import org.junit.Rule;
import org.junit.Test;

public class CustomRuleTester {

    @Rule
    public CustomRule myRule = new CustomRule();

    /*
    * @Rule 애너테이션은 public 에만 사용 가능하므로 아래와 같이 변경할 수 있다.
    *
    * private CustomRule myRule = new CustomRule();
    *
    * @Rule
    * public CustomRule getMyRule() {
    *   return myRule;
    * }
    * */

    @Test
    public void myCustomRuleTest() {
        System.out.println("Call of a test method");
    }
}
