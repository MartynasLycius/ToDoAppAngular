package com.proit.todoapp.security;

import com.proit.todoapp.entity.Authority;
import com.proit.todoapp.entity.User;
import com.proit.todoapp.repositories.AuthorityRepository;
import com.proit.todoapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by rana on 9/06/22.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            new UsernameNotFoundException("User Not Found with username: " + username);
        }
        List<Authority> roles =authorityRepository.findAllByUsername(username);
        return UserDetailsImpl.build(user, roles);
    }

}