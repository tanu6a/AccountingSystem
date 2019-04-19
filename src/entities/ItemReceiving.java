package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemReceiving {
    private long id;
    private long receivingGoodsId;
    private long productId;
    private int quantity;
    private Double price;

}
