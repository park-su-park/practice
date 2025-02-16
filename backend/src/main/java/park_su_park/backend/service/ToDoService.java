package park_su_park.backend.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import park_su_park.backend.domain.ToDo;
import park_su_park.backend.domain.User;
import park_su_park.backend.dto.requestBody.CreateToDoRequest;
import park_su_park.backend.dto.responseBody.ToDoDataResponse;
import park_su_park.backend.exception.ForbiddenAccessException;
import park_su_park.backend.exception.ResourceNotFoundException;
import park_su_park.backend.repository.ToDoRepository;
import park_su_park.backend.util.SessionUtil;

import java.text.MessageFormat;

@Service
@Transactional
@RequiredArgsConstructor
public class ToDoService {

    private static final String FORBIDDEN_ACCESS_ACTION = "해당 일정을 수정 및 삭제할 권한이 없습니다";
    private final ToDoRepository toDoRepository;
    private final UserService userService;

    public ToDoDataResponse createToDo(CreateToDoRequest createToDoRequest, HttpSession session) {
        Long userId = SessionUtil.getUserIdFromSession(session);
        User user = userService.findUserById(userId);

        ToDo toDo = saveToDo(createToDoRequest, user);
        return ToDoDataResponse.create(toDo);
    }

    private ToDo saveToDo(CreateToDoRequest createToDoRequest, User user) {
        ToDo toDo = ToDo.create(createToDoRequest);
        toDo.setUser(user);

        return toDoRepository.save(toDo);
    }

    @Transactional(readOnly = true)
    public ToDo findToDo(Long toDoId) {
        return toDoRepository.findById(toDoId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        MessageFormat.format("해당 id 를 가진 일정을 찾을 수 없습니다: {0}", toDoId)
                ));
    }

    public ToDoDataResponse getToDo(Long toDoId) {
        ToDo toDo = findToDo(toDoId);

        return ToDoDataResponse.create(toDo);
    }

    public ToDoDataResponse updateToDo(CreateToDoRequest updateToDoRequest, Long toDoId, HttpSession session) {
        ToDo toDo = getAuthorizedToDo(toDoId, session);
        toDo.update(updateToDoRequest);

        return ToDoDataResponse.create(toDo);
    }

    public void deleteToDo(Long toDoId, HttpSession session) {
        ToDo toDo = getAuthorizedToDo(toDoId, session);

        toDoRepository.delete(toDo);
    }

    private ToDo getAuthorizedToDo(Long toDoId, HttpSession session) {
        Long userId = SessionUtil.getUserIdFromSession(session);
        ToDo toDo = findToDo(toDoId);

        if (!toDo.getUser().getId().equals(userId)) {
            throw new ForbiddenAccessException(FORBIDDEN_ACCESS_ACTION);
        }

        return toDo;
    }
}
