package basic.assumptions;

import basic.assumptions.environment.JavaSpecification;
import basic.assumptions.environment.OperationSystem;
import basic.assumptions.environment.TestsEnvironment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class AssumptionsTest {
    private static final String EXPECTED_JAVA_VERSION = "1.8";
    private final TestsEnvironment environment = new TestsEnvironment(
            new JavaSpecification(System.getProperty("java.vm.specification.version")),
            new OperationSystem(System.getProperty("os.name"), System.getProperty("os.arch"))
    );

    private final SUT systemUnderTest = new SUT();

    @BeforeEach
    void setUp() {
        //  현재 OS 환경이 윈도우라는 가정을 만족하지 않으면 테스트가 실행되지 않는다.
        assumeTrue(environment.isWindows());
    }

    @Test
    void testNoJobToRun() {
        assumingThat(
                () -> environment.getJavaVersion().equals(EXPECTED_JAVA_VERSION),
                () -> assertFalse(systemUnderTest.hasJobToRun()));
    }

    @Test
    void testJobToRun() {
        assumeTrue(environment.isAmd64Architecture());

        systemUnderTest.run(new Job());

        //  assumeTrue 를 통과하지 않으면 실행되지 않는다.
        assertTrue(systemUnderTest.hasJobToRun());
    }
}
