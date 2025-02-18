package park_su_park.backend.controller;

import jakarta.validation.Valid;
import java.util.Optional;
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

        User user = findUserByUsernameOrEmail(username, email);
        ResponseUserDto responseUserDto = ResponseUserDto.of(user);

        return ResponseEntity.ok(responseUserDto);
    }

    /**
     * username 또는 email을 이용해 사용자 조회
     */
    private User findUserByUsernameOrEmail(String username, String email) {
        return Optional.ofNullable(
            username != null ? userRepository.findByUsername(username).orElse(null) :
                email != null ? userRepository.findByEmail(email).orElse(null) :
                    null
        ).orElseThrow(() -> new ValidateException("파라미터를 입력하세요"));
    }

    //U
    @PostMapping("/update/{id}")
    public ResponseEntity<ResponseUserDto> updateUser(@PathVariable(name = "userid") Long id,
        @Valid @RequestBody RequestUserDto requestUserDto) {
        ResponseUserDto responseUserDto = userService.updateUser(id, requestUserDto);
        return ResponseEntity.ok(responseUserDto);
    }


    //D
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("message : 유저 삭제 성공");
    }
}
