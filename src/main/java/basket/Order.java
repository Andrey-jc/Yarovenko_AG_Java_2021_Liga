package basket;

import bases.UserBasket;
import notification.Notification;

import java.util.Map;

public class Order extends Notification implements ViewBasket {

    private UserBasket basket;

    public Order(UserBasket basket) {
        this.basket = basket;
    }

    public String checkBasket() {
        for (Map.Entry<String, Integer> entry : basket.getMapOrders().entrySet()) {
            System.out.println("Товар: " + entry.getKey() + ", Колличество: " + entry.getValue());
        }
        return "Проверьте заказ перед оформлением\n";
    }


    public void checkout() {
        if (!basket.getMapOrders().isEmpty()) {
            basket.setOrdered(true);
            System.out.println(orderIsAccepted());
        } else {
            System.out.println(selectProduct());
        }

    }



}
