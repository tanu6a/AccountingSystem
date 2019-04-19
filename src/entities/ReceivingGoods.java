package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ReceivingGoods {
    private long id;
    private Date date;
    private String number;
    private Partner partner;
    private OrderSupplier orderSupplier;
    private User manager;
    private Warehouse warehouse;
    private List<ItemReceiving> items = new ArrayList<>();
    private double total;
}
