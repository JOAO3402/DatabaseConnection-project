package models.entities;

import java.util.Objects;

public class Sell {

    private Integer quantity;
    private Client client;
    private Product product;

    public Sell(Client client, Product product, Integer quantity) {
        this.client = client;
        this.product = product;
        this.quantity = quantity;
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double subtotal(){
        return quantity * product.getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Sell sell)) return false;
        return Objects.equals(quantity, sell.quantity) && Objects.equals(client, sell.client) && Objects.equals(product, sell.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, client, product);
    }
}
