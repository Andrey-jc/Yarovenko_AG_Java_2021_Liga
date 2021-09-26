package notification;

import bases.User;
import basket.Order;
import bases.UserBasket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import products.Food;

@RunWith(MockitoJUnitRunner.class)
public class NotificationTest {
    private static UserBasket basket;

    private static User user;

    private static Order order;

    private static Food apple;

    private static Food orange;

    @BeforeAll
    static void setUp() {
        user = new User(1L, "Andrey");
        basket = new UserBasket(user);
        order = new Order(basket);
        apple = new Food("Apple", 10000);
        orange = new Food("Orange", 15);
    }

    @Test
    public void notificationErrorChange() {
        String notificationErrorChange = Notification.notificationErrorChange();
        Assertions.assertEquals("Невозможно изменять корзину после оформления заказа", notificationErrorChange);
    }

    @Test
    public void notificationBasketEmpty() {
        String notificationBasketEmpty = Notification.notificationBasketEmpty();
        Assertions.assertEquals("Корзина очищена", notificationBasketEmpty);
    }

    @Test
    public void notEnoughfProduct() {
        String notEnoughfProduct = Notification.notEnoughfProduct(apple, 10001);
        Assertions.assertEquals("Не хватает товаров в колличестве:  1", notEnoughfProduct);
    }

    @Test
    public void orderIsAccepted() {
        String orderIsAccepted = Notification.orderIsAccepted();
        Assertions.assertEquals("Заказ принят, отправлено на упаковку", orderIsAccepted);
    }

    @Test
    public void selectProduct() {
        basket.addProductToBasket(orange, 1);
        order.checkout();
        boolean ordered = basket.isOrdered();
        Assertions.assertTrue(ordered);
    }
}