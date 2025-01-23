package mock.account;

import mock.configurations.MockConfiguration;
import org.junit.jupiter.api.Test;

public class TestDefaultAccountManager {

    @Test
    public void testFindAccountByUser() {
        MockLog logger = new MockLog();
        MockConfiguration configuration = new MockConfiguration();
        configuration.setSQL("SELECT * ...");
        DefaultAccountManager2 am = new DefaultAccountManager2(logger, configuration);

        @SuppressWarnings("unused")
        Account account = am.findAccountForUser("1234");

        //  Perform asserts here
    }
}
