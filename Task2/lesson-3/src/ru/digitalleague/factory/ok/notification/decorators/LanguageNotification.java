package ru.digitalleague.factory.ok.notification.decorators;

public enum LanguageNotification {
    ENGLISH(
            "Address",
            "Dear",
            "With respect, Support-Team!",
            "Good day!",
            "Kaufen Sie unsere Produkte!",
            "Phone"
    ),
    GERMAN(
            "Address",
            "Lieb",
            "Kaufen Sie unsere Produkte!",
            "Guten Tag!",
            "Kaufen Sie unsere Produkte!",
            "Telefon"
    ),
    CHINESE(
            "地址",
            "亲",
            "最好的问候，支持团队！",
            "再会！",
            "购买我们的产品！",
            "电话"
    ),
    FRENCH(
            "Address",
            "Cher",
            "Meilleures salutations, équipe de soutien!",
            "Bonne journée!",
            "Achetez nos produits!",
            "Téléphone"
    ),
    UAE(
            "عنوان",
            "عزيزي",
            "مع أطيب التحيات ، فريق الدعم!",
            "يوم جيد!",
            "شراء منتجاتنا!",
            "هاتف"
    ),
    SPAIN(
            "Dirección",
            "Estimado",
            "Saludos cordiales, equipo de soporte!",
            "¡Buen día!",
            "¡Compra nuestros productos!",
            "Teléfono"
    ),
    PORTUGAL(
            "Endereço",
            "Caro",
            "Atenciosamente, equipe de suporte!",
            "Dia bom!",
            "Compre nossos produtos!",
            "Telefone"
    ),
    RUSSIAN(
            "Адрес",
            "Уважаемый",
            "С уважением, команда поддержки!",
            "Добрый день!",
            "Покупайте наши товары!",
            "Телефон"
    );

    private final String address;
    private final String dear;
    private final String withRespect;
    private final String hasAdvertisementText;
    private final String body;
    private final String phoneWord;

    public String getAddress() {
        return address;
    }

    public String getDear() {
        return dear;
    }

    public String getWithRespect() {
        return withRespect;
    }

    LanguageNotification(String address, String dear, String withRespect, String body, String hasAdvertisementText, String phoneWord) {
        this.address = address;
        this.dear = dear;
        this.withRespect = withRespect;
        this.hasAdvertisementText = hasAdvertisementText;
        this.body = body;
        this.phoneWord = phoneWord;
    }

    public String getPhoneWord() {
        return phoneWord;
    }


    public String getBody() {
        return body;
    }

    public String getHasAdvertisementText() {
        return hasAdvertisementText;
    }
}
