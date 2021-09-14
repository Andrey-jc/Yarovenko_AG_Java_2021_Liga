package ru.digitalleague.factory.ok;

import ru.digitalleague.factory.ok.notification.Notification;
import ru.digitalleague.factory.ok.notification.factory.MailNotificationFactory;
import ru.digitalleague.factory.ok.notification.factory.NotificationFactory;
import ru.digitalleague.factory.ok.notification.factory.PhoneNotificationFactory;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<User> listUser = new LinkedList<>();
        listUser.add(new User(22L, "Денис", "denis@gmail.com", "+79522668105")); // ru
        listUser.add(new User(2L, "光辉", "radiant@gmail.com", "+89522668105")); // chi
        listUser.add(new User(44L, "عزيز", "asis@gmail.com", "+99522668105")); // uae
        listUser.add(new User(26L, "Wolfgang", "wolfgang@gmail.com", "+49522668105")); // ger
        listUser.add(new User(52L, "Amelio", "amelio@gmail.com", "+34522668105")); // spain
        listUser.add(new User(6L, "Michel", "michel@gmail.com", "+33522668105")); // french
        listUser.add(new User(70L, "Adilson", "adilson@gmail.com", "+35522668105")); // port
        listUser.add(new User(8L, "Chris", "chris_wilson@gmail.com", "+66522668105")); // usa (hasAdvertisement true)
        listUser.add(new User(9L, "Reinier", "reinier@gmail.com", "+53522668105")); // cuba (hasAdvertisement false)

        NotificationFactory factoryMailNotification = new MailNotificationFactory();
        NotificationFactory factoryPhoneNotification = new PhoneNotificationFactory();

        System.out.println("==========================================================================");
        System.out.println("Factory Mail Notification\n");
        System.out.println("==========================================================================");

        for (User user: listUser) {
            sendNotification(factoryMailNotification.makeNotification("Доброго дня!", user));
            System.out.println("\n -------------------------------------------------------------------------");
        }

        System.out.println("==========================================================================");
        System.out.println("\nFactory Phone Notification\n");
        System.out.println("==========================================================================");

        for (User user: listUser) {
            sendNotification(factoryPhoneNotification.makeNotification("Доброго дня!", user));
            System.out.println("\n -------------------------------------------------------------------------");
        }


    }

    private static void sendNotification(Notification notification) {
        System.out.println(notification.getText());
    }
}
