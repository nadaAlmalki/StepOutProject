package com.example.final_project.Config;

import com.example.final_project.Services.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider =new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(myUserDetailsService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST,"/api/v1/user","/api/v1/user/register").permitAll()
                .requestMatchers("/api/v1/coach/**").hasAuthority("Admin")
                .requestMatchers("/api/v1/customer/**").hasAuthority("Admin")
                .requestMatchers("/api/v1/order/**").hasAuthority("Admin")
                .requestMatchers("/api/v1/review/**").hasAuthority("Admin")
                .requestMatchers("/api/v1/order","/api/v1/order/add").hasAuthority("Customer")
                .requestMatchers("/api/v1/review", "/api/v1/review/add", "/api/v1/review/delete/{id}").hasAuthority("Customer")
                .requestMatchers("/api/v1/training/**").hasAuthority("Admin")
                .requestMatchers("/api/v1/training","/api/v1/training/add","/api/v1/training/update/{id}","/api/v1/training/delete/{id}").hasAuthority("Coach")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/user/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
    }

}
