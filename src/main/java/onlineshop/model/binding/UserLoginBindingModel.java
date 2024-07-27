package onlineshop.model.binding;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static onlineshop.constants.RegexValidation.*;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginBindingModel {

    @Pattern(regexp = USERNAME_REGEX, message = USERNAME_REGEX_ERROR_MSG)
    private String username;
    @Pattern(regexp = PASSWORD_REGEX, message = PASSWORD_MIN_MAX_ERROR_MSG)
    private String password;
}
