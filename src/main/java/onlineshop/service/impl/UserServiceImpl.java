package onlineshop.service.impl;

import lombok.RequiredArgsConstructor;
import onlineshop.model.entity.User;
import onlineshop.repository.UserRepository;
import onlineshop.model.service.UserServiceModel;
import onlineshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserServiceModel register(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);
        User savedUser = this.userRepository.save(user);
        return modelMapper.map(savedUser, UserServiceModel.class);
        //TODO register(UserServiceModel userServiceModel) ...
    }

    @Override
    public UserServiceModel editUser(UserServiceModel userServiceModel) {
        User user = this.findUser(userServiceModel.getId());
        User editUser = this.mapUser(user, userServiceModel);
        this.userRepository.save(editUser);
        return modelMapper.map(editUser, UserServiceModel.class);
    }

    @Override
    public UserServiceModel getUserById(Long userId) {
        User user = this.findUser(userId);
        UserServiceModel foundUser = modelMapper.map(user, UserServiceModel.class);
        //TODO getUserById(Long userId)...
        return foundUser;
    }

    @Override
    public boolean deleteUserById(Long userId) {
        User user = this.findUser(userId);
        this.userRepository.delete(user);
        return true;
        //TODO deleteUserById(long userId)...
    }

    private User findUser(Long id) {
        User user = this.userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new NoSuchElementException();
        }
        return user;
    }

    private User mapUser(User user, UserServiceModel userServiceModel) {
        user.setRole(userServiceModel.getRole());
        user.setEmail(userServiceModel.getEmail());
        user.setPassword(userServiceModel.getPassword());
        user.setPassword(userServiceModel.getPassword());
        user.setFirstName(userServiceModel.getFirstName());
        user.setLastName(userServiceModel.getLastName());
        return user;
    }
}
