package park_su_park.backend.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import park_su_park.backend.domain.ToDo;
import park_su_park.backend.domain.User;
import park_su_park.backend.dto.requestBody.CreateToDoRequest;
import park_su_park.backend.dto.responseBody.PagedObjectData;
import park_su_park.backend.dto.responseBody.ToDoData;
import park_su_park.backend.exception.ForbiddenAccessException;
import park_su_park.backend.exception.ResourceNotFoundException;
import park_su_park.backend.repository.ToDoRepository;
import park_su_park.backend.util.constant.SessionConstant;
import park_su_park.backend.util.constant.ToDoResponseMessage;

import java.text.MessageFormat;

@Service
@Transactional
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;
    private final UserService userService;

    public ToDoData createToDo(CreateToDoRequest createToDoRequest, HttpSession session) {
        Long userId = (Long) session.getAttribute(SessionConstant.SESSION_USER_ID);
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

    @Transactional(readOnly = true)
    public PagedObjectData<ToDoData> findAll(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "updateTime"));
        Page<ToDo> toDoPage = toDoRepository.findAll(pageRequest);

        if (toDoPage.getContent().isEmpty()) {
            throw new ResourceNotFoundException(ToDoResponseMessage.TODO_FETCH_FAILED);
        }

        Page<ToDoData> toDoDataPage = toDoPage.map(ToDoData::create);
        return PagedObjectData.create(toDoDataPage);
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
        Long userId = (Long) session.getAttribute(SessionConstant.SESSION_USER_ID);
        ToDo toDo = findToDo(toDoId);

        if (!toDo.getUser().getId().equals(userId)) {
            throw new ForbiddenAccessException(ToDoResponseMessage.TODO_FORBIDDEN_ACCESS);
        }
    }
}
