package com.factsdemo.guineaPigFacts.config;

import com.factsdemo.guineaPigFacts.securityFilters.AuthenticationFilter;
import com.factsdemo.guineaPigFacts.securityFilters.AuthorizationFilter;
import com.factsdemo.guineaPigFacts.services.UserService;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImplementation userDetailsService;

    /**
     * Allows non-logged in users to view all fact URLS and to send a POST request to /user/
     * All other users are sent to the login page
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/fact", "/fact/home").permitAll()
                .antMatchers(HttpMethod.POST,"/user/").permitAll()
                .antMatchers(HttpMethod.PUT, "/fact/").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/fact/").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/fact/all").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilter(new AuthenticationFilter(authenticationManager()))
                .addFilter(new AuthorizationFilter(authenticationManager()))
                .formLogin()
                .loginPage("/user/")
                .permitAll()
                .and()
                .sessionManagement().disable();;
    }
    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService);
    }
}
