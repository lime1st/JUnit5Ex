package architecture.runner;

import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CustomTestRunner extends Runner {

    //  테스트 대상 클래스에 대한 참조를 갖기 위한 멤버 변수 선언
    private final Class<?> testedClass;

    public CustomTestRunner(Class<?> testedClass) {
        this.testedClass = testedClass;
    }

    @Override
    public Description getDescription() {
        return Description.createTestDescription(testedClass,
                this.getClass().getSimpleName() + " description");
    }

    @Override
    public void run(RunNotifier notifier) {
        System.out.println("Running tests with " +
                this.getClass().getSimpleName() + ": " + testedClass);

        try {
            Object testObject = testedClass.newInstance();
            for (Method method : testedClass.getMethods()) {//  testedClass 의 모든 public 메서드를 조회
                if (method.isAnnotationPresent(Test.class)) {// @Test 애너테이션(Test.class)이 달린 메서드를 찾는다.
                    notifier.fireTestStarted(Description    //  fireTestStarted: 리스너에게 원자적 테스트가 곧 시작됨을 알림
                            .createTestDescription(testedClass, method.getName()));
                    method.invoke(testObject);  //  리플렉션을 활용해 @Test 에너테이션이 달린 메서드 호출
                    notifier.fireTestFinished(Description   //  fireTestFinished: 리스너에게 원자적 테스느가 완료됨을 알림
                            .createTestDescription(testedClass, method.getName()));
                }
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
