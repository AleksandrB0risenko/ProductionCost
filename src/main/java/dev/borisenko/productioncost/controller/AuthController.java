package dev.borisenko.productioncost.controller;

import dev.borisenko.productioncost.payload.request.SigninRequest;
import dev.borisenko.productioncost.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public interface AuthController {
    @PostMapping("/signin")
    ResponseEntity<?> authenticateUser(@Valid @RequestBody SigninRequest signinRequest);

    @PostMapping("/signup")
        //@PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest);
}