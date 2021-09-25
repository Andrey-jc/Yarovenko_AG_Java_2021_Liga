package products;

public abstract class ProductShelf {
    private final String nameProduct;
    private Integer number;

    public ProductShelf(String name, Integer number) {
        this.nameProduct = name;
        this.number = number;
    }


    public String getNameProduct() {
        return nameProduct;
    }

    public Integer getNumber() {
        return number;
    }


    private void setNumber(Integer number) {
        this.number = number;
    }

    public void addProducts(ProductShelf product, int number) {
        product.setNumber(product.getNumber() + number);


    }

    public void decreaseProducts(ProductShelf product, int number) {
        product.setNumber(product.getNumber() - number);
    }


}
