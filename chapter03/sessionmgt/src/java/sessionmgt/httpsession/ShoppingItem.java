package sessionmgt.httpsession;

import java.io.Serializable;

public class ShoppingItem implements Serializable {
    private static final long serialVersionUID = -100L;
    private Product product;
    private int quantity;

    public ShoppingItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }	
}
