package onlineshop.model.binding;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginBindingModel {

    @Size(min = 3,max = 50,message = "Username must be between 3 and 50 characters.")
    private String username;
    @Size(min = 3,max = 50,message = "Password must be between 3 and 50 characters.")
    private String password;
}
