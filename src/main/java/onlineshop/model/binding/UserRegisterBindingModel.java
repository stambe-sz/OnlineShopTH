package onlineshop.model.binding;

import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import onlineshop.model.entity.Role;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterBindingModel {

    @Size(min = 3,max = 20,message = "Username length must be between 3 and 20 characters")
    private String username;
    @Size(min = 3,max = 20,message = "First name length must be between 3 and 20 characters")
    private String firstName;
    @Size(min = 3,max = 20,message = "Last name length must be between 3 and 20 characters")
    private String lastName;
    private String email;
    private String password;
    @Size(min = 10,max = 100,message = "Address length must be between 3 and 20 characters")
    private String address;

}
