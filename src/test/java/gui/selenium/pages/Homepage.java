package gui.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Homepage {

    private WebDriver webDriver;

    public Homepage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public LoginPage openFormAuthentication() {
        webDriver.get("https://the-internet.herokuapp.com/");

        // login 하이퍼링크가 있는 곳을 찾아 해당 요소를 클릭한다.
        webDriver.findElement(By.cssSelector("[href=\"/login\"]")).click();

        // 웹드라이버로 새로운 LoginPage 객체를 만들어 반환한다.
        return new LoginPage(webDriver);
    }
}
