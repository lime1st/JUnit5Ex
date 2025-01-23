package stub;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * The first stub-skeleton of a test.
 *
 * @version $Id$
 */
public class TestWebClientSkeleton {

    @BeforeAll
    public static void setUp() {
        // Start Jetty and configure it to return "It works" when
        // the http://localhost:8081/testGetContentOk URL is
        // called.
    }

    @AfterAll
    public static void tearDown() {
        // Stop Jetty.
    }

    @Test
    @Disabled(value = "단순한 테스트 스켈레톤이므로 현재 이 테스트를 실행하면 실패한다")
    public void testGetContentOk() throws MalformedURLException {
        WebClient client = new WebClient();
        String workingContent = client.getContent(new URL("http://localhost:8081/testGetContentOk"));

        assertEquals("It works", workingContent);
    }
}