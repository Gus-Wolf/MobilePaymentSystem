package NotificationManagement.Model;


public class NotificationModel {

    /**
     * This is the model for the class for notification, this is how the notification is modeled
     */

    private int id;
    private String content;

    public NotificationModel() {
    }

    /**
     *
     * @param id is the type of notification
     * @param content is the stuff inside the notifcation that is being sent
     */

    public NotificationModel(int id, String content) {
        this.id = id;
        this.content = content;
    }

    /**
     *
     * @return id
     */

    public int getId() {
        return id;
    }

    /**
     *
     * @param id sets the id
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return content
     */

    public String getContent() {
        return content;
    }

    /**
     *
     * @param content sets the string of the message
     */

    public void setContent(String content) {
        this.content = content;
    }
}
