package com.learning.basics.security;

import com.learning.basics.filter.CustomAuthFilter;
import com.learning.basics.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    CustomAuthFilter customAuthFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().
                antMatchers("/admin").hasRole("ADMIN").
                antMatchers("/user").hasAnyRole("ADMIN", "USER").
                antMatchers("/authenticate").permitAll().
                antMatchers("/").permitAll().
                antMatchers("/h2-console/**").permitAll().
                and().sessionManagement().
                sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().addFilterBefore(customAuthFilter, UsernamePasswordAuthenticationFilter.class);

        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }


    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().
                dataSource(dataSource).
                usersByUsernameQuery("select username, password, enabled from my_users " +
                        "where username=?").
                authoritiesByUsernameQuery("select username, authority from my_authorities " +
                        "where username=?");
    }*/

     /*@Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }*/


    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().
                dataSource(dataSource).
                withDefaultSchema().
                withUser(User.withUsername("dummy1").password("dummy1").roles("USER")).
                withUser(User.withUsername("dummy2").password("dummy2").roles("ADMIN", "USER"));
    }*/


/* @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                antMatchers("/admin").hasRole("ADMIN").
                antMatchers("/user").hasAnyRole("ADMIN", "USER").
                antMatchers("/").permitAll().
                and().formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

       auth.inMemoryAuthentication().withUser("dummy").password("dummy").roles("USER")
       .and().withUser("dummy1").password("dummy1").roles("ADMIN");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }*/
}
