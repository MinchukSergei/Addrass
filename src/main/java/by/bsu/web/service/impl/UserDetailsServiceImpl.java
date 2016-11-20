package by.bsu.web.service.impl;

import by.bsu.web.entity.UserData;
import by.bsu.web.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDataRepository userDataRepository;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserData userData = userDataRepository.findByUserLogin(s);
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority("USER"));

        UserDetails userDetails = new User(userData.getUserLogin(), userData.getUserPassword(), roles);
        return userDetails;
    }
}
