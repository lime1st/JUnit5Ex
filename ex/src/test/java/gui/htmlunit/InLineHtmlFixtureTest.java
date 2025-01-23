package gui.htmlunit;

import com.gargoylesoftware.htmlunit.MockWebConnection;
import com.gargoylesoftware.htmlunit.WebAssert;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

/**
 * Demonstrates using in-line HTML fixtures in test methods.
 */
public class InLineHtmlFixtureTest extends ManagedWebClient {

    @Test
    @DisplayName("독립 실행형 테스트 구성")
    public void testInLineHtmlFixture() throws IOException {
        //  응답을 미리 생성
        final String expectedTitle = "Hello 1!";
        String html = "<html><head><title>" + expectedTitle + "</title></head></html>";

        // 모의 객체를 만들고 모의 http 연결을 위한 디폴트 응답 html 페이지 설정
        MockWebConnection connection = new MockWebConnection();
        connection.setDefaultResponse(html);

        // webclient 에 모의 http 연결을 설정한다.
        webClient.setWebConnection(connection);

        // webclient 에서 임시 페이지를 호출한다. 미리 설정한(위에서) 응답을 반환하므로 어떤 url 을 사용해도 상관 없다.
        HtmlPage page = webClient.getPage("http://page");
        WebAssert.assertTitleEquals(page, expectedTitle);
    }

    @Test
    @DisplayName("여러 페이지 테스트")
    public void testInLineHtmlFixtures() throws IOException {
        // url 의 마지막에 반드시 /를 넣어야 한다. 아니면 예외 발생 No response specified that can handle URL
        final URL page1Url = new URL("http://Page1/");
        final URL page2Url = new URL("http://Page2/");
        final URL page3Url = new URL("http://Page3/");

        MockWebConnection connection = new MockWebConnection();
        connection.setResponse(page1Url, "<html><head><title>Hello 1!</title></head></html>");
        connection.setResponse(page2Url, "<html><head><title>Hello 2!</title></head></html>");
        connection.setResponse(page3Url, "<html><head><title>Hello 3!</title></head></html>");
        webClient.setWebConnection(connection);

        HtmlPage page1 = webClient.getPage(page1Url);
        WebAssert.assertTitleEquals(page1, "Hello 1!");

        HtmlPage page2 = webClient.getPage(page2Url);
        WebAssert.assertTitleEquals(page2, "Hello 2!");

        HtmlPage page3 = webClient.getPage(page3Url);
        WebAssert.assertTitleEquals(page3, "Hello 3!");
    }
}
