package park_su_park.backend.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import park_su_park.backend.dto.requestBody.CreateToDoRequest;
import park_su_park.backend.dto.responseBody.ApiResponseBody;
import park_su_park.backend.dto.responseBody.PagedObjectData;
import park_su_park.backend.dto.responseBody.ToDoData;
import park_su_park.backend.service.ToDoService;
import park_su_park.backend.util.constant.ToDoResponseMessage;

@RestController
@RequiredArgsConstructor
@RequestMapping("/to-do")
public class ToDoController {

    private final ToDoService toDoService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponseBody> createToDo(@RequestBody @Valid CreateToDoRequest createToDoRequest, HttpSession session) {
        ToDoData toDoData = toDoService.createToDo(createToDoRequest, session);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseBody.create(ToDoResponseMessage.TODO_CREATION_SUCCESS, toDoData));
    }

    @GetMapping("/{toDoId}")
    public ResponseEntity<ApiResponseBody> getToDo(@PathVariable Long toDoId) {
        ToDoData toDoData = toDoService.getToDoData(toDoId);

        return ResponseEntity.ok(ApiResponseBody.create(ToDoResponseMessage.TODO_FETCH_SUCCESS, toDoData));
    }

    @GetMapping
    public ResponseEntity<ApiResponseBody> getAllToDos(@RequestParam int pageNumber, @RequestParam int pageSize) {
        PagedObjectData<ToDoData> pagedObjectData = toDoService.findAll(pageNumber, pageSize);

        return ResponseEntity.ok(ApiResponseBody.create(ToDoResponseMessage.TODO_FETCH_SUCCESS, pagedObjectData));
    }

    @PatchMapping("/{toDoId}")
    public ResponseEntity<ApiResponseBody> updateToDo(@PathVariable Long toDoId, @RequestBody @Valid CreateToDoRequest updateToDoRequest, HttpSession session) {
        ToDoData toDoData = toDoService.updateToDo(updateToDoRequest, toDoId, session);

        return ResponseEntity.ok(ApiResponseBody.create(ToDoResponseMessage.TODO_UPDATE_SUCCESS, toDoData));
    }

    @DeleteMapping("/{toDoId}")
    public ResponseEntity<ApiResponseBody> deleteToDo(@PathVariable Long toDoId, HttpSession session) {
        toDoService.deleteToDo(toDoId, session);

        return ResponseEntity.ok(ApiResponseBody.create(ToDoResponseMessage.TODO_DELETION_SUCCESS, null));
    }
}
