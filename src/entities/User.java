package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private long id;
    String name;
    String surname;
    String login;
    String password;
    String status;
    String role;
}
