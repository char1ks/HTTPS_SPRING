package com.example.SpringRedisJWT_demo7.Security;

import com.example.SpringRedisJWT_demo7.Model.Personage;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class PersonageDetails implements UserDetails {

    private Personage personage;

    public PersonageDetails(Personage personage) {
        this.personage = personage;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return personage.getPassword();
    }

    @Override
    public String getUsername() {
        return personage.getUsername();
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

    public Personage getPersonage() {
        return personage;
    }
}
