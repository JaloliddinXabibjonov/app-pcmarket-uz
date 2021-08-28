package uz.pcmarket.apppcmarketuz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("SuperAdmin").password(passwordEncoder().encode("000")).roles("SUPER_ADMIN")
                .and()
                .withUser("Moderator").password(passwordEncoder().encode("111")).roles("MODERATOR")
                .and()
                .withUser("Operator").password(passwordEncoder().encode("222")).roles("OPERATOR");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/product/*").hasAnyRole("OPERATOR","SUPER_ADMIN")
                .antMatchers(HttpMethod.POST, "/api/product/*").hasAnyRole("MODERATOR","SUPER_ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/product/*").hasAnyRole("MODERATOR","SUPER_ADMIN")
                .antMatchers("/api/product/**").hasRole("SUPER_ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
