package com.travelplanner.core.user.adapter.in.web;
import com.travelplanner.core.user.adapter.in.web.dto.login.UserLoginRequestDTO;
import com.travelplanner.core.user.adapter.in.web.dto.login.UserLoginResponseDTO;
import com.travelplanner.core.user.adapter.in.web.dto.register.UserRegisterRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterRequestDTO request) {
        // save user and return JWT
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> login(@RequestBody UserLoginRequestDTO request) {
        // authenticate and return JWT
        return null;
    }
}
