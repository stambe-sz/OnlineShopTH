package onlineshop.service;

import onlineshop.model.service.UserServiceModel;

public interface UserService {

    UserServiceModel register(UserServiceModel user);
    UserServiceModel getUserById(Long userId);
    boolean deleteUserById(long userId);

}
