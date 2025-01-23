package gui.selenium.browse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MultiBrowserSeleniumTest {

    private WebDriver driver;

    // 파라미터로 주입하기 위한 소스 메서드를 설정
    public static Collection<WebDriver> getBrowserVersions() {
        return Arrays.asList(new WebDriver[]{new ChromeDriver()
//                , new SafariDriver()  // 사파리 에러남;;;
        });
    }

    // 파라미터를 사용한 테스트
    @ParameterizedTest
    @MethodSource("getBrowserVersions") // 파라미터로 메서드를 사용한다.
    void testManningAccess(WebDriver driver) {
        this.driver = driver;
        driver.get("https://www.manning.com");
        assertThat(driver.getTitle(), is("Manning"));
    }

    @ParameterizedTest
    @MethodSource("getBrowserVersions")
    void testGoogleAccess(WebDriver driver) {
        this.driver = driver;
        driver.get("https://www.google.com");
        assertThat(driver.getTitle(), is("Google"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

}
