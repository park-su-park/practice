package park_su_park.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import park_su_park.backend.domain.Comment;
import park_su_park.backend.domain.ToDo;
import park_su_park.backend.domain.User;
import park_su_park.backend.dto.requestBody.CreateCommentRequest;
import park_su_park.backend.exception.ResourceNotFoundException;
import park_su_park.backend.repository.CommentRepository;

import java.text.MessageFormat;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final ToDoService toDoService;

    public Long join(CreateCommentRequest createCommentRequest, Long userId, Long toDoId) {
        Comment comment = Comment.create(createCommentRequest);
        User user = userService.findUserById(userId);
        ToDo toDo = toDoService.findToDo(toDoId);
        comment.setUser(user);
        comment.setToDo(toDo);

        Comment savedComment = commentRepository.save(comment);

        return savedComment.getId();
    }

    @Transactional(readOnly = true)
    public Comment findComment(Long commentId){
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        MessageFormat.format("해당 id 를 가진 댓글을 찾을 수 없습니다: {0}", commentId)
                ));
    }

    public void updateComment(CreateCommentRequest updateCommentRequest, Comment oldComment) {
        oldComment.update(updateCommentRequest);
    }

    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }
}
