package onlineshop.utils;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import onlineshop.enums.RoleEnum;
import onlineshop.model.entity.Role;
import onlineshop.repository.RoleRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Init {

    private final RoleRepository roleRepository;

    @PostConstruct
    void loadData(){
        this.loadRoles();
        this.loadProducts();
    }

    private void loadProducts() {
        //TODO for products
    }

    private void loadRoles(){
        if(this.roleRepository.count() == 0){
            Role role = null;
            for (int i = 0; i < RoleEnum.values().length; i++) {
                role = new Role(RoleEnum.values()[i].name());
                this.roleRepository.saveAndFlush(role);
            }
        }
    }
}
