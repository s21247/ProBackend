package com.example.probackend.Security;

import com.example.probackend.User.AppUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static com.example.probackend.Security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final AppUserService userService;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder,
                                     AppUserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/api/register/**")
                    .permitAll();
//                .antMatchers("/api/register")
//                .permitAll()
//                .antMatchers("/api/home/**").hasRole(USER.name())
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login").permitAll()
//                .defaultSuccessUrl("/courses",true)
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
//                .clearAuthentication(true)
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID")
//                .logoutSuccessUrl("/login");
        //todo remember me?
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails wojtekUser = User.builder()
                .username("wojtek")
                .password(passwordEncoder.encode("123"))
                //.roles(USER.name())  //ROLE_USER
                .authorities(USER.getGrantedAuthorities())
                .build();
        UserDetails adminUser = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("123"))
                //.roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();
        UserDetails admin1 = User.builder()
                .username("admin1")
                .password(passwordEncoder.encode("123"))
                //.roles(ADMINTrainee.name()) //ROLE_ADMINTRAINEE
                .authorities(ADMINTRAINEE.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(
                wojtekUser,
                adminUser,
                admin1
        );
    }

}
