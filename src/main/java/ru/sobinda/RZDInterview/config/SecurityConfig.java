package ru.sobinda.RZDInterview.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Pasha").password(encoder().encode("Pasha1234")).roles("USER")
                .and()
                .withUser("Dima").password(encoder().encode("Dima1234")).roles("GUEST")
                .and()
                .withUser("Olga").password(encoder().encode("Olga1234")).roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().and()
//                .authorizeHttpRequests().antMatchers("/nomenclatures/**").hasAnyRole("USER, ADMIN")
//                .and()
//                .authorizeHttpRequests().antMatchers("/full_scale/**").hasAnyRole("USER, ADMIN")
//                .and()
//                .authorizeHttpRequests().antMatchers("/scale/**").hasAnyRole("USER, ADMIN")
//                .and()
//                .authorizeHttpRequests().antMatchers("/station/**").hasAnyRole("USER, ADMIN")
//                .and()
//                .authorizeHttpRequests().antMatchers("/wagon_passport/**").hasAnyRole("USER, ADMIN")
//                .and()
                .authorizeHttpRequests().anyRequest().authenticated();
    }
}

