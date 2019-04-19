package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemOrderSupplier {
    private long id;
    private long orderSupplierId;
    private long productId;
    private int quantity;
    private double discountPercent;
    private Double price;

    public ItemOrderSupplier(long id, long productId, int quantity) {
    }
}
