package gui.htmlunit;

import com.gargoylesoftware.htmlunit.CollectingAlertHandler;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.MockWebConnection;
import com.gargoylesoftware.htmlunit.WebAssert;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class WindowConfirmTest extends ManagedWebClient {

    @Test
    @DisplayName("컨펌 메시지 검증")
    public void testWindowConfirm() throws FailingHttpStatusCodeException, IOException {
        String html = "<html><head><title>Hello</title></head><body onload='confirm(\"Confirm Message\")'></body></html>";
        URL testUrl = new URL("http://Page1/");
        MockWebConnection mockConnection = new MockWebConnection();
        final List<String> confirmMessages = new ArrayList<String>();
        // 테스트 셋업
        webClient.setConfirmHandler((page, message) -> {
            confirmMessages.add(message);
            return true;
        });
        mockConnection.setResponse(testUrl, html);
        webClient.setWebConnection(mockConnection);
        // 태스트 진행
        HtmlPage firstPage = webClient.getPage(testUrl);
        WebAssert.assertTitleEquals(firstPage, "Hello");
        assertArrayEquals(new String[]{"Confirm Message"}, confirmMessages.toArray());
    }

    @Test
    @DisplayName("컨펌 메시지와 경고 메시지 검증")
    public void testWindowConfirmAndAlert() throws FailingHttpStatusCodeException, IOException {
        String html = "<html><head><title>Hello</title><script>function go(){alert(confirm('Confirm Message'))}</script>\n"
                + "</head><body onload='go()'></body></html>";
        URL testUrl = new URL("http://Page1/");
        MockWebConnection mockConnection = new MockWebConnection();
        final List<String> confirmMessages = new ArrayList<String>();
        // 테스트 셋업
        webClient.setAlertHandler(new CollectingAlertHandler());
        webClient.setConfirmHandler((page, message) -> {
            confirmMessages.add(message);
            return true;
        });
        mockConnection.setResponse(testUrl, html);
        webClient.setWebConnection(mockConnection);
        // 테스트 진행
        HtmlPage firstPage = webClient.getPage(testUrl);
        WebAssert.assertTitleEquals(firstPage, "Hello");
        assertArrayEquals(new String[]{"Confirm Message"}, confirmMessages.toArray());
        assertArrayEquals(new String[]{"true"}, ((CollectingAlertHandler) webClient.getAlertHandler()).getCollectedAlerts().toArray());
    }
}
