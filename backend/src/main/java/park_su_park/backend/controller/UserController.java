package park_su_park.backend.controller;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import park_su_park.backend.domain.User;
import park_su_park.backend.exception.BadRequestParamException;
import park_su_park.backend.exception.NotExistUserException;
import park_su_park.backend.repository.UserRepository;
import park_su_park.backend.requestDto.RequestUserDto;
import park_su_park.backend.responseDto.ErrorResponse;
import park_su_park.backend.responseDto.ResponseDto;
import park_su_park.backend.responseDto.ResponseUserDto;
import park_su_park.backend.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    //C
    @PostMapping("/sign-in")
    public ResponseEntity<ResponseDto> createUser(
        @Valid @RequestBody RequestUserDto requestUserDto) {
        ResponseUserDto responseUserDto = userService.save(requestUserDto);
        ResponseDto responseDto = new ResponseDto("유저등록 성공", responseUserDto);
        return ResponseEntity.ok(responseDto);
    }

    //R
    @GetMapping
    public ResponseEntity<ResponseDto> readUser(
        @RequestParam(name = "username", required = false) String username,
        @RequestParam(name = "email", required = false) String email) {
        //username email 동시 조회 불가
        if (username != null && email != null) {
            throw new BadRequestParamException("username과 email 중 하나만 입력하세요");
        }
        //username으로 조회
        else if (username != null) {
            Optional<User> byUsername = userRepository.findByUsername(username);
            if (byUsername.isPresent()) {
                ResponseUserDto responseUserDto = ResponseUserDto.of(byUsername.get());
                ResponseDto responseDto = new ResponseDto("유저 조회 성공", responseUserDto);
                return ResponseEntity.ok(responseDto);
            } else {
                throw new NotExistUserException("등록되지 않은 사용자입니다.");
            }
            //email 조회
        } else if (email != null) {
            Optional<User> byEmail = userRepository.findByEmail(email);
            if (byEmail.isPresent()) {
                ResponseUserDto responseUserDto = ResponseUserDto.of(byEmail.get());
                ResponseDto responseDto = new ResponseDto("유저 조회 성공", responseUserDto);
                return ResponseEntity.ok(responseDto);
            } else {
                throw new NotExistUserException("등록되지 않은 사용자 입니다.");
            }
            //파라미터가 없는 경우
        } else {
            throw new BadRequestParamException("파라미터를 입력하세요");
        }
    }

    //U
    @PostMapping("/update/{id}")
    public ResponseEntity<ResponseDto> updateUser(@PathVariable(name = "userid") Long id,
        @Valid @RequestBody RequestUserDto requestUserDto) {
        userService.updateUser(id, requestUserDto);
        return ResponseEntity.ok(new ResponseDto("update 완료", requestUserDto));
    }


    //D
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("message : 유저 삭제 성공");
    }
}
