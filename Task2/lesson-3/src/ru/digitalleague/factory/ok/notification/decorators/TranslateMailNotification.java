package ru.digitalleague.factory.ok.notification.decorators;

import ru.digitalleague.factory.ok.notification.MailNotification;
import ru.digitalleague.factory.ok.notification.Notification;

public class TranslateMailNotification implements Notification {

    private final MailNotification component;
    private String hasAdvertisementText;
    private String body;
    String email;

    public TranslateMailNotification(MailNotification component) {
        this.component = component;

    }

//    определяем язык по коду номера если не определили то стандартный язык вывода Английский
    private String localUser(String number) {
        String text;
        String codeCountry = number.substring(1, 2);
        email = component.getUser().getEmail();
        switch (codeCountry) {
            case "7":
                text = component.getText();
                break;
            case "8":
                text = LanguageNotification.CHINESE.getText();
                hasAdvertisementText = LanguageNotification.CHINESE.getHasAdvertisementText();
                body = LanguageNotification.CHINESE.getBody();
                break;
            case "9":
                text = LanguageNotification.UAE.getText();
                hasAdvertisementText = LanguageNotification.UAE.getHasAdvertisementText();
                body = LanguageNotification.UAE.getBody();
                // отразил номер email предполагая что они все пишут справа на лево
                email = new StringBuilder(email).reverse().toString();
                break;
            case "4":
                text = LanguageNotification.GERMAN.getText();
                hasAdvertisementText = LanguageNotification.GERMAN.getHasAdvertisementText();
                body = LanguageNotification.GERMAN.getBody();
                break;
            case "3":
                codeCountry = number.substring(1, 3);
                switch (codeCountry) {
                    case "34":
                        text = LanguageNotification.SPAIN.getText();
                        hasAdvertisementText = LanguageNotification.SPAIN.getHasAdvertisementText();
                        body = LanguageNotification.SPAIN.getBody();
                        break;
                    case "33":
                        text = LanguageNotification.FRENCH.getText();
                        hasAdvertisementText = LanguageNotification.FRENCH.getHasAdvertisementText();
                        body = LanguageNotification.FRENCH.getBody();
                        break;
                    case "35":
                        text = LanguageNotification.PORTUGAL.getText();
                        hasAdvertisementText = LanguageNotification.PORTUGAL.getHasAdvertisementText();
                        body = LanguageNotification.PORTUGAL.getBody();
                        break;
                    default:
                        text = LanguageNotification.ENGLISH.getText();
                        hasAdvertisementText = LanguageNotification.ENGLISH.getHasAdvertisementText();
                        body = LanguageNotification.ENGLISH.getBody();
                        break;
                }
                break;
            default:
                text = LanguageNotification.ENGLISH.getText();
                hasAdvertisementText = LanguageNotification.ENGLISH.getHasAdvertisementText();
                body = LanguageNotification.ENGLISH.getBody();
        }
        return text;
    }

    @Override
    public String getText() {

        component.getText();
        return String.format(localUser(component.getUser().getPhone()), email,
                component.getUser().getName(),
                body,
                component.isHasAdvertisement() ? hasAdvertisementText : "");
    }

}
