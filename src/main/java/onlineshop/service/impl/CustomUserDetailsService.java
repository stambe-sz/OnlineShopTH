package onlineshop.service.impl;

import lombok.RequiredArgsConstructor;
import onlineshop.model.OnlineShopUserDetails;
import onlineshop.model.entity.User;
import onlineshop.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findUserByUsername(username)
                .map(CustomUserDetailsService::map)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

    }

    private static UserDetails map(User user) {

        return new OnlineShopUserDetails(
                user.getUsername(),
                user.getPassword(),
                List.of());
    }
}
