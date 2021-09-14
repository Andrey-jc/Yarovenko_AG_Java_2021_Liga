package ru.digitalleague.factory.ok.notification;


import ru.digitalleague.factory.ok.User;

public class MailNotification implements Notification {

    private String body;
    private User user;
    private boolean hasAdvertisement;

    public MailNotification(String body, User user, boolean hasAdvertisement) {
        this.body = body;
        this.user = user;
        this.hasAdvertisement = hasAdvertisement;
    }

    public User getUser() {
        return user;
    }


    public boolean isHasAdvertisement() {
        return hasAdvertisement;
    }

    public  String getText() {
        return "\"%s: %s\n%s,%s.\n%s%s\n%s\"";
    }
}
