package onlineshop.model.binding;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static onlineshop.constants.RegexValidation.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryBindingModel {

    @Pattern(regexp = CATEGORY_NAME_REGEX, message = CATEGORY_ERROR_MSG)
    private String categoryName;
}
