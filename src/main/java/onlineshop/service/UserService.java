package onlineshop.service;

import onlineshop.model.service.UserServiceModel;

public interface UserService{

    UserServiceModel register(UserServiceModel userServiceModel);
    
    UserServiceModel editUser(UserServiceModel userServiceModel);
    
    UserServiceModel getUserById(Long userId);
    
    UserServiceModel getUserByUsername(String username) throws Exception;
    
    boolean deleteUserById(Long userId);
    
    UserServiceModel saveUserToDb(UserServiceModel user);
}
