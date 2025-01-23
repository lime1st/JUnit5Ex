package gui.htmlunit;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebAssert;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests navigating the HtmlUnit SourceForge site.
 */
public class JavadocPageAllBrowserTest {

    private static Collection<BrowserVersion[]> getBrowserVersions() {
        return Arrays.asList(new BrowserVersion[][]{{BrowserVersion.FIREFOX},
                {BrowserVersion.EDGE}, {BrowserVersion.CHROME},
                {BrowserVersion.BEST_SUPPORTED}});
    }

    @ParameterizedTest
    @MethodSource("getBrowserVersions")
    public void testClassNav(BrowserVersion browserVersion) throws IOException {
        WebClient webClient = new WebClient(browserVersion);

        HtmlPage mainPage = (HtmlPage) webClient.getPage("http://htmlunit.sourceforge.net/apidocs/index.html");
        WebAssert.notNull("Missing main page", mainPage);
        HtmlPage packagePage = (HtmlPage) mainPage.getFrameByName("packageFrame").getEnclosedPage();
        WebAssert.notNull("Missing package page", packagePage);
        HtmlListItem htmlListItem = (HtmlListItem) packagePage.getElementsByTagName("li").item(2);
        assertEquals("AboutURLConnection", htmlListItem.getTextContent());
    }
}
