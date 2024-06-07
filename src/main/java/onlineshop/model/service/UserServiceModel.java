package onlineshop.model.service;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import onlineshop.model.entity.Role;

@Getter
@Setter
@NoArgsConstructor
public class UserServiceModel extends BaseServiceModel {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private RoleServiceModel role;
    private CartServiceModel cart;
}
