package dev.borisenko.productioncost.controller.impl;

import dev.borisenko.productioncost.controller.AuthController;
import dev.borisenko.productioncost.model.User;
import dev.borisenko.productioncost.payload.request.SigninRequest;
import dev.borisenko.productioncost.payload.request.SignupRequest;
import dev.borisenko.productioncost.payload.response.JwtResponse;
import dev.borisenko.productioncost.payload.response.MessageResponse;
import dev.borisenko.productioncost.repository.UserRepo;
import dev.borisenko.productioncost.security.jwt.JwtUtils;
import dev.borisenko.productioncost.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthControllerImpl implements AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtils jwtUtils;

    @Override
    public ResponseEntity<?> registerUser(SignupRequest signupRequest) {
        if (userRepo.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Ошибка: пользователь с таким логином уже существует"));
        }

        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        //user.setFio(signupRequest.getFio());
        //user.setDepartmentByDepartmentId(signupRequest.getDepartmentByDepartmentId());

        userRepo.save(user);
        return new ResponseEntity<>(new MessageResponse("Пользователь успешно зарегистрирован"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> authenticateUser(SigninRequest signinRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        /*List<String> departmentStrs = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();*/

        //Department department = departmentRepository.findByDepartmentName(departmentStrs.get(0)).get();
        return new ResponseEntity<>(new JwtResponse(jwt, userDetails.getUsername()/*, department*/), HttpStatus.OK);
    }
}
