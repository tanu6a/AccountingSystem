package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderSupplier {
    private long id;
    private Date date;
    private String number;
    private long partnerId;
    private long userId;
    private long warehouseId;
    private List<ItemOrderSupplier> items = new ArrayList<>();
    private double total;
}
