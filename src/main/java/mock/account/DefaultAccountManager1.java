package mock.account;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Default account manager implementation before refactoring.
 */
public class DefaultAccountManager1 implements AccountManager {

    /**
     * Logger instance.
     */
    private static final Log logger = LogFactory.getLog(DefaultAccountManager1.class);

    /**
     * Finds an account for user with the given userID.
     * @param userId
     * @return
     */
    @Override
    public Account findAccountForUser(String userId) {
        logger.debug("Getting account for user [" + userId + "]");
        ResourceBundle bundle = PropertyResourceBundle.getBundle("technical");
        String sql = bundle.getString("FIND_ACCOUNT_FOR_USER");

        return null;
    }

    /**
     * Updates the given account.
     * @param account
     */
    @Override
    public void updateAccount(Account account) {
        // Perform database access here
    }

    /**
     * 이 클래스의 문제점
     * 1. Log 객체를 클래스 내부에서 생성해 Log 객체를 바꿔서 쓸 수 없다.
     *      -> 테스트를 위해 아무 일도 하지 않는 Log 클래스를 모의하고 싶어도 할 수 없다.
     * 2. PropertyResourceBundle 클래스도 1번과 비슷한 상황이다.
     *      -> 어떤 구현체를 사용할지를 결정하는 것이 이 클래스 설계의 목표가 되어서는 안 된다.
     *
     * : 비즈니스로직과 직접 관계없는 객체는 파라미터로 전달하는 것이 유연한 설계가 된다.
     */
}
