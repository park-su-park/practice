package park_su_park.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import park_su_park.backend.dto.requestDto.RequestToDoDto;
import park_su_park.backend.dto.responseDto.ErrorResponseDto;
import park_su_park.backend.dto.responseDto.ResponseDto;
import park_su_park.backend.dto.responseDto.ResponseToDoDto;
import park_su_park.backend.logIn.LogInterface;
import park_su_park.backend.repository.UserRepository;
import park_su_park.backend.service.ToDoService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/to-do")
public class ToDoController {

    private final ToDoService toDoService;
    private final UserRepository userRepository;

    //C
    @PostMapping("/create")
    public ResponseEntity<Object> createToDo(
        @SessionAttribute(name = LogInterface.LOGIN_USER, required = false) Long userId,
        @Valid @RequestBody
        RequestToDoDto requestToDoDto) {
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponseDto("로그인 정보가 유효하지 않습니다."));
        } else {
            ResponseDto responseDto = toDoService.save(userId, requestToDoDto);
            return ResponseEntity.ok(responseDto);
        }
    }

    //R 유저의 할일 모두 부르기
    @GetMapping
    public ResponseEntity<Object> readToDoByUser(
        @SessionAttribute(name = LogInterface.LOGIN_USER, required = false) Long userId) {
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponseDto("로그인 정보가 유효하지 않습니다."));
        }

        ResponseDto responseDto = toDoService.findByUser(userId);
        return ResponseEntity.ok(responseDto);
    }

    //U
    @PatchMapping("/{toDoId}")
    public ResponseEntity<Object> updateToDo(
        @SessionAttribute(name = LogInterface.LOGIN_USER, required = false) Long userId,
        @PathVariable Long toDoId,
        @Valid @RequestBody RequestToDoDto requestToDoDto) {
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponseDto("로그인 정보가 유효하지 않습니다."));
        }
        ResponseToDoDto responseToDoDto = toDoService.update(userId, toDoId, requestToDoDto);
        return ResponseEntity.ok(new ResponseDto("toDo 수정 완료", responseToDoDto));
    }

    //D
    @DeleteMapping("/{toDoId}")
    public ResponseEntity<Object> deleteToDo(@SessionAttribute(name = LogInterface.LOGIN_USER, required = false) Long userId,
        @PathVariable Long toDoId){
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponseDto("로그인 정보가 유효하지 않습니다."));
        }
        toDoService.delete(userId, toDoId);
        return ResponseEntity.ok().build();
    }


}
