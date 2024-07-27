package onlineshop.model.binding;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static onlineshop.constants.RegexValidation.*;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterBindingModel {

    @Pattern(regexp = USERNAME_REGEX, message = USERNAME_REGEX_ERROR_MSG)
    private String username;
    @Pattern(regexp = FIRST_NAME_REGEX, message = FIRST_NAME_REGEX_ERROR_MSG)
    private String firstName;
    @Pattern(regexp = LAST_NAME_REGEX, message = LAST_NAME_REGEX_ERROR_MSG)
    private String lastName;
    @Pattern(regexp = EMAIL_REGEX, message = EMAIL_REGEX_ERROR_MSG)
    private String email;
    @Pattern(regexp = PASSWORD_REGEX, message = PASSWORD_REGEX_ERROR_MSG)
    private String password;
    @Pattern(regexp = PASSWORD_REGEX, message = PASSWORD_REGEX_ERROR_MSG)
    private String confirmPassword;
    @Pattern(regexp = ADDRESS_REGEX, message = ADDRESS_ERROR_MSG)
    private String address;

}
