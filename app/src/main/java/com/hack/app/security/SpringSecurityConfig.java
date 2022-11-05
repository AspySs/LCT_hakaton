package com.hack.app.security;

import com.hack.app.security.jwt.JwtSecurityConfigurer;
import com.hack.app.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/auth/register").permitAll()
                .antMatchers("/user/update/{login}").permitAll()
                .antMatchers("/user/find/{login}").permitAll()
                .antMatchers("/ideas/find/all").permitAll()
                .antMatchers("/ideas/find/byTag/{tag}").permitAll()
                .antMatchers("/ideas/find/bySphere/{sphere}").permitAll()
                .antMatchers("/ideas/find/byId/{id}").permitAll()
                .antMatchers("/ideas/delete/{id}").permitAll()
                .antMatchers("/ideas/add").permitAll()
                .antMatchers("/comments/find/all").permitAll()
                .antMatchers("/comments/find/idea/{id}").permitAll()
                .antMatchers("/comments/count/{id}").permitAll()
                .antMatchers("/comments/delete/{id}").permitAll()
                .antMatchers("/reactions/count/idea/true/{id}").permitAll()
                .antMatchers("/reactions/count/idea/false/{id}").permitAll()
                .antMatchers("/reactions/count/comment/true/{id}").permitAll()
                .antMatchers("/reactions/count/comment/false/{id}").permitAll()
                .antMatchers("/reactions/add_idea").permitAll()
                .antMatchers("/reactions/add_comment").permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JwtSecurityConfigurer(jwtTokenProvider));
    }


/*    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("{noop}pwd")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("{noop}pwd")
                .roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("user").password("pwd").roles("USER").and().withUser("admin").password("pwd").roles("ADMIN");

    }*/
}
