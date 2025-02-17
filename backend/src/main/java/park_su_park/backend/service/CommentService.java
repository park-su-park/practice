package park_su_park.backend.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import park_su_park.backend.domain.Comment;
import park_su_park.backend.domain.ToDo;
import park_su_park.backend.domain.User;
import park_su_park.backend.dto.requestBody.CreateCommentRequest;
import park_su_park.backend.dto.responseBody.CommentData;
import park_su_park.backend.exception.ForbiddenAccessException;
import park_su_park.backend.exception.ResourceNotFoundException;
import park_su_park.backend.repository.CommentRepository;
import park_su_park.backend.util.SessionUtil;

import java.text.MessageFormat;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private static final String FORBIDDEN_ACCESS_ACTION = "해당 댓글을 수정 및 삭제할 권한이 없습니다";
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final ToDoService toDoService;

    public CommentData createComment(CreateCommentRequest createCommentRequest, Long toDoId, HttpSession session) {
        Long userId = SessionUtil.getUserIdFromSession(session);
        User user = userService.findUser(userId);
        ToDo toDo = toDoService.findToDo(toDoId);

        Comment savedComment = saveComment(createCommentRequest, user, toDo);

        return CommentData.create(savedComment);
    }

    private Comment saveComment(CreateCommentRequest createCommentRequest, User user, ToDo toDo) {
        Comment comment = Comment.create(createCommentRequest);
        comment.setUser(user);
        comment.setToDo(toDo);

        return commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public Comment findComment(Long commentId){
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        MessageFormat.format("해당 id 를 가진 댓글을 찾을 수 없습니다: {0}", commentId)
                ));
    }

    public CommentData getComment(Long commentId) {
        Comment comment = findComment(commentId);

        return CommentData.create(comment);
    }

    public CommentData updateComment(CreateCommentRequest updateCommentRequest, Long commentId, HttpSession session) {
        getAuthorizedComment(commentId, session);
        Comment comment = findComment(commentId);
        comment.update(updateCommentRequest);

        return CommentData.create(comment);
    }

    public void deleteComment(Long commentId, HttpSession session) {
        getAuthorizedComment(commentId, session);
        Comment comment = findComment(commentId);

        commentRepository.delete(comment);
    }

    private void getAuthorizedComment(Long commentId, HttpSession session){
        Long userId = SessionUtil.getUserIdFromSession(session);
        Comment comment = findComment(commentId);

        if (!comment.getUser().getId().equals(userId)) {
            throw new ForbiddenAccessException(FORBIDDEN_ACCESS_ACTION);
        }
    }
}
