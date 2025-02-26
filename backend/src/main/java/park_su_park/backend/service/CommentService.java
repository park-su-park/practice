package park_su_park.backend.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import park_su_park.backend.domain.Comment;
import park_su_park.backend.domain.ToDo;
import park_su_park.backend.domain.User;
import park_su_park.backend.dto.requestBody.CreateCommentRequest;
import park_su_park.backend.dto.responseBody.CommentData;
import park_su_park.backend.dto.responseBody.PagedObjectData;
import park_su_park.backend.exception.ForbiddenAccessException;
import park_su_park.backend.exception.ResourceNotFoundException;
import park_su_park.backend.repository.CommentRepository;
import park_su_park.backend.util.constant.CommentResponseMessage;
import park_su_park.backend.util.constant.SessionConstant;

import java.text.MessageFormat;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final ToDoService toDoService;

    public CommentData createComment(CreateCommentRequest createCommentRequest, Long toDoId, HttpSession session) {
        Long userId = (Long) session.getAttribute(SessionConstant.SESSION_USER_ID);
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

    @Transactional(readOnly = true)
    public PagedObjectData<CommentData> findAll(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "updateTime"));
        Page<Comment> commentPage = commentRepository.findAll(pageRequest);

        if (commentPage.getContent().isEmpty()) {
            throw new ResourceNotFoundException(CommentResponseMessage.COMMENT_FETCH_FAILED);
        }

        Page<CommentData> commentDataPage = commentPage.map(CommentData::create);
        return PagedObjectData.create(commentDataPage);
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
        Long userId = (Long) session.getAttribute(SessionConstant.SESSION_USER_ID);
        Comment comment = findComment(commentId);

        if (!comment.getUser().getId().equals(userId)) {
            throw new ForbiddenAccessException(CommentResponseMessage.COMMENT_FORBIDDEN_ACTION);
        }
    }
}
