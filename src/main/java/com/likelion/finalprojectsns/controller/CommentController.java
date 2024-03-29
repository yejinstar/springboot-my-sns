package com.likelion.finalprojectsns.controller;

import com.likelion.finalprojectsns.domain.Response;
import com.likelion.finalprojectsns.domain.dto.*;
import com.likelion.finalprojectsns.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    /* Comment 전체 조회 */
    @GetMapping("")
    public ResponseEntity<Response<CommentPageInfoResponse>> get(
            @PathVariable Integer postId, @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            Authentication authentication) {
        String userName = authentication.getName();
        CommentPageInfoResponse comments = commentService.get(postId, pageable, userName);
        return ResponseEntity.ok().body(Response.success(comments));
    }

    /* Comment 등록 */
    @PostMapping("")
    public ResponseEntity<Response<CommentWriteResponse>> writing(@PathVariable Integer postId,
                                                                  @RequestBody CommentWriteRequest dto, Authentication authentication) {
        String userName = authentication.getName();
        CommentWriteResponse commentWriteResponse = commentService.writing(postId, dto, userName);
        return ResponseEntity.ok().body(Response.success(commentWriteResponse));
    }
    /* Comment 변경 */
    @PutMapping("/{id}")
    public ResponseEntity<Response<CommentWriteResponse>> edit(@PathVariable Integer postId, @PathVariable Integer id,
                                                               @RequestBody CommentWriteRequest dto, Authentication authentication) {
        String userName = authentication.getName();
        CommentWriteResponse commentWriteResponse = commentService.edit(postId, id, dto, userName);
        return ResponseEntity.ok().body(Response.success(commentWriteResponse));
    }

    /* Comment 삭제 */
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<CommentDeleteResponse>> delete(@PathVariable Integer postId, @PathVariable Integer id, Authentication authentication){
        String userName = authentication.getName();
        CommentDeleteResponse commentDeleteResponse = commentService.delete(postId, id, userName);
        return ResponseEntity.ok().body(Response.success(commentDeleteResponse));
    }

}
