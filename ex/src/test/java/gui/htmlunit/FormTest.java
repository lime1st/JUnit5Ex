package gui.htmlunit;

import com.gargoylesoftware.htmlunit.CollectingAlertHandler;
import com.gargoylesoftware.htmlunit.WebAssert;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FormTest extends ManagedWebClient {

    private static final String FORM_TEST_URL = "file:src/main/java/gui/htmlunit/formtest.html";

    @Test
    void testForm() throws IOException {
        HtmlPage page = webClient.getPage(FORM_TEST_URL);
        HtmlForm form = page.getFormByName("validated_form");
        HtmlTextInput input = form.getInputByName("in_text");
        input.setValueAttribute("typing...");
        HtmlSubmitInput submitButton = form.getInputByName("submit");
        HtmlPage resultPage = submitButton.click();

        WebAssert.assertTitleEquals(resultPage, "Result");
    }

    @Test
    @DisplayName("예상되는 경고 단언")
    public void testFormAlert() throws IOException {
        // 경고 핸들러 설정
        CollectingAlertHandler alertHandler = new CollectingAlertHandler();
        webClient.setAlertHandler(alertHandler);
        //alternative code for the line above:
        // webClient.setAlertHandler((page, message) -> fail("JavaScript alert: " + message));

        HtmlPage page = webClient.getPage(FORM_TEST_URL);
        HtmlForm form = page.getFormByName("validated_form");
        HtmlSubmitInput submitButton = form.getInputByName("submit");

        // 입력없이 submit 클릭: 경고가 발생한다 -> resultPage 에 저장
        HtmlPage resultPage = submitButton.click();
        WebAssert.assertTitleEquals(resultPage, page.getTitleText());
        WebAssert.assertTextPresent(resultPage, page.asNormalizedText());

        // 위에서 설정한 경고 핸들러에 발생한 경고가 저장되어있다.
        List<String> collectedAlerts = alertHandler.getCollectedAlerts();
        List<String> expectedAlerts = Collections.singletonList("Please enter a value."); // 예상 경고 메시지
        assertEquals(expectedAlerts, collectedAlerts);
    }

    @Test
    @DisplayName("자바스크립트 경고가 발생하지 않은 경우 검증")
    public void testFormNoAlert() throws IOException {
        CollectingAlertHandler alertHandler = new CollectingAlertHandler();
        webClient.setAlertHandler(alertHandler);
        HtmlPage page = webClient.getPage(FORM_TEST_URL);
        HtmlForm form = page.getFormByName("validated_form");
        HtmlTextInput input = form.getInputByName("in_text");
        input.setValueAttribute("typing...");
        HtmlSubmitInput submitButton = form.getInputByName("submit");
        HtmlPage resultPage = submitButton.click();
        WebAssert.assertTitleEquals(resultPage, "Result");
        assertTrue(alertHandler.getCollectedAlerts().isEmpty(), "No alerts expected");
    }
}
