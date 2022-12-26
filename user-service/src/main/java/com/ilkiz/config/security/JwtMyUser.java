package com.ilkiz.config.security;

import com.ilkiz.manager.IAuthManager;
import com.ilkiz.repository.entity.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtMyUser implements UserDetailsService {
    @Autowired
    IAuthManager authManager;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails loadByAuthid(UserProfile userProfile){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        String role = authManager.findAllByRoleId(userProfile.getAuthid()).getBody().getRole();
        grantedAuthorities.add(new SimpleGrantedAuthority(role));
        return User.builder()
                .username(userProfile.getUsername())
                .password("")
                .accountLocked(false)
                .accountExpired(false)
                .authorities(grantedAuthorities)
                .build();
    }
}
