package onlineshop.service;

import onlineshop.model.service.UserServiceModel;

public interface UserService {

    UserServiceModel register(UserServiceModel userServiceModel);
    UserServiceModel editUser(UserServiceModel userServiceModel);
    UserServiceModel getUserById(Long userId);
    boolean deleteUserById(Long userId);

}
