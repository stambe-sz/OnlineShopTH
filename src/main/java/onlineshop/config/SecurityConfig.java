package onlineshop.config;

import onlineshop.repository.UserRepository;
import onlineshop.service.impl.OnlineShopUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(auth ->
//                auth
//                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/css/**")).permitAll()
//                        .anyRequest()
//                        .authenticated()
//        ).formLogin(formLogin -> {
//            formLogin
//                    .loginPage("/users/login")
//                    .usernameParameter("username")
//                    .passwordParameter("password")
//                    .defaultSuccessUrl("/")
//                    .failureForwardUrl("/users/login-error");
//        }).logout(logout->{
//            logout
//                    .logoutUrl("/users/logout")
//                    .logoutSuccessUrl("/")
//                    .invalidateHttpSession(true);
//        });

        http.authorizeHttpRequests((auth) ->
                auth
                        .requestMatchers("/css/**", "/login", "/users/login", "/users/register", "/")
                        .permitAll()
                        .requestMatchers("/products/add").hasRole("ADMIN")
                        .anyRequest()
                        .authenticated())
                .logout(logout->{
                    logout
                            .logoutUrl("/users/logout")
                            .logoutSuccessUrl("/")
                            .invalidateHttpSession(true);
                });

        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);
        return http.build();
    }
//    @Bean
//    public UserDetailsService userDetailsService(UserRepository userRepository){
//        return new OnlineShopUserDetailsService(userRepository);
//    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
}
