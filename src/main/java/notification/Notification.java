package notification;

import products.ProductShelf;

public abstract class Notification {


    public static String notificationErrorChange() {
        return "Невозможно изменять корзину после оформления заказа";
    }

    public static String notificationBasketEmpty() {
        return "Корзина очищена";
    }

    public static String notEnoughfProduct(ProductShelf product, int number) {
        return "Не хватает товаров в колличестве:  " + (number - product.getNumber());
    }

    public static String orderIsAccepted() {
        return "Заказ принят, отправлено на упаковку";
    }

    public static String selectProduct() {
        return "Корзина путса, перед оформлением заказа выберите товары";
    }

}
