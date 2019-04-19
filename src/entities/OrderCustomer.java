package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderCustomer {
    private long id;
    private Date date;
    private String number;
    private long partnerId;
    private long userId;
    private long warehouseId;
    private List<ItemOrderCustomer> items = new ArrayList<>();
    private double total;
}
