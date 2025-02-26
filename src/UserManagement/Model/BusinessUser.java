package UserManagement.Model;

public class BusinessUser extends User {
    private String businessName;
    private String businessAddress;
    private String businessPhone;
    private String businessEmail;
    private String businessWebsite;

    /**
     * Gets the business name.
     *
     * @return the name of the business
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * Sets the business name.
     *
     * @param businessName the name of the business to set
     */
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    /**
     * Gets the business address.
     *
     * @return the address of the business
     */
    public String getBusinessAddress() {
        return businessAddress;
    }

    /**
     * Sets the business address.
     *
     * @param businessAddress the address of the business to set
     */
    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    /**
     * Gets the business phone number.
     *
     * @return the phone number of the business
     */
    public String getBusinessPhone() {
        return businessPhone;
    }

    /**
     * Sets the business phone number.
     *
     * @param businessPhone the phone number of the business to set
     */
    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    /**
     * Gets the business email.
     *
     * @return the email of the business
     */
    public String getBusinessEmail() {
        return businessEmail;
    }

    /**
     * Sets the business email.
     *
     * @param businessEmail the email of the business to set
     */
    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }

    /**
     * Gets the business website.
     *
     * @return the website of the business
     */
    public String getBusinessWebsite() {
        return businessWebsite;
    }

    /**
     * Sets the business website.
     *
     * @param businessWebsite the website of the business to set
     */
    public void setBusinessWebsite(String businessWebsite) {
        this.businessWebsite = businessWebsite;
    }
}
