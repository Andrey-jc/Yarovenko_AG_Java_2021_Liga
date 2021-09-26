package bases;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UserBasketTest {
    private static UserBasket basket;

    private static User user;

    @BeforeAll
    static void setUp() {
        user = new User(1L, "Andrey");
        basket = new UserBasket(user);
    }

    @Test
    public void checkBasket() {
        String massage = basket.checkBasket();
        Assertions.assertEquals(user.getName() +  " добавьте товар или перейдите к оформлению заказа\n", massage);
    }
}