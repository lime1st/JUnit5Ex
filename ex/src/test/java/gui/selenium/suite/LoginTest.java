package gui.selenium.suite;

import gui.selenium.pages.Homepage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.Collection;

public class LoginTest {

    private Homepage homepage;
    private WebDriver webDriver;

    // 파라미터로 주입하기 위한 소스 메서드를 설정
    public static Collection<WebDriver> getBrowserVersions() {
        return Arrays.asList(new WebDriver[]{new ChromeDriver()
//                , new SafariDriver()  // 사파리 에러남;;;
        });
    }

    @ParameterizedTest
    @MethodSource("getBrowserVersions")
    public void loginWithValidCredentials(WebDriver webDriver) {
        this.webDriver = webDriver;
        homepage = new Homepage(webDriver);
        homepage
                .openFormAuthentication()
                .loginWith("tomsmith", "SuperSecretPassword!")
                .thenLoginSuccessful();
    }

    @ParameterizedTest
    @MethodSource("getBrowserVersions")
    public void loginWithInvalidCredentials(WebDriver webDriver) {
        this.webDriver = webDriver;
        homepage = new Homepage(webDriver);
        homepage
                .openFormAuthentication()
                .loginWith("tomsmith", "SuperSecretPassword")
                .thenLoginUnsuccessful();
    }

    @AfterEach
    void tearDown() {
        webDriver.quit();
    }
}
