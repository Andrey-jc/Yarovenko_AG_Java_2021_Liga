package ru.digitalleague.factory.ok.notification.decorators;

import ru.digitalleague.factory.ok.notification.Notification;
import ru.digitalleague.factory.ok.notification.PhoneNotification;

public class TranslatePhoneNotification implements Notification {

    private final PhoneNotification component;
    private String body;
    private String phoneNumber;
    private String phoneWord;

    public TranslatePhoneNotification(PhoneNotification component) {
        this.component = component;
    }

    //    определяем язык по коду номера если не определили то стандартный язык вывода Английский
    private void localUser(String number) {
        phoneNumber = component.getUser().getPhone();
        String text;
        String codeCountry = number.substring(1, 2);
        switch (codeCountry) {
            case "7":
                body = LanguageNotification.RUSSIAN.getBody();
                phoneWord = LanguageNotification.RUSSIAN.getPhoneWord();
                break;
            case "8":
                body = LanguageNotification.CHINESE.getBody();
                phoneWord = LanguageNotification.CHINESE.getPhoneWord();
                break;
            case "9":
                body = LanguageNotification.UAE.getBody();
                phoneWord = LanguageNotification.UAE.getPhoneWord();
                // отразил номер телефона предполагая что они все пишут справа на лево
                phoneNumber = new StringBuilder(phoneNumber.substring(1, 12)).reverse().toString();

                break;
            case "4":
                body = LanguageNotification.GERMAN.getBody();
                phoneWord = LanguageNotification.GERMAN.getPhoneWord();
                break;
            case "3":
                codeCountry = number.substring(1, 3);
                switch (codeCountry) {
                    case "34":
                        body = LanguageNotification.SPAIN.getBody();
                        phoneWord = LanguageNotification.SPAIN.getPhoneWord();
                        break;
                    case "33":
                        body = LanguageNotification.FRENCH.getBody();
                        phoneWord = LanguageNotification.FRENCH.getPhoneWord();
                        break;
                    case "35":
                        body = LanguageNotification.PORTUGAL.getBody();
                        phoneWord = LanguageNotification.PORTUGAL.getPhoneWord();
                        break;
                    default:
                        body = LanguageNotification.ENGLISH.getBody();
                        phoneWord = LanguageNotification.ENGLISH.getPhoneWord();
                        break;
                }
                break;
            default:
                body = LanguageNotification.ENGLISH.getBody();
                phoneWord = LanguageNotification.ENGLISH.getPhoneWord();
        }
    }

    @Override
    public String getText() {
        localUser(component.getUser().getPhone());
        return String.format(component.getText(), phoneWord, phoneNumber, body);
    }

}
