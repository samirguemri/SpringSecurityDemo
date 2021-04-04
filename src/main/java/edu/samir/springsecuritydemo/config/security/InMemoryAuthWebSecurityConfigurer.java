package edu.samir.springsecuritydemo.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class InMemoryAuthWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    // Authentication Manager
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser(User.withUsername("zeyn")
                        .password(passwordEncoder().encode("test123"))
                                .roles("USER"))
                .withUser(User.withUsername("samir")
                        .password(passwordEncoder().encode("test321"))
                                .roles("USER","ADMIN"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configurations for authorize requests and form login
    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        // Configuration to Authorize Requests
        http
            .csrf().disable()
            .authorizeRequests()
                /*.antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/anonymous*").anonymous()
                .antMatchers("/login*").permitAll()*/
                .anyRequest().authenticated()

        // Configuration for Form Login
            .and()
            .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/perform_login")
                /*.defaultSuccessUrl("/home", true)
                .failureUrl("//login?error=true")
                .failureHandler(authenticationFailureHandler())
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(logoutSuccessHandler())*/
                .permitAll();
    }
}
