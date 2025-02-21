package park_su_park.backend.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import park_su_park.backend.dto.requestBody.CreateCommentRequest;
import park_su_park.backend.dto.responseBody.ApiResponseBody;
import park_su_park.backend.dto.responseBody.CommentData;
import park_su_park.backend.service.CommentService;
import park_su_park.backend.util.constant.CommentResponseMessage;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/create/{toDoId}")
    public ResponseEntity<ApiResponseBody> createComment(@RequestBody @Valid CreateCommentRequest createCommentRequest, @PathVariable Long toDoId, HttpSession session) {
        CommentData commentData = commentService.createComment(createCommentRequest, toDoId, session);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseBody.create(CommentResponseMessage.COMMENT_CREATION_SUCCESS, commentData));
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<ApiResponseBody> getComment(@PathVariable Long commentId) {
        CommentData commentData = commentService.getComment(commentId);

        return ResponseEntity.ok(ApiResponseBody.create(CommentResponseMessage.COMMENT_FETCH_SUCCESS, commentData));
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<ApiResponseBody> updateComment(@RequestBody @Valid CreateCommentRequest updateCommentRequest, @PathVariable Long commentId, HttpSession session) {
        CommentData commentData = commentService.updateComment(updateCommentRequest, commentId, session);

        return ResponseEntity.ok(ApiResponseBody.create(CommentResponseMessage.COMMENT_UPDATE_SUCCESS, commentData));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponseBody> deleteComment(@PathVariable Long commentId, HttpSession session) {
        commentService.deleteComment(commentId, session);

        return ResponseEntity.ok(ApiResponseBody.create(CommentResponseMessage.COMMENT_DELETION_SUCCESS, null));
    }
}
