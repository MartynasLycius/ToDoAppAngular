package com.proit.todoapp.web.resource;

import com.proit.todoapp.entity.Authority;
import com.proit.todoapp.entity.User;
import com.proit.todoapp.enumeration.Role;
import com.proit.todoapp.mapper.JwtResponse;
import com.proit.todoapp.mapper.LoginRequest;
import com.proit.todoapp.mapper.Response;
import com.proit.todoapp.repositories.AuthorityRepository;
import com.proit.todoapp.repositories.UserRepository;
import com.proit.todoapp.security.JwtUtils;
import com.proit.todoapp.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rana on 9/06/22.
 */
@RestController
@RequestMapping("/api/")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse response;
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if (user != null) {
            response = authenticateUserCredential(loginRequest, user);
        } else {
            response =  new JwtResponse(false);
        }
        return ResponseEntity.ok(response);
    }

    private JwtResponse authenticateUserCredential(LoginRequest loginRequest, User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = new ArrayList<String>();
        for (GrantedAuthority grantedAuthority: userDetails.getAuthorities()) {
            roles.add(grantedAuthority.getAuthority());
        }
        return new JwtResponse(jwt, userDetails.getUsername(), user.getFullName());
    }

    @RequestMapping(value = "/user/sign-up",  method = RequestMethod.POST)
    public ResponseEntity<?> register(@Valid @RequestBody User user) {
        User exUser = userRepository.findByUsername(user.getUsername());
        if (exUser == null) {
            Role role = user.getRole();
            user = userRepository.save(user);
            Authority authority = new Authority(user.getUsername(), role.getText());
            authorityRepository.save(authority);
            return new ResponseEntity<Response>(new Response("User Successfully Created.", user), HttpStatus.OK);
        } else {
            return new ResponseEntity<Response>(new Response("User Already Exist.", user) , HttpStatus.BAD_REQUEST);
        }

    }
}
