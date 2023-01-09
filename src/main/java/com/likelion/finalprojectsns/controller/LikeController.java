package com.likelion.finalprojectsns.controller;

import com.likelion.finalprojectsns.domain.Response;
import com.likelion.finalprojectsns.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts/{postId}/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    /**/

    @PostMapping("")
    public ResponseEntity<Response<String>> like(@PathVariable Integer postId, Authentication authentication){
        String userName = authentication.getName();
        String likeResponse = likeService.like(userName, postId);
        return ResponseEntity.ok().body(Response.success(likeResponse));
    }

    @GetMapping("")
    public ResponseEntity<Response<Long>> likeResult(@PathVariable Integer postId/*, Authentication authentication*/){
//        String userName = authentication.getName();
//        Long likeResultResponse = likeService.likeCount(userName, postId);
        Long likeResultResponse = likeService.likeCount(postId);
        return ResponseEntity.ok().body(Response.success(likeResultResponse));
    }
}
