package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemShipment {
    private long id;
    private long shipmentGoodsId;
    private long productId;
    private int quantity;
    private Double price;

}
