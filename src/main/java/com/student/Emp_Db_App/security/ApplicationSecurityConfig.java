package com.student.Emp_Db_App.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.student.Emp_Db_App.security.ApplicationUserRole.BOSS;
import static com.student.Emp_Db_App.security.ApplicationUserRole.MANAGER;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails managerUser = User.builder()
                .username("manager")
                .password(passwordEncoder.encode("passwordManager"))
                .roles(MANAGER.name())
                .build();
        UserDetails bossUser = User.builder()
                .username("boss")
                .password(passwordEncoder.encode("passwordBoss"))
                .roles(BOSS.name())
                .build();
        return new InMemoryUserDetailsManager(
                managerUser,
                bossUser
        );
    }
}
