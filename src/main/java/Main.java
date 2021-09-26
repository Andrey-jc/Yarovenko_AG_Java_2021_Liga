import bases.User;
import bases.UserBasket;
import basket.Order;
import products.Food;
import products.Hat;
import products.Magazine;
import products.ProductShelf;

public class Main {
    public static void main(String[] args) {
        User user = new User(1L, "Andrey");

        emulation(user);
    }

    /**
     * Метод эмулирует заказ в магазине различных товаров
     *
     * @param user покупатель
     */
    public static void emulation(User user) {
        UserBasket basket = new UserBasket(user);
        Order order = new Order(basket);
//        Товарные полки
        ProductShelf apple = new Food("Apple", 10000);
        ProductShelf magazine = new Magazine("People", 101);
        ProductShelf hat = new Hat("Sambrero", 1);

//      Добавлляем в корзину различные товары
        basket.addProductToBasket(apple, 10000);
        basket.addProductToBasket(hat, 1);
//      Просмотр корзины пользователя
        System.out.println(basket.checkBasket());
//      Удаление товара из корзины корзины пользователя
        basket.removeProductFromBasket(apple);
//        Попытка просмотра заказа перед оформление корзины
        System.out.println(order.checkBasket());
//        Очистка корзины
        basket.clearProductBasket();
        // корзина пустая и оформить заказ не удасться
        order.checkout();
//        Вновь наполняем корзину
        basket.addProductToBasket(magazine, 1);
        order.checkout();
//        Попытка изменения корзины
        basket.addProductToBasket(magazine, 1);
    }
}
