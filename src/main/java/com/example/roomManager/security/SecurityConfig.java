package com.example.roomManager.security;

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
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //this is added so we can give it a parameter and make request on static resources available
    private static final String[] PUBLIC_MATCHERS = {
            "/webjars/**",
            "/css/**",
            "/js/**",
            "/images/**",
            "/"
    };


    //This method is responsible with the authentication of things.
    //In this method we select the inMemoryAuth in order to add users to use in the log in form in memory .
    //Then we use the password eccoder to encrypt the password
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("password")).roles("USER").and()
                .withUser("admin").password(passwordEncoder().encode("password")).roles("USER", "ADMIN");
    }

    //This method is responsible for configuring all the requests and mappings.What this does is the following.
    //we create a custom login page by adding .and().formLogin().loginPage("/login") and we use permitAll()
    //to permit all users to access it.This custom login page has to be created and served by a controller for
    //it to work.What happens behind the scenes is this.Once we add the .loginPage("/login") method , sprin security
    //will automatically send a get request on that path in order to retrieve the resource.
    // So we need to add a new file to the templates folder and create a Controller with a /login
    //@GetMapping wich returns the file name that we created.Next we add the paths for logout.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/resources/**", "/js/**", "/css/**", "/images/**", "/fonts/**", "/scss/**", "/index", "/", "/login").permitAll()
                //this is enableing request on static resources wich are blocked by spring security automatically.
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                //This method here automatically redirects the user after login to the /home endpoint from
                //the Controller wich serves him the needed resource.
                .defaultSuccessUrl("/home", true)
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .logoutUrl("/signout")
                .and()
                .csrf().disable();
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web
//                .ignoring()
//                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/assets/**", "/fonts/**", "/dis/**", "/vendor1/**");
//
//    }


    //Basic password encoder using bcrypt for encryption.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
