package bases;

import basket.ShoppingBasketAction;
import basket.ViewBasket;

import java.util.Map;

public class UserBasket extends ShoppingBasketAction implements ViewBasket {
    private final User user;


    public UserBasket(User user) {
        this.user = user;
    }

    @Override
    public boolean isOrdered() {
        return super.isOrdered();
    }

    public String checkBasket() {
        for (Map.Entry<String, Integer> entry : getMapOrders().entrySet()) {
            System.out.println("Товар: " + entry.getKey() + ", Колличество: " + entry.getValue());
        }
        return user.getName() +  " добавьте товар или перейдите к оформлению заказа\n";
    }
}
