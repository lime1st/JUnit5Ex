package gui.selenium.browse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChromeSeleniumTest {

    private WebDriver driver;

    private RemoteWebDriver remoteWebDriver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        remoteWebDriver = new ChromeDriver();
    }

    @Test
    void testChromeManning() {
        driver.get("https://www.manning.com/");
        assertThat(driver.getTitle(), is("Manning"));
    }

    @Test
    void testChromeGoogle() {
        driver.get("https://www.google.com/");
        assertThat(driver.getTitle(), is("Google"));
    }

    @Test
    void testWikipediaAccess() {
        remoteWebDriver.get("https://en.wikipedia.org/");
        assertThat(remoteWebDriver.getTitle(), is("Wikipedia, the free encyclopedia"));

        // findElementByLinkText 메서드 deprecated
        WebElement contents = remoteWebDriver.findElement(By.linkText("Talk"));
        assertTrue(contents.isDisplayed());

    }

    @AfterEach
    void tearDown() {
        driver.quit();
        remoteWebDriver.quit();
    }
}