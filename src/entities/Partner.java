package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Partner {
    private long tin;
    String name;
    String fullname;
    public TypePartner typePartner;

}

