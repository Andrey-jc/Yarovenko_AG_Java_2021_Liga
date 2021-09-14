package ru.digitalleague.factory.ok.notification.decorators;

import ru.digitalleague.factory.ok.notification.Notification;
import ru.digitalleague.factory.ok.notification.PhoneNotification;

public class TranslatePhoneNotification implements Notification {

    private final PhoneNotification COMPONENT;
    private String body;
    private String phoneNumber;

    public TranslatePhoneNotification(PhoneNotification COMPONENT) {
        this.COMPONENT = COMPONENT;
    }

    private String localUser(String number) {
        phoneNumber = COMPONENT.getUser().getPhone();
        String text;
        String codeCountry = number.substring(1, 2);
        switch (codeCountry) {
            case "7":
                text = COMPONENT.getText();
                break;
            case "8":
                text = LanguageNotification.CHINESE.getPhoneWord();
                body = LanguageNotification.CHINESE.getBody();
                break;
            case "9":
                text = LanguageNotification.UAE.getPhoneWord();
                body = LanguageNotification.UAE.getBody();
                // отразил номер телефона предполагая что они все пишут справа на лево
                phoneNumber = new StringBuilder(phoneNumber.substring(1, 12)).reverse().toString();

                break;
            case "4":
                text = LanguageNotification.GERMAN.getPhoneWord();
                body = LanguageNotification.GERMAN.getBody();
                break;
            case "3":
                codeCountry = number.substring(1, 3);
                switch (codeCountry) {
                    case "34":
                        text = LanguageNotification.SPAIN.getPhoneWord();
                        body = LanguageNotification.SPAIN.getBody();
                        break;
                    case "33":
                        text = LanguageNotification.FRENCH.getPhoneWord();
                        body = LanguageNotification.FRENCH.getBody();
                        break;
                    case "35":
                        text = LanguageNotification.PORTUGAL.getPhoneWord();
                        body = LanguageNotification.PORTUGAL.getBody();
                        break;
                    default:
                        text = LanguageNotification.ENGLISH.getPhoneWord();
                        body = LanguageNotification.ENGLISH.getBody();
                        break;
                }
                break;
            default:
                text = LanguageNotification.ENGLISH.getPhoneWord();
                body = LanguageNotification.ENGLISH.getBody();
        }
        return text;
    }

    @Override
    public String getText() {
        COMPONENT.getText();
        return String.format(localUser(COMPONENT.getUser().getPhone()),
                phoneNumber,
                body
                );
    }

}
