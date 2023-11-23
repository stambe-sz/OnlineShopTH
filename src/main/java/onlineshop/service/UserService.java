package onlineshop.service;

import onlineshop.model.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserServiceModel register(UserServiceModel userServiceModel);
    UserServiceModel editUser(UserServiceModel userServiceModel);
    UserServiceModel getUserById(Long userId);
    boolean deleteUserById(Long userId);

}
