package onlineshop.service.impl;

import lombok.RequiredArgsConstructor;
import onlineshop.model.entity.Role;
import onlineshop.model.entity.User;
import onlineshop.model.service.UserServiceModel;
import onlineshop.repository.UserRepository;
import onlineshop.service.RoleService;
import onlineshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public UserServiceModel register(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        if (this.userRepository.count() == 0){
            Role roleAdmin = this.roleService.findRoleByName("ADMIN");
            user.setRole(roleAdmin);
        } else {
            Role roleUser = this.roleService.findRoleByName("USER");
            user.setRole(roleUser);
        }

        User savedUser = this.userRepository.save(user);
        return modelMapper.map(savedUser, UserServiceModel.class);
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
        return modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel getUserByUsername(String username) {
        User user = this.userRepository.findUserByUsername(username)
                .orElse(null);
        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public boolean deleteUserById(Long userId) {
        User user = this.findUser(userId);
        this.userRepository.delete(user);
        return true;
    }

    @Override
    public User saveUserToDb(User user) {
        return this.userRepository.save(user);
    }

    private User findUser(Long id) {
        User user = this.userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new NoSuchElementException();
        }
        return user;
    }

    private User mapUser(User user, UserServiceModel userServiceModel) {
        user.setEmail(userServiceModel.getEmail());
        user.setPassword(userServiceModel.getPassword());
        user.setFirstName(userServiceModel.getFirstName());
        user.setLastName(userServiceModel.getLastName());
        user.setAddress(userServiceModel.getAddress());
        user.setUsername(userServiceModel.getUsername());
        user.setPassword(userServiceModel.getPassword());
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundUser = this.userRepository.findUserByUsername(username).orElse(null);
        if (foundUser == null) {
            //TODO
        }
        return foundUser;
    }
}
