package onlineshop.service.impl;

import lombok.RequiredArgsConstructor;
import onlineshop.model.entity.User;
import onlineshop.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
@RequiredArgsConstructor
public class OnlineShopUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        userRepository
                .findUserByUsername(username)
                .map()
        return null;
    }
    private UserDetails map(User user){

    }
}
