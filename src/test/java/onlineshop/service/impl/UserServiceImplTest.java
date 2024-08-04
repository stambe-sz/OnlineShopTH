package onlineshop.service.impl;

import onlineshop.model.entity.Role;
import onlineshop.model.entity.User;
import onlineshop.model.service.UserServiceModel;
import onlineshop.repository.UserRepository;
import onlineshop.service.RoleService;
import onlineshop.service.UserService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;
    
    @MockBean
    private RoleService roleService;
    
    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @MockBean
    private ModelMapper modelMapper;
    
    @Test
    void register_NewUser_ShouldReturnUserServiceModel() {
        // Setup
        UserServiceModel userServiceModel = new UserServiceModel();
        User user = new User();
        Role roleUser = new Role();
        roleUser.setName("USER");
        Role roleAdmin = new Role();
        roleAdmin.setName("ADMIN");

        when(modelMapper.map(userServiceModel, User.class)).thenReturn(user);
        when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userRepository.count()).thenReturn(0L);
        when(roleService.findRoleByName("ADMIN")).thenReturn(roleAdmin);
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));
        when(modelMapper.map(user, UserServiceModel.class)).thenReturn(userServiceModel);

        // Execute
        UserService userService = new UserServiceImpl(userRepository, roleService, bCryptPasswordEncoder, modelMapper);
        UserServiceModel savedUserServiceModel = userService.register(userServiceModel);

        // Verify and Assert
        verify(userRepository, times(1)).save(user);
        assertEquals(savedUserServiceModel, userServiceModel);
        assertEquals(savedUserServiceModel.getPassword(), "encodedPassword");
        assertEquals(user.getRole(), roleAdmin);
    }
    
    @Test
    void register_ExistingUser_ShouldReturnUserServiceModel() {
        // Setup
        UserServiceModel userServiceModel = new UserServiceModel();
        User user = new User();
        Role roleUser = new Role();
        roleUser.setName("USER");
        Role roleAdmin = new Role();
        roleAdmin.setName("ADMIN");

        when(modelMapper.map(userServiceModel, User.class)).thenReturn(user);
        when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userRepository.count()).thenReturn(1L);
        when(roleService.findRoleByName("USER")).thenReturn(roleUser);
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));
        when(modelMapper.map(user, UserServiceModel.class)).thenReturn(userServiceModel);

        // Execute
        UserService userService = new UserServiceImpl(userRepository, roleService, bCryptPasswordEncoder, modelMapper);
        UserServiceModel savedUserServiceModel = userService.register(userServiceModel);

        // Verify and Assert
        verify(userRepository, times(1)).save(user);
        assertEquals(savedUserServiceModel, userServiceModel);
        assertEquals(savedUserServiceModel.getPassword(), "encodedPassword");
        assertEquals(user.getRole(), roleUser);
    }
    @Test
    void getUserById_ExistingUserId_ShouldReturnUserServiceModel() {
        // Setup
        UserServiceModel userServiceModel = new UserServiceModel();
        User user = new User();

        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));
        when(modelMapper.map(user, UserServiceModel.class)).thenReturn(userServiceModel);

        // Execute
        UserService userService = new UserServiceImpl(userRepository, roleService, bCryptPasswordEncoder, modelMapper);
        UserServiceModel fetchedUserServiceModel = userService.getUserById(1L);
        // Verify and Assert
        assertEquals(fetchedUserServiceModel, userServiceModel);
    }

    @Test
    void getUserById_NonExistingUserId_ShouldThrowNoSuchElementException() {
        // Setup
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        // Execute and Assert
        UserService userService = new UserServiceImpl(userRepository, roleService, bCryptPasswordEncoder, modelMapper);
        assertThrows(NoSuchElementException.class, () -> userService.getUserById(1L));
    }
}
