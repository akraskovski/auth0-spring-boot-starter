package com.kraskovski.auth0.config;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configuration class using Auth0.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${auth0.apiAudience}")
    private String apiAudience;
    @Value("${auth0.issuer}")
    private String issuer;
    @Value("${auth0.clientSecret}")
    private String secret;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        JwtWebSecurityConfigurer
                .forRS256(apiAudience, issuer)
                .configure(http)
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/login").permitAll()
                .anyRequest().authenticated();
    }
}
