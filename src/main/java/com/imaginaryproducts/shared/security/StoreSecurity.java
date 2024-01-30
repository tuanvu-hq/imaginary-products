package com.imaginaryproducts.shared.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class StoreSecurity {
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails employee = User.builder().username("employee").password("{noop}employee").roles("EMPLOYEE").build();
        UserDetails manager = User.builder().username("manager").password("{noop}manager").roles("EMPLOYEE", "MANAGER").build();
        UserDetails admin = User.builder().username("admin").password("{noop}admin").roles("EMPLOYEE", "MANAGER", "ADMIN").build();

        return new InMemoryUserDetailsManager(employee, manager, admin);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(configure ->
                        configure
                                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/", "/home", "/login", "/css/**", "/js/**", "/api/**").permitAll()
                                .requestMatchers("/dashboard").hasRole("EMPLOYEE")
                                .requestMatchers("/products/**").hasRole("MANAGER")
                                .anyRequest().authenticated())
                .formLogin(form ->
                        form
                                .loginPage("/login")
                                .loginProcessingUrl("/authenticateTheUser")
                                .defaultSuccessUrl("/home", true)
                                .permitAll()
                )
                .logout(LogoutConfigurer::permitAll)
                .exceptionHandling(configure ->
                        configure
                                .accessDeniedPage("/access-denied")
                );
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.cors(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();

        resolver.setPrefix("templates/");
        resolver.setSuffix(".html");

        return resolver;
    }
}
