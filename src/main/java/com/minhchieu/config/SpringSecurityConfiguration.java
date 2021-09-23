package com.minhchieu.config;

import com.minhchieu.serviceimpl.CustomAuthenticateService;
import com.minhchieu.service.CustomJwtAuthenticationFilter;
import com.minhchieu.service.JwtAuthenticationEntryPoint;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@OpenAPIDefinition(
    info = @Info(
        title = "Fitness API Documentation - Minh Chieu Solution",
        description = "RESTful API for training intern.",
        contact = @Contact(),
        license = @License()
    )
)
@SecurityScheme(
    name = "api",
    scheme = "bearer",
    bearerFormat = "JWT",
    type = SecuritySchemeType.HTTP,
    in = SecuritySchemeIn.HEADER
)
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String[] AUTH_WHITELIST = {
        // -- Swagger UI v3 (OpenAPI)
        "/v1/docs",
        "/v1/api-docs/**",
        "/v1/api-docs//swagger-config",
        "/v1/swagger-ui.html",
        "/v1/swagger-ui/**",
        // other public endpoints of your API may be appended to this array
        // -- Course public endpoints
        "/course/*",
        "/public/post"
    };


    @Autowired
    private CustomAuthenticateService customAuthenticateService;

    @Autowired
    private CustomJwtAuthenticationFilter customJwtAuthenticationFilter;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
//            .authorizeRequests().antMatchers("/helloadmin").hasRole("ADMIN")
//            .antMatchers("/hellouser").hasAnyRole("USER","ADMIN")
            .antMatchers(AUTH_WHITELIST).permitAll()
            .antMatchers("/authenticate/register", "/authenticate/login", "/account/create" ,"/courses").permitAll().anyRequest().authenticated()
            .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).
            and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
            and().addFilterBefore(customJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customAuthenticateService);
    }
}
