package park_su_park.backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import park_su_park.backend.dto.requestBody.CreateUserRequest;
import park_su_park.backend.dto.requestBody.LoginRequest;
import park_su_park.backend.dto.responseBody.ApiResponseBody;
import park_su_park.backend.dto.responseBody.UserData;
import park_su_park.backend.service.AuthService;
import park_su_park.backend.util.message.AuthResponseMessage;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponseBody> signUp(@RequestBody @Valid CreateUserRequest createUserRequest) {
        UserData userData = authService.signUp(createUserRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseBody.success(AuthResponseMessage.SIGN_IN_SUCCESS, userData));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseBody> login(@RequestBody @Valid LoginRequest loginRequest, HttpServletRequest request) {
        authService.login(loginRequest, request);

        return ResponseEntity.ok(ApiResponseBody.success(AuthResponseMessage.LOGIN_SUCCESS, null));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponseBody> logout(HttpServletRequest request) {
        authService.logout(request);

        return ResponseEntity.ok(ApiResponseBody.success(AuthResponseMessage.LOGOUT_SUCCESS, null));
    }
}
