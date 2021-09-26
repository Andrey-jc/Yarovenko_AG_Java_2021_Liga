package basket;

import products.ProductShelf;
import notification.Notification;

import java.util.HashMap;

public abstract class ShoppingBasketAction extends Notification {
    private HashMap<String, Integer> mapOrders = new HashMap<>();

    private boolean ordered = false;

    public HashMap<String, Integer> getMapOrders() {
        return mapOrders;
    }

    protected void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }

    public boolean isOrdered() {
        return ordered;
    }

    public void addProductToBasket(ProductShelf product, int number) {
        if (!ordered) {
            if ((product.getNumber() - number) >= 0) {
                product.decreaseProducts(product, number);
                mapOrders.merge(product.getNameProduct(), number, Integer::sum);
            } else {
                System.out.println(notEnoughfProduct(product, number));
            }
        } else {
            System.out.println(notificationErrorChange());
        }
    }

    public void removeProductFromBasket(ProductShelf product) {
        if (!ordered) {
            product.addProducts(product, mapOrders.get(product.getNameProduct()));
            mapOrders.remove(product.getNameProduct());
        } else {
            System.out.println(notificationErrorChange());
        }
    }

    public void changeProductBasket(ProductShelf product, int number) {
        if (!ordered) {
            product.addProducts(product, mapOrders.get(product.getNameProduct()));
            if ((product.getNumber() - number) >= 0) {
                mapOrders.replace(product.getNameProduct(), number);
                product.decreaseProducts(product, mapOrders.get(product.getNameProduct()));
            } else {
                product.decreaseProducts(product, mapOrders.get(product.getNameProduct()));
                System.out.println(notEnoughfProduct(product, number));
            }
        } else {
            System.out.println(notificationErrorChange());
        }
    }

    public void clearProductBasket() {
        if (!ordered) {
            mapOrders.clear();
            System.out.println(notificationBasketEmpty());
        } else {
            System.out.println(notificationErrorChange());
        }
    }
}