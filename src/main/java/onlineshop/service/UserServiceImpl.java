package onlineshop.service;

import lombok.RequiredArgsConstructor;
import onlineshop.model.repository.UserRepository;
import onlineshop.model.service.UserServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public UserServiceModel register(UserServiceModel user) {
        //UserServiceModel => User
        return null;
    }

    @Override
    public UserServiceModel getUserById(long userId) {
        //User => UserServiceModel
        return null;
    }

    @Override
    public boolean deleteUserById(long userId) {
        return false;
    }
}
