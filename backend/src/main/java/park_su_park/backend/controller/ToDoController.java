package park_su_park.backend.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import park_su_park.backend.dto.requestBody.CreateToDoRequest;
import park_su_park.backend.dto.responseBody.ToDoDataResponse;
import park_su_park.backend.service.ToDoService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/to-do")
public class ToDoController {

    private final ToDoService toDoService;

    @PostMapping("/create")
    public ResponseEntity<ToDoDataResponse> createToDo(@RequestBody @Valid CreateToDoRequest createToDoRequest, HttpSession session) {
        ToDoDataResponse toDoDataResponse = toDoService.createToDo(createToDoRequest, session);

        return ResponseEntity.status(HttpStatus.CREATED).body(toDoDataResponse);
    }

    @GetMapping("/{toDoId}")
    public ResponseEntity<ToDoDataResponse> getToDo(@PathVariable Long toDoId) {
        ToDoDataResponse toDoDataResponse = toDoService.getToDo(toDoId);

        return ResponseEntity.ok(toDoDataResponse);
    }

    @PatchMapping("/{toDoId}")
    public ResponseEntity<ToDoDataResponse> updateToDo(@PathVariable Long toDoId, @RequestBody @Valid CreateToDoRequest updateToDoRequest, HttpSession session) {
        ToDoDataResponse toDoDataResponse = toDoService.updateToDo(updateToDoRequest, toDoId, session);

        return ResponseEntity.ok(toDoDataResponse);
    }

    @DeleteMapping("/{toDoId}")
    public ResponseEntity<Void> deleteToDo(@PathVariable Long toDoId, HttpSession session) {
        toDoService.deleteToDo(toDoId, session);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
