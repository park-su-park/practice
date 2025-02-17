package park_su_park.backend.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import park_su_park.backend.domain.ToDo;
import park_su_park.backend.domain.User;
import park_su_park.backend.dto.requestBody.CreateToDoRequest;
import park_su_park.backend.dto.responseBody.ToDoData;
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

    public ToDoData createToDo(CreateToDoRequest createToDoRequest, HttpSession session) {
        Long userId = SessionUtil.getUserIdFromSession(session);
        User user = userService.findUser(userId);

        ToDo toDo = saveToDo(createToDoRequest, user);
        return ToDoData.create(toDo);
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

    public ToDoData getToDoData(Long toDoId) {
        ToDo toDo = findToDo(toDoId);

        return ToDoData.create(toDo);
    }

    public ToDoData updateToDo(CreateToDoRequest updateToDoRequest, Long toDoId, HttpSession session) {
        checkAuthorization(toDoId, session);
        ToDo toDo = findToDo(toDoId);
        toDo.update(updateToDoRequest);

        return ToDoData.create(toDo);
    }

    public void deleteToDo(Long toDoId, HttpSession session) {
        checkAuthorization(toDoId, session);
        ToDo toDo = findToDo(toDoId);

        toDoRepository.delete(toDo);
    }

    private void checkAuthorization(Long toDoId, HttpSession session) {
        Long userId = SessionUtil.getUserIdFromSession(session);
        ToDo toDo = findToDo(toDoId);

        if (!toDo.getUser().getId().equals(userId)) {
            throw new ForbiddenAccessException(FORBIDDEN_ACCESS_ACTION);
        }
    }
}
