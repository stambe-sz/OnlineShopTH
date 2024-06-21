package onlineshop.config;

import lombok.RequiredArgsConstructor;
import onlineshop.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfiguration {
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
                                .requestMatchers("/products/add")
                                .hasRole("ADMIN")
                                .anyRequest()
                                .authenticated()
                )

                .formLogin(formLogin ->
                        formLogin.loginPage("/users/login")
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

    //    @Bean
//    public UserDetailsService userDetailsService(UserRepository userRepository){
//        return new OnlineShopUserDetailsService(userRepository);
//    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    @Bean
    public UserDetailsService userDetailsService(AuthenticationManagerBuilder auth) {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        ArrayList<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();

        SimpleGrantedAuthority role1 = new SimpleGrantedAuthority("ADMIN");
        SimpleGrantedAuthority role2 = new SimpleGrantedAuthority("USER");

        roles.add(role1);
        roles.add(role2);

        userDetailsManager.createUser(User.withUsername(userService.getUserByUsername()));
    }
    
}
