package com.idalavye.petclinic;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(value = 0)
public class H2SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/h2-console/**").authorizeRequests().anyRequest().permitAll(); //Herkes erişebilsin
        http.csrf().disable(); //h2 console'un kendi giriş mekanizması olduğu için spring securtiy'nin csrf özelliğini devre dışı bırakıyoruz
        http.headers().frameOptions().disable();
    }
}
