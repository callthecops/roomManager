package com.example.roomManager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



//security configuration class for implementing spring security on urls
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;
    //for handling user success handler
    @Autowired
    private CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;
    @Override
    //this configuration is for handling user requests
    protected void configure(HttpSecurity http)  {
        try {
            http
                    .authorizeRequests()
                    .antMatchers("/login").permitAll()
                    .anyRequest()
                    .authenticated().and().csrf().disable().formLogin().successHandler(customizeAuthenticationSuccessHandler)
                    .loginPage("/login").failureUrl("/login?error=true")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .and().logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/logout.done").deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
                    .logoutSuccessUrl("/login").and().exceptionHandling().accessDeniedPage("/403");
            http.sessionManagement( ).maximumSessions(1). maxSessionsPreventsLogin(false);
	       /* http.sessionManagement( ).sessionFixation( ).migrateSession( )
	                .sessionAuthenticationStrategy( registerSessionAuthStr( ) );*/

        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("Exception here");
        }
    }


    //this method allows static resources to be neglected by spring security
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**","/assets/**","/fonts/**","/dis/**","/vendor1/**","/assets2/**");
    }



    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
    @Bean
    public RegisterSessionAuthenticationStrategy registerSessionAuthStr( ) {
        return new RegisterSessionAuthenticationStrategy( sessionRegistry( ) );
    }
    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)  {
        //BCryptPasswordEncoder encoder = passwordEncoder();
        //auth.inMemoryAuthentication().withUser("logan@yahoo.com").password(encoder.encode("admin")).roles("user");
        try {
            //The authentication manager loads the userdetails from DB and checks agains the data provided by the
            //user during login.
            auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
        } catch (Exception e) {

            System.out.println("Login Failed");
        }
    }


}