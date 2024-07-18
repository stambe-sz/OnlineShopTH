package onlineshop.config;

import lombok.RequiredArgsConstructor;
import onlineshop.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    private final UserService userService;

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
                                .requestMatchers("/css/**", "/images/**", "/login", "/users/login", "/users/register", "/")
                                .permitAll()
//                                .requestMatchers("/products/add").hasRole("ADMIN")
                                .anyRequest()
                                .authenticated())
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/users/login")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/")
                                .failureForwardUrl("/users/login-error")
                                .permitAll())
                .logout(logout -> {
                    logout
                            .logoutUrl("/users/logout")
                            .logoutSuccessUrl("/")
                            .invalidateHttpSession(true);
                });

        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);
        return http.build();
    }

    /*@Bean
    public CustomUserDetailsService customUserDetailsService(UserRepository userRepository) {
        return new CustomUserDetailsService(userRepository);
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//        String username = getCurrentUsername();
//        UserServiceModel user = userService.getUserByUsername(username) ;
//
//        userDetailsManager.createUser((UserDetails) user);
//        return userDetailsManager;
//    }

//    public String getCurrentUsername(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || !authentication.isAuthenticated()){
//            return null;
//        }
//
//        Object principal = authentication.getPrincipal();
//        if (principal instanceof UserDetails){
//            return ((UserDetails) principal).getUsername();
//        }else {
//            return principal.toString();
//        }
//    }
}
