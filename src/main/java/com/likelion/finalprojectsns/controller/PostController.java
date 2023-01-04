package com.likelion.finalprojectsns.controller;

import com.likelion.finalprojectsns.domain.Response;
import com.likelion.finalprojectsns.domain.dto.*;
import com.likelion.finalprojectsns.domain.entity.PostEntity;
import com.likelion.finalprojectsns.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    /* Post 전체 조회 */
    @GetMapping("")
    public ResponseEntity<Response<PageInfoResponse>> get(
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable){
        PageInfoResponse posts = postService.get(pageable);
        return ResponseEntity.ok().body(Response.success(posts));
    }

    /* Post 1개 조회 */
    @GetMapping("/{postId}")
    public ResponseEntity<Response<PostGetResponse>> getOne(@PathVariable Integer postId){
        PostGetResponse postGetResponse = postService.getOne(postId);
        return ResponseEntity.ok().body(Response.success(postGetResponse));
    }

    /* Post 작성 */
    @PostMapping("")
    public ResponseEntity<Response<PostPostingResponse>> posting(@RequestBody PostPostingRequest dto, Authentication authentication){
        String userName = authentication.getName();
        PostPostingResponse postPostingResponse = postService.posting(dto, userName);
        return ResponseEntity.ok().body(Response.success(postPostingResponse));
    }

    /* Post 수정 */
    @PutMapping("/{postId}")
    public ResponseEntity<Response<PostPostingResponse>> edit(@PathVariable Integer postId, @RequestBody PostPostingRequest dto, Authentication authentication){
        String userName = authentication.getName();
        PostPostingResponse postPostingResponse = postService.edit(postId, dto, userName);
        return ResponseEntity.ok().body(Response.success(postPostingResponse));
    }

    /* Post 삭제 */
    @DeleteMapping("/{postId}")
    public ResponseEntity<Response<PostPostingResponse>> delete(@PathVariable Integer postId, Authentication authentication){
        String userName = authentication.getName();
        PostPostingResponse postPostingResponse = postService.delete(postId, userName);
        return ResponseEntity.ok().body(Response.success(postPostingResponse));
    }

    /* MyFeed 조회 */
    @GetMapping("/my")
    public ResponseEntity<Response<MyFeedPageInfoResponse>> myFeed(@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            Authentication authentication) {
        String userName = authentication.getName();
        MyFeedPageInfoResponse myFeeds = postService.myFeed(pageable, userName);
        return ResponseEntity.ok().body(Response.success(myFeeds));
    }
}
