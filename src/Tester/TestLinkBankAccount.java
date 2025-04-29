package Tester;

import UserManagement.BankLinker;
import UserManagement.BankLinkerFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestLinkBankAccount {

    @Test
    public void testChaseBankLinking() {
        BankLinker linker = BankLinkerFactory.getBankLinker("Chase");
        String result = linker.linkAccount("1234567890", "987654321");

        assertEquals("Chase account ending in 7890 linked!", result);
    }

    @Test
    public void testCustomBankLinking() {
        BankLinker linker = BankLinkerFactory.getBankLinker("MyLocalBank");
        String result = linker.linkAccount("111122223333", "000011112");

        assertEquals("Linked account for custom bank: MyLocalBank", result);
    }
}
