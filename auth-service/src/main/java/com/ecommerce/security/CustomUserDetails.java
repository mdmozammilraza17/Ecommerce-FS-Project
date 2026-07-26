package com.ecommerce.security;

import com.ecommerce.entity.registration.UserEntity;
import com.ecommerce.enums.Role;
import com.ecommerce.enums.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final UserEntity userEntity;

    public CustomUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + userEntity.getRole().name()));
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getEmailAddress();
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
        return userEntity.getStatus() == UserStatus.ACTIVE;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public Long getUserId() {
        return userEntity.getId();
    }

    public String getFirstName() {
        return userEntity.getFirstName();
    }

    public String getLastName() {
        return userEntity.getLastName();
    }

    public String getEmailAddress() {
        return userEntity.getEmailAddress();
    }

    public Role getRole() {
        return userEntity.getRole();
    }

}
