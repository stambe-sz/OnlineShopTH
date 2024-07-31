package onlineshop.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserViewModel extends BaseViewModel {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private RoleViewModel role;
    private CartViewModel cart;
}
