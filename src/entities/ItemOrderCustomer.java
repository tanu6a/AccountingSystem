package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemOrderCustomer {
    private long id;
    private long orderCustomerId;
    private long productId;
    private int quantity;
    private double discountPercent;
    private Double price;

    public ItemOrderCustomer(long orderId, long productId, int quantity, double discountPercent) {
        this.orderCustomerId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.discountPercent = discountPercent;
    }
    public ItemOrderCustomer(long id, long productId, int quantity) {
    }
}
