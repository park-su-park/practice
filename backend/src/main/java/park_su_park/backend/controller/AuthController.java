package park_su_park.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import park_su_park.backend.domain.User;
import park_su_park.backend.dto.requestBody.CreateUserRequest;
import park_su_park.backend.dto.responseBody.UserDataResponse;
import park_su_park.backend.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/sign-in")
    public ResponseEntity<UserDataResponse> signIn(@RequestBody @Valid CreateUserRequest createUserRequest) {
        Long savedUserId = userService.join(createUserRequest);
        User user = userService.findUserById(savedUserId);
        return ResponseEntity.ok(UserDataResponse.createUserDataResponse(user));
    }
}
