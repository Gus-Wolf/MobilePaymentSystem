package UserManagement;

public class ChaseBankLinker implements BankLinker {
    @Override
    public String linkAccount(String accountNumber, String routingNumber) {
        // Simulated logic
        return "Chase account ending in " + accountNumber.substring(accountNumber.length() - 4) + " linked!";
    }
}
