package gui.selenium.browse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleSearchTest {

    private RemoteWebDriver remoteWebDriver;

    // 파라미터로 주입하기 위한 소스 메서드를 설정
    public static Collection<RemoteWebDriver> getBrowserVersions() {
        return Arrays.asList(new RemoteWebDriver[]{new ChromeDriver()
//                , new SafariDriver()  // 사파리 에러남;;;
        });
    }

    @ParameterizedTest
    @MethodSource("getBrowserVersions")
    void testGoogleSearch(RemoteWebDriver driver) {
        this.remoteWebDriver = driver;
        remoteWebDriver.get("http://www.google.com");
        WebElement element = remoteWebDriver.findElement(By.name("q"));
        element.sendKeys("en.wikipedia.org");
        remoteWebDriver.findElement(By.name("q")).sendKeys(Keys.ENTER);

        // wait until the Google page shows the result
        WebElement myDynamicElement = (new WebDriverWait(remoteWebDriver, Duration.ofSeconds(30)))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("result-stats")));

        List<WebElement> findElements = remoteWebDriver.findElements(By.xpath("//*[@id='rso']//a/h3"));

        findElements.get(0).click();

        assertEquals("https://en.wikipedia.org/wiki/Main_Page", remoteWebDriver.getCurrentUrl());
        assertThat(remoteWebDriver.getTitle(), is("Wikipedia, the free encyclopedia"));

        WebElement contents = remoteWebDriver.findElement(By.linkText("Talk"));
        assertTrue(contents.isDisplayed());
        contents.click();

        assertThat(remoteWebDriver.getTitle(), is("Talk:Main Page - Wikipedia"));
    }

    @AfterEach
    void tearDown() {
        remoteWebDriver.quit();
    }
}
