package TransactionMangement.Model;

/**
 * Represents a financial transaction between two users.
 */
public class TransactionManagementModel {

    /**
     * Unique identifier for the transaction.
     */
    private int transactionId;

    /**
     * The username of the user initiating the transaction.
     */
    private String userFrom;

    /**
     * The username of the user receiving the transaction.
     */
    private String userTo;

    /**
     * The amount of money being transferred in the transaction.
     */
    private double amount;

    /**
     * The timestamp of when the transaction occurred.
     */
    private String timestamp;

    /**
     * Default constructor for creating an empty TransactionManagementModel object.
     */
    public TransactionManagementModel() {
    }

    /**
     * Constructs a TransactionManagementModel object with specified details.
     *
     * @param transactionId the unique identifier of the transaction
     * @param userFrom      the username of the user initiating the transaction
     * @param userTo        the username of the user receiving the transaction
     * @param amount        the amount of money being transferred
     * @param timestamp     the timestamp of when the transaction occurred
     */
    public TransactionManagementModel(int transactionId, String userFrom, String userTo, double amount, String timestamp) {
        this.transactionId = transactionId;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    /**
     * Gets the unique identifier of the transaction.
     *
     * @return the transaction ID
     */
    public int getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the unique identifier of the transaction.
     *
     * @param transactionId the transaction ID to set
     */
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Gets the username of the user initiating the transaction.
     *
     * @return the username of the sender
     */
    public String getUserFrom() {
        return userFrom;
    }

    /**
     * Sets the username of the user initiating the transaction.
     *
     * @param userFrom the username of the sender to set
     */
    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    /**
     * Gets the username of the user receiving the transaction.
     *
     * @return the username of the receiver
     */
    public String getUserTo() {
        return userTo;
    }

    /**
     * Sets the username of the user receiving the transaction.
     *
     * @param userTo the username of the receiver to set
     */
    public void setUserTo(String userTo) {
        this.userTo = userTo;
    }

    /**
     * Gets the amount of money being transferred in the transaction.
     *
     * @return the transaction amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the amount of money being transferred in the transaction.
     *
     * @param amount the transaction amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Gets the timestamp of when the transaction occurred.
     *
     * @return the timestamp as a string
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp of when the transaction occurred.
     *
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
