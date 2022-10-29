package com.hack.app.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String login;

    private String password;

    @Column(name="name", nullable = true)
    private String name;

    @Column(name="info", nullable = true)
    private String info;

    @Column(name="status", nullable = true)
    private String status;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    public User() {
    }

    public User(String login, String password, List<String> roles) {
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getName(){return name;}
    public String getInfo(){return info;}
    public String getStatus(){return status;}
    public void setInfo(String info){this.info = info;}
    public void setName(String name){this.name = name;}
    public void setStatus(String status){this.status = status;}


    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
