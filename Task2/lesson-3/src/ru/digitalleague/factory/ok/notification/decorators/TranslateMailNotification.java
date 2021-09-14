package ru.digitalleague.factory.ok.notification.decorators;

import ru.digitalleague.factory.ok.notification.MailNotification;
import ru.digitalleague.factory.ok.notification.Notification;

public class TranslateMailNotification implements Notification {

    private final MailNotification component;
    private String adres;
    private String dear;
    private String withRespect;
    private String hasAdvertisementText;
    private String body;
    private String email;

    public TranslateMailNotification(MailNotification component) {
        this.component = component;

    }

    //    определяем язык по коду номера если не определили то стандартный язык вывода Английский
    private void localUser(String number) {
        String codeCountry = number.substring(1, 2);
        email = component.getUser().getEmail();
        switch (codeCountry) {
            case "7":
                adres = LanguageNotification.RUSSIAN.getAddress();
                dear = LanguageNotification.RUSSIAN.getDear();
                withRespect = LanguageNotification.RUSSIAN.getWithRespect();
                hasAdvertisementText = LanguageNotification.RUSSIAN.getHasAdvertisementText();
                body = LanguageNotification.RUSSIAN.getBody();
                break;
            case "8":
                adres = LanguageNotification.CHINESE.getAddress();
                dear = LanguageNotification.CHINESE.getDear();
                withRespect = LanguageNotification.CHINESE.getWithRespect();
                hasAdvertisementText = LanguageNotification.CHINESE.getHasAdvertisementText();
                body = LanguageNotification.CHINESE.getBody();
                break;
            case "9":
                adres = LanguageNotification.UAE.getAddress();
                dear = LanguageNotification.UAE.getDear();
                withRespect = LanguageNotification.UAE.getWithRespect();
                hasAdvertisementText = LanguageNotification.UAE.getHasAdvertisementText();
                body = LanguageNotification.UAE.getBody();
                // отразил номер email предполагая что они все пишут справа на лево
                email = new StringBuilder(email).reverse().toString();
                break;
            case "4":
                adres = LanguageNotification.GERMAN.getAddress();
                dear = LanguageNotification.GERMAN.getDear();
                withRespect = LanguageNotification.GERMAN.getWithRespect();
                hasAdvertisementText = LanguageNotification.GERMAN.getHasAdvertisementText();
                body = LanguageNotification.GERMAN.getBody();
                break;
            case "3":
                codeCountry = number.substring(1, 3);
                switch (codeCountry) {
                    case "34":
                        adres = LanguageNotification.SPAIN.getAddress();
                        dear = LanguageNotification.SPAIN.getDear();
                        withRespect = LanguageNotification.SPAIN.getWithRespect();
                        hasAdvertisementText = LanguageNotification.SPAIN.getHasAdvertisementText();
                        body = LanguageNotification.SPAIN.getBody();
                        break;
                    case "33":
                        adres = LanguageNotification.FRENCH.getAddress();
                        dear = LanguageNotification.FRENCH.getDear();
                        withRespect = LanguageNotification.FRENCH.getWithRespect();
                        hasAdvertisementText = LanguageNotification.FRENCH.getHasAdvertisementText();
                        body = LanguageNotification.FRENCH.getBody();
                        break;
                    case "35":
                        adres = LanguageNotification.PORTUGAL.getAddress();
                        dear = LanguageNotification.PORTUGAL.getDear();
                        withRespect = LanguageNotification.PORTUGAL.getWithRespect();
                        hasAdvertisementText = LanguageNotification.PORTUGAL.getHasAdvertisementText();
                        body = LanguageNotification.PORTUGAL.getBody();
                        break;
                    default:
                        adres = LanguageNotification.ENGLISH.getAddress();
                        dear = LanguageNotification.ENGLISH.getDear();
                        withRespect = LanguageNotification.ENGLISH.getWithRespect();
                        hasAdvertisementText = LanguageNotification.ENGLISH.getHasAdvertisementText();
                        body = LanguageNotification.ENGLISH.getBody();
                        break;
                }
                break;
            default:
                adres = LanguageNotification.ENGLISH.getAddress();
                dear = LanguageNotification.ENGLISH.getDear();
                withRespect = LanguageNotification.ENGLISH.getWithRespect();
                hasAdvertisementText = LanguageNotification.ENGLISH.getHasAdvertisementText();
                body = LanguageNotification.ENGLISH.getBody();
        }
    }

    @Override
    public String getText() {
        localUser(component.getUser().getPhone());
        return String.format(component.getText(),
                adres,
                email,
                dear,
                component.getUser().getName(),
                body,
                component.isHasAdvertisement() ? hasAdvertisementText : "",
                withRespect);
    }

}
