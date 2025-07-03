package com.travelplanner.core.user.adapter.in.web;
import com.travelplanner.core.user.adapter.in.web.dto.login.UserLoginRequestDTO;
import com.travelplanner.core.user.adapter.in.web.dto.login.UserLoginResponseDTO;
import com.travelplanner.core.user.adapter.in.web.dto.register.UserRegisterRequestDTO;
import com.travelplanner.core.user.adapter.in.web.dto.register.UserRegisterResponseDTO;
import com.travelplanner.core.user.domain.port.in.UserCommandUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserCommandUseCase userCommandUseCase;

    public UserController(UserCommandUseCase userCommandUseCase) {
        this.userCommandUseCase = userCommandUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDTO> register(@RequestBody UserRegisterRequestDTO request) {
        var model = userCommandUseCase.register(request.toDomain());
        return ResponseEntity.ok(UserRegisterResponseDTO.fromDomain(model));
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> login(@RequestBody UserLoginRequestDTO request) {
        var model = userCommandUseCase.login(request.toDomain());
        return ResponseEntity.ok(UserLoginResponseDTO.fromDomain(model));
    }
}
