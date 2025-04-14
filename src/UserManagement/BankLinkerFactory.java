package UserManagement;

public class BankLinkerFactory {
    public static BankLinker getBankLinker(String bankName) {
        switch (bankName.toLowerCase()) {
            case "chase":
                return new ChaseBankLinker();
            default:
                return (accountNumber, routingNumber) -> "Linked account for custom bank: " + bankName;
        }
    }
}

