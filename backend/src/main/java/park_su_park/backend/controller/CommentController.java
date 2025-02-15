package park_su_park.backend.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import park_su_park.backend.domain.Comment;
import park_su_park.backend.dto.requestBody.CreateCommentRequest;
import park_su_park.backend.dto.responseBody.CommentDataResponse;
import park_su_park.backend.exception.ForbiddenAccessException;
import park_su_park.backend.service.CommentService;
import park_su_park.backend.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private static final String FORBIDDEN_ACCESS_ACTION = "해당 댓글을 수정 및 삭제할 권한이 없습니다";
    private final CommentService commentService;
    private final UserService userService;

    @PostMapping("/create/{toDoId}")
    public ResponseEntity<CommentDataResponse> createComment(@RequestBody @Valid CreateCommentRequest createCommentRequest, @PathVariable Long toDoId, HttpSession session) {
        Long userId = userService.getUserIdFromSession(session);

        Long commentId = commentService.join(createCommentRequest, userId, toDoId);

        CommentDataResponse commentDataResponse = CommentDataResponse.create(commentService.findComment(commentId));
        return ResponseEntity.status(HttpStatus.CREATED).body(commentDataResponse);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDataResponse> getComment(@PathVariable Long commentId) {
        Comment comment = commentService.findComment(commentId);

        CommentDataResponse commentDataResponse = CommentDataResponse.create(comment);
        return ResponseEntity.ok(commentDataResponse);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentDataResponse> updateComment(@RequestBody @Valid CreateCommentRequest updateCommentRequest, @PathVariable Long commentId, HttpSession session) {
        Long userId = userService.getUserIdFromSession(session);
        Comment comment = commentService.findComment(commentId);
        validateUserAuthorization(userId,comment);

        commentService.updateComment(updateCommentRequest, comment);

        CommentDataResponse commentDataResponse = CommentDataResponse.create(comment);
        return ResponseEntity.ok(commentDataResponse);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId, HttpSession session) {
        Long userId = userService.getUserIdFromSession(session);
        Comment comment = commentService.findComment(commentId);
        validateUserAuthorization(userId, comment);

        commentService.deleteComment(comment);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //==비즈니스 로직==//
    private void validateUserAuthorization(Long userId, Comment comment){
        if (!comment.getUser().getId().equals(userId)) {
            throw new ForbiddenAccessException(FORBIDDEN_ACCESS_ACTION);
        }
    }
}
