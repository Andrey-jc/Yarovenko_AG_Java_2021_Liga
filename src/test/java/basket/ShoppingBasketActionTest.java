package basket;

import products.ProductShelf;
import bases.User;
import bases.UserBasket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import products.Food;
import products.Magazine;

public class ShoppingBasketActionTest {
    private static ShoppingBasketAction basket;

    @BeforeAll
    static void setUp() {
        User user = new User(1L, "Andrey");
        basket = new UserBasket(user);
    }

    @Test
    void addProductToBasket_value() {
        ProductShelf apple = new Food("Apple", 10000);
        ProductShelf orange = new Food("Orange", 1);
        ProductShelf magazine = new Magazine("People", 101);

        basket.addProductToBasket(apple, 10000);
        Assertions.assertEquals(10000, basket.getMapOrders().get("Apple"));
        // проверяем сколько  осталось на складе
        Assertions.assertEquals(apple.getNumber(), 0);
        // не сможет добавить так как на складе нехватает
        basket.addProductToBasket(orange, 2);
        Assertions.assertNull(basket.getMapOrders().get("Orange"));

        basket.addProductToBasket(magazine, 1);
        basket.addProductToBasket(magazine, 1);
        Assertions.assertEquals(2, basket.getMapOrders().get("People"));
    }

    @Test
    void addProductToBasket_value_isNotEmpty() {
        ProductShelf apple = new Food("Apple", 10000);

        basket.addProductToBasket(apple, 10000);
        Assertions.assertFalse(basket.getMapOrders().isEmpty());
    }

    @Test
    void removeProductFromBasket() {
        ProductShelf apple = new Food("Apple", 10000);

        basket.addProductToBasket(apple, 10000);
        basket.removeProductFromBasket(apple);
        Assertions.assertFalse(basket.getMapOrders().containsKey("Apple"));
    }

    @Test
    void removeProductFromBasketNullPointerException() {
        ProductShelf apple = new Food("Apple", 10000);

        basket.addProductToBasket(apple, 10000);
        basket.removeProductFromBasket(apple);
        Assertions.assertThrows(NullPointerException.class, () ->
                basket.removeProductFromBasket(apple));
    }

    @Test
    void changeProductBasket() {
        ProductShelf apple = new Food("Apple", 10000);

        basket.addProductToBasket(apple, 10000);
        basket.changeProductBasket(apple, 12);
        Assertions.assertEquals(9988, apple.getNumber());
    }

    @Test
    void clearProductBasket() {
        ProductShelf apple = new Food("Apple", 10000);
        ProductShelf orange = new Food("Orange", 120);

        basket.addProductToBasket(apple, 10000);
        basket.addProductToBasket(orange, 100);
        basket.addProductToBasket(orange, 20);
        basket.clearProductBasket();
        Assertions.assertTrue(basket.getMapOrders().isEmpty());
    }
}