package onlineshop.model.binding;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import onlineshop.model.entity.CartItem;

import java.util.List;

import static onlineshop.constants.RegexValidation.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartBindingModel {
    @Pattern(regexp = USERNAME_REGEX, message = USERNAME_REGEX_ERROR_MSG)
    private String username;
    private List<CartItem> cartItems;
}
