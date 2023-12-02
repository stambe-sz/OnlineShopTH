package onlineshop.model.service;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleServiceModel extends BaseServiceModel {

    private String name;

    public String getAuthority() {
        return this.name;
    }
}
