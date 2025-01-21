package mock.account;

import mock.configurations.Configuration;
import mock.configurations.DefaultConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Refactored architecture. We now pass the Configuration and
 * Log objects to the constructor and use them for our own logic.
 */
public class DefaultAccountManager2 implements AccountManager {

    /**
     * Logger instance.
     */
    private Log logger;

    /**
     * Configuration to use.
     */
    private Configuration configuration;

    /**
     * Constructor with no parameters.
     */
    public DefaultAccountManager2() {
        this(LogFactory.getLog(DefaultAccountManager2.class), new DefaultConfiguration("technical"));
    }

    /**
     * Constructor with logger and configuration parameters.
     * @param logger
     * @param configuration
     */
    public DefaultAccountManager2(Log logger, Configuration configuration) {
        this.logger = logger;
        this.configuration = configuration;
    }

    /**
     * Finds an account for user with the given userID.
     *
     * @param userId
     */
    @Override
    public Account findAccountForUser(String userId) {
        this.logger.debug("Getting account for user [" + userId + "]");
        this.configuration.getSQL("FIND_ACCOUNT_FOR_USER");

        // JDBC 를 사용하여 유저의 계좌 정보를 가져오는 비즈니스 로직
        return null;
    }

    /**
     * Updates the given account.
     *
     * @param account
     */
    @Override
    public void updateAccount(Account account) {
        //  Perform database access here

    }
}
