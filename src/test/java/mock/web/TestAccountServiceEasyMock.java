package mock.web;

import mock.account.Account;
import mock.account.AccountManager;
import mock.account.AccountService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAccountServiceEasyMock {

    private AccountManager mockAccountManager;

    @BeforeEach
    public void setUp() {
        //  EasyMock 프레임워크는 인터페이스만 모의할 수 있다.
        mockAccountManager = createMock("mockAccountManager", AccountManager.class);
    }

    @Test
    public void testTransferOk() {
        Account senderAccount = new Account("1", 200);
        Account beneficiaryAccount = new Account("2", 100);

        //  기대를 정의한다.
        //  메서드 반환 타입이 void 인 경우 모의 객체에서 간단하게 호출할 수 있다.
        mockAccountManager.updateAccount(senderAccount);
        mockAccountManager.updateAccount(beneficiaryAccount);

        //  메서드가 어떤 종류든 객체를 반환할 때 expect 나 andReturn 메서드를 사용한다.
        expect(mockAccountManager.findAccountForUser("1"))
                .andReturn(senderAccount);
        expect(mockAccountManager.findAccountForUser("2"))
                .andReturn(beneficiaryAccount);

        //  기대 정의가 끝나면 replay 를 호출한다.
        //  replay 메서드를 호출하면 모의 객체의 행동을 기록하는 단계에서 모의 객체의 동작을 활성화하는 단계로 넘어간다.
        //  단순히 모의 객체의 행동을 기록하는 것만으로는 모의객체가 동작하지 않는다.
        //  replay 메서드를 호출해야 모의 객체가 기대한 대로 동작한다.
        replay(mockAccountManager);

        AccountService accountService = new AccountService();
        accountService.setAccountManager(mockAccountManager);
        accountService.transfer("1", "2", 50);

        assertEquals(150, senderAccount.getBalance());
        assertEquals(150, beneficiaryAccount.getBalance());
    }

    @AfterEach
    public void tearDown() {
        //  기대에 대한 검증
        //  EasyMock 을 사용하면 어떤 모의 객체든 verify 메서드를 호출하여 이전에 선언했던 메서드 호출에 대한 기대가 충족되었는지 검증할 수 있다.
        verify(mockAccountManager);
    }
}
