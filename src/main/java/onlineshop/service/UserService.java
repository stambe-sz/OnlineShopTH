package onlineshop.service;

import onlineshop.model.entity.User;
import onlineshop.model.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService{

    UserServiceModel register(UserServiceModel userServiceModel);
    UserServiceModel editUser(UserServiceModel userServiceModel);
    UserServiceModel getUserById(Long userId);
    UserServiceModel getUserByUsername(String username) throws Exception;
    boolean deleteUserById(Long userId);
    UserServiceModel saveUserToDb(UserServiceModel user);
}
