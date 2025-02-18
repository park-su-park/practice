package park_su_park.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import park_su_park.backend.domain.User;
import park_su_park.backend.dto.requestDto.RequestUserDto;
import park_su_park.backend.dto.responseDto.ResponseUserDto;
import park_su_park.backend.exception.ValidateException;
import park_su_park.backend.repository.UserRepository;
import park_su_park.backend.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    //C
    @PostMapping("/sign-in")
    public ResponseEntity<ResponseUserDto> createUser(
        @Valid @RequestBody RequestUserDto requestUserDto) {
        ResponseUserDto responseUserDto = userService.save(requestUserDto);
        return ResponseEntity.ok(responseUserDto);
    }

    //R
    @GetMapping
    public ResponseEntity<ResponseUserDto> readUser(
        @RequestParam(name = "username", required = false) String username,
        @RequestParam(name = "email", required = false) String email) {

        if (username != null && email != null) {
            throw new ValidateException("username과 email 중 하나만 입력하세요");
        }

        User user = userService.findUserByUsernameOrEmail(username, email);
        ResponseUserDto responseUserDto = ResponseUserDto.of(user);

        return ResponseEntity.ok(responseUserDto);
    }


    //U
    @PostMapping("/update/{userId}")
    public ResponseEntity<ResponseUserDto> updateUser(@PathVariable(name = "userid") Long userId,
        @Valid @RequestBody RequestUserDto requestUserDto) {
        ResponseUserDto responseUserDto = userService.update(userId, requestUserDto);
        return ResponseEntity.ok(responseUserDto);
    }


    //D
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
        return ResponseEntity.ok("message : 유저 삭제 성공");
    }
}
