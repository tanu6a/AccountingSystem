package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ShipmentGoods {
    private long id;
    private Date date;
    private String number;
    private Partner partner;
    private OrderCustomer orderCustomer;
    private User manager;
    private Warehouse warehouse;
    private List<ItemShipment> items = new ArrayList<>();
    private double total;
}
