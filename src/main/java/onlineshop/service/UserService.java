package onlineshop.service;

import onlineshop.model.service.UserServiceModel;

public interface UserService {

    UserServiceModel register(UserServiceModel user);
    UserServiceModel getUserById(long userId);
    boolean deleteUserById(long userId);

}
