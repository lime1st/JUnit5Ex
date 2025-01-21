package mock.web;

import java.io.InputStream;

public class WebClient2 {

    public String getContent(ConnectionFactory connectionFactory) {
        String workingContent;

        StringBuilder content = new StringBuilder();
        try (InputStream is = connectionFactory.getData()) {
            int count;
            while (-1 != (count = is.read())) {
                content.append(new String(Character.toChars(count)));
            }

            workingContent = content.toString();
        } catch (Exception e) {
            workingContent = null;
        }

        return workingContent;
    }
}
