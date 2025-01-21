package mock.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A sample web-client class that opens an HTTP connection to a web-server and reads the response from it.
 *
 * @version $Id$
 */
public class WebClient {
    public String getContent(URL url) {
        StringBuilder content = new StringBuilder();
        try {
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            InputStream is = connection.getInputStream();
            int count;
            while (-1 != (count = is.read())) {
                content.append(new String(Character.toChars(count)));
            }
        } catch (IOException e) {
            return null;
        }
        return content.toString();
    }
}
