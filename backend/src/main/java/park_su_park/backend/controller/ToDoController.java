package park_su_park.backend.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import park_su_park.backend.domain.ToDo;
import park_su_park.backend.dto.requestBody.CreateToDoRequest;
import park_su_park.backend.dto.responseBody.ToDoDataResponse;
import park_su_park.backend.exception.ForbiddenAccessException;
import park_su_park.backend.service.ToDoService;
import park_su_park.backend.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/to-do")
public class ToDoController {

    private static final String FORBIDDEN_ACCESS_ACTION = "해당 댓글을 수정 및 삭제할 권한이 없습니다";
    private final ToDoService toDoService;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<ToDoDataResponse> createToDo(@RequestBody @Valid CreateToDoRequest createToDoRequest, HttpSession session) {
        Long userId = userService.getUserIdFromSession(session);

        Long toDoId = toDoService.join(createToDoRequest, userId);

        ToDoDataResponse toDoDataResponse = ToDoDataResponse.create(toDoService.findToDo(toDoId));
        return ResponseEntity.status(HttpStatus.CREATED).body(toDoDataResponse);
    }

    @GetMapping("/{toDoId}")
    public ResponseEntity<ToDoDataResponse> getToDo(@PathVariable Long toDoId) {
        ToDo toDo = toDoService.findToDo(toDoId);

        ToDoDataResponse toDoDataResponse = ToDoDataResponse.create(toDo);
        return ResponseEntity.ok(toDoDataResponse);
    }

    @PatchMapping("/{toDoId}")
    public ResponseEntity<ToDoDataResponse> updateToDo(@PathVariable Long toDoId, @RequestBody @Valid CreateToDoRequest updateToDoRequest, HttpSession session) {
        Long userId = userService.getUserIdFromSession(session);
        ToDo toDo = toDoService.findToDo(toDoId);
        validateUserAuthorization(userId, toDo);

        toDoService.updateToDo(updateToDoRequest, toDo);

        ToDoDataResponse toDoDataResponse = ToDoDataResponse.create(toDo);
        return ResponseEntity.ok(toDoDataResponse);
    }

    @DeleteMapping("/{toDoId}")
    public ResponseEntity<Void> deleteToDo(@PathVariable Long toDoId, HttpSession session) {
        Long userId = userService.getUserIdFromSession(session);
        ToDo toDo = toDoService.findToDo(toDoId);
        validateUserAuthorization(userId, toDo);

        toDoService.deleteToDo(toDo);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //==비즈니스 로직==//
    private void validateUserAuthorization(Long userId, ToDo toDo){
        if (!toDo.getUser().getId().equals(userId)) {
            throw new ForbiddenAccessException(FORBIDDEN_ACCESS_ACTION);
        }
    }
}
