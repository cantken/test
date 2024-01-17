package com.example.test.services;

import com.example.test.constants.UserType;
import com.example.test.entities.SystemUser;
import com.example.test.repositories.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class SystemUserDetailService implements UserDetailsService {
    @Autowired
    private SystemUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser user = userRepository.findByName(username).orElseThrow(()-> new UsernameNotFoundException("Admin Username "+ username+ "not found"));

        SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority(UserType.ADMIN.toString());
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(adminAuthority);
        return new User(user.getName(), user.getPassword(), authorities);
    }
}
