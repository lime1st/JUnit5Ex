package mock.account;

import java.util.HashMap;
import java.util.Map;

/**
 * 모의 객체: AccountManager interface 구현
 */
class MockAccountManager implements AccountManager {

    /**
     * A Map to hold all the <userId, account> values.
     */
    private final Map<String, Account> accounts = new HashMap<>();

    /**
     * A method to add an account to the manager.
     *
     * @param userId
     * @param account
     */
    public void addAccount(String userId, Account account) {
        this.accounts.put(userId, account);
    }

    /**
     * A method to find an account for the user with the given ID.
     */
    @Override
    public Account findAccountForUser(String userId) {
        return this.accounts.get(userId);
    }

    /**
     * A method to update the given account. Notice that we don't need this method and that's why we leave it with a
     * blank implementation.
     */
    @Override
    public void updateAccount(Account account) {
        // do noting;
        // 목 객체에는 비즈니스 로직이 없다.
    }
}