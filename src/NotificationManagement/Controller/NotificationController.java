package NotificationManagement.Controller;

import NotificationManagement.Model.NotificationModel;
import NotificationManagement.View.NotificationView;

public class NotificationController {
    private NotificationModel model;
    private NotificationView view;

    /**
     * this class is the controller of notifications
     */

    public NotificationController() {
        model = new NotificationModel();
        view = new NotificationView();
    }

    /**
     *
     * @param id is the id of the notification and the type of notification that is being sent
     * @param content is the content of the notification
     */

    public void sendNotification(int id, String content) {
        System.out.println("Stub: Sending notification with ID " + id);
        model.setId(id);
        model.setContent(content);
    }

    /**
     *
     */

    public void displayNotification() {
        System.out.println("Stub: Displaying notification: " + model.getContent());
    }
}

