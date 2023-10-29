package onlineshop.service;

import lombok.RequiredArgsConstructor;
import onlineshop.model.entity.User;
import onlineshop.model.repository.UserRepository;
import onlineshop.model.service.UserServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public UserServiceModel register(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel,User.class);
        User savedUser = this.userRepository.save(user);
        return modelMapper.map(savedUser,UserServiceModel.class);
        //TODO register(UserServiceModel userServiceModel) ...
    }

    @Override
    public UserServiceModel getUserById(Long userId) {
        User user = this.findUser(userId);
        UserServiceModel foundUser = modelMapper.map(user,UserServiceModel.class);
        //TODO getUserById(Long userId)...
        return foundUser;
    }

    @Override
    public boolean deleteUserById(long userId) {
        User user = this.findUser(userId);
        this.userRepository.delete(user);
        return true;
        //TODO deleteUserById(long userId)...
    }

    private User findUser(Long id){
        User user = this.userRepository.findById(id).orElse(null);
        if (user == null){
            throw new NoSuchElementException();
        }
        return user;
    }
}
