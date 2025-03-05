package NotificationManagement.Controller;

import NotificationManagement.Model.NotificationModel;
import NotificationManagement.View.NotificationView;

public class NotificationController {
    private NotificationModel model;
    private NotificationView view;

    public NotificationController() {
        model = new NotificationModel();
        view = new NotificationView();
    }

    public void sendNotification(int id, String content) {
        System.out.println("Stub: Sending notification with ID " + id);
        model.setId(id);
        model.setContent(content);
    }

    public void displayNotification() {
        System.out.println("Stub: Displaying notification: " + model.getContent());
    }
}

