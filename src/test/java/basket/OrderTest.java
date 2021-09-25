package basket;

import bases.User;
import bases.UserBasket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import products.Food;

class OrderTest {

    private static UserBasket basket;
    private static User user;
    private static basket.Order order;

    @BeforeAll
    static void setUp() {
        user = new User(1L, "Andrey");
        basket = new UserBasket(user);
        order = new Order(basket);
    }


    @Test
    void checkOrder() {
        String massage = order.checkBasket();
        Assertions.assertEquals("Проверьте заказ перед оформлением\n", massage);
    }

    @Test
    void checkout() {
        Food food = new Food("orange", 1);
        basket.addProductToBasket(food, 1);
        order.checkout();
        boolean ordered = basket.isOrdered();
        Assertions.assertTrue(ordered);
    }

}