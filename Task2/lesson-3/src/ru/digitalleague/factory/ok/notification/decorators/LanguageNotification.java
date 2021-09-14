package ru.digitalleague.factory.ok.notification.decorators;

public enum LanguageNotification {

    ENGLISH(
            "\"Address: %s\nDear %s,\n%s%s\nWith respect to you, support team!\"",
            "\n\nBuy our products!\n", "Good day!", "Phone #%s\n%s"
    ),
    GERMAN(
            "\"Adresse: %s\nLieb %s,\n%s%s\nDViele Grüße, Support-Team!\"",
            "\n\nKaufen Sie unsere Produkte!\n", "Guten Tag!", "Telefon #%s\n%s"
    ),
    CHINESE(
            "\"地址：%s\n亲 %s,\n%s%s\n最好的问候，支持团队！\"",
            "\n\n购买我们的产品！\n", "再会！", "电话 #%s\n%s"
    ),
    FRENCH(
            "\"Adresse: %s\nCher %s,\n%s%s\nMeilleures salutations, équipe de soutien!\"",
            "\n\nAchetez nos produits!\n", "Bonne journée!", "Téléphone #%s\n%s"
    ),
    UAE("\"عنوان: %s\nعزيزي %s,\n%s%s\nمع أطيب التحيات ، فريق الدعم!\"\n",
            "\n\nشراء منتجاتنا!\n\n", "يوم جيد!", "هاتف #+%s\n%s"
    ),
    SPAIN(
            "\"Dirección: %s\nEstimado %s,\n%s%s\nSaludos cordiales, equipo de soporte!\"",
            "\n\n¡Compra nuestros productos!\n", "¡Buen día!", "Teléfono #%s\n%s"
    ),
    PORTUGAL(
            "\"Endereço: %s\nCaro %s,\n%s%s\nAtenciosamente, equipe de suporte!\"",
            "\n\nCompre nossos produtos!\n", "Dia bom!", "Telefone #%s\n%s"
    );

    private String text;
    private String hasAdvertisementText;
    private String body;
    private String phoneWord;

    LanguageNotification(String text, String hasAdvertisementText, String body, String phoneWord) {
        this.text = text;
        this.hasAdvertisementText = hasAdvertisementText;
        this.body = body;
        this.phoneWord = phoneWord;
    }


    public String getPhoneWord() {
        return phoneWord;
    }

    public String getText() {
        return text;
    }

    public String getBody() {
        return body;
    }

    public String getHasAdvertisementText() {
        return hasAdvertisementText;
    }
}
