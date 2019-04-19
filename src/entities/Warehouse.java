package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Warehouse {
    private long id;
    String name;
    String address;
}
