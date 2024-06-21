package onlineshop.model.binding;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Email
    private String email;
    @Size(min = 3,max = 30,message = "Password length must be between 3 and 30 characters")
    private String password;
    @Size(min = 3,max = 30,message = "Password length must be between 3 and 30 characters")
    private String confirmPassword;
    @Size(min = 10,max = 100,message = "Address length must be between 10 and 100 characters")
    private String address;

}
