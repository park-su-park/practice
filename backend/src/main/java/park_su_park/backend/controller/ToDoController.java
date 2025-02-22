package park_su_park.backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import park_su_park.backend.dto.requestDto.RequestToDoDto;
import park_su_park.backend.dto.responseData.ApiResponseBody;
import park_su_park.backend.dto.responseData.ToDoData;
import park_su_park.backend.exception.ExpriedSessionException;
import park_su_park.backend.logIn.LogInterface;
import park_su_park.backend.message.TODOMESSAGE;
import park_su_park.backend.repository.UserRepository;
import park_su_park.backend.service.ToDoService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/to-do")
public class ToDoController {

    private final ToDoService toDoService;

    //C
    @PostMapping("/create")
    public ResponseEntity<ApiResponseBody> createToDo(
        HttpServletRequest request,
        @Valid @RequestBody
        RequestToDoDto requestToDoDto) {
        Long userId = getUserIdFromSession(request);
        ToDoData toDoData = toDoService.save(userId, requestToDoDto);
        return ResponseEntity.ok(new ApiResponseBody(TODOMESSAGE.CREATE_SUCCESS, toDoData));
    }

    //R 유저의 할 일 모두 부르기
    @GetMapping
    public ResponseEntity<ApiResponseBody> readToDo(
        HttpServletRequest request) {
        Long userId = getUserIdFromSession(request);

        ToDoData toDoData = toDoService.findAll(userId);
        return ResponseEntity.ok(new ApiResponseBody(TODOMESSAGE.READ_ALL_SUCCESS, toDoData));
    }

    //R 유저의 특정 id를 가진 할 일 부르기
    @GetMapping("/{toDoId}")
    public ResponseEntity<ApiResponseBody> readAllToDo(
        HttpServletRequest request, @PathVariable Long toDoId) {
        Long userId = getUserIdFromSession(request);
        ToDoData toDoData = toDoService.findOne(userId, toDoId);
        return ResponseEntity.ok(new ApiResponseBody(TODOMESSAGE.READ_SUCCESS, toDoData));
    }

    //U
    @PatchMapping("/{toDoId}")
    public ResponseEntity<ApiResponseBody> updateToDo(HttpServletRequest request,
        @PathVariable Long toDoId,
        @Valid @RequestBody RequestToDoDto requestToDoDto) {
        Long userId = getUserIdFromSession(request);
        ToDoData toDoData = toDoService.update(userId, toDoId, requestToDoDto);
        return ResponseEntity.ok(new ApiResponseBody(TODOMESSAGE.UPDATE_SUCCESS, toDoData));
    }

    //D
    @DeleteMapping("/{toDoId}")
    public ResponseEntity<ApiResponseBody> deleteToDo(HttpServletRequest request,
        @PathVariable Long toDoId) {
        Long userId = getUserIdFromSession(request);
        toDoService.delete(userId, toDoId);
        return ResponseEntity.ok(new ApiResponseBody(TODOMESSAGE.DELETE_SUCCESS, null));
    }

    private static Long getUserIdFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute(LogInterface.LOGIN_USER);
        return userId;
    }
}
