package mock.web;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

class TestWebClientMock {

    @Test
    public void testGetContentOk() throws Exception {
        MockHttpURLConnection mockConnection = new MockHttpURLConnection();
        mockConnection.setExpectedInputStream(new ByteArrayInputStream("It works".getBytes()));

    }

}