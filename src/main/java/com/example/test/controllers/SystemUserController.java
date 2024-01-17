package com.example.test.controllers;

import com.example.test.configs.JwtGenerator;
import com.example.test.constants.UserType;
import com.example.test.dtos.LogIn;
import com.example.test.dtos.UserDto;
import com.example.test.entities.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.test.services.SystemUserService;

import java.util.Optional;

@RestController
public class SystemUserController {
    @Autowired
    private JwtGenerator jwtGenerator;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping(value = "/logIn", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> logIn(@RequestBody LogIn user) {

        String account = user.getAccount();
        String password = user.getPassword();

        Optional<SystemUser> logInUser = systemUserService.findByAccount(account);

        if (logInUser.isPresent()) {
            SystemUser tmp = logInUser.get();
            if (encoder.matches(password, tmp.getPassword())) {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(tmp.getName(), password));

                UserDto userDto = new UserDto();
                userDto.setAccount(tmp.getAccount());
                userDto.setName(tmp.getName());

                String token = jwtGenerator.generateToken(authentication, UserType.ADMIN.toString());

                return ResponseEntity.status(HttpStatus.OK).header("Authorization", "Bearer " + token).body(userDto);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SystemUser> register(@RequestBody SystemUser user) {
        try {
            SystemUser newUser = systemUserService.save(user);
            return ResponseEntity.status(HttpStatus.OK).body(newUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

