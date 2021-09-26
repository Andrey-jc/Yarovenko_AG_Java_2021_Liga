package products;

import bases.User;
import bases.UserBasket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FoodTest {
    private final User user = new User(1L, "Andrey");
    private final UserBasket userBasket = new UserBasket(user);
    private final ProductShelf food = mock(Food.class);

    @Test
    public void addProducts() {
        Hat hat = new Hat("Sambrero", 1);
        hat.addProducts(hat, 2);
        Assertions.assertEquals(3, hat.getNumber());
        food.addProducts(food, 2);
        verify(food).addProducts(food, 2);
    }

    @Test
    public void addProductsEquals() {
        Hat hat = new Hat("Sambrero", 100);
        userBasket.addProductToBasket(hat, 1);
        Integer sambrero = userBasket.getMapOrders().get("Sambrero");
        Assertions.assertEquals(1, sambrero);
    }

    @Test
    public void decreaseProductsEquals() {
        userBasket.addProductToBasket(food, 1);
        food.decreaseProducts(food, 1);
        verify(food).decreaseProducts(food, 1);
    }
}