package com.likelion.finalprojectsns.controller;

import com.likelion.finalprojectsns.domain.Response;
import com.likelion.finalprojectsns.domain.dto.PostPostingRequest;
import com.likelion.finalprojectsns.domain.dto.PostPostingResponse;
import com.likelion.finalprojectsns.domain.dto.UserJoinRequest;
import com.likelion.finalprojectsns.domain.dto.UserJoinResponse;
import com.likelion.finalprojectsns.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("")
    public Response<PostPostingResponse> join(@RequestBody PostPostingRequest dto, Authentication authentication){
        String userName = authentication.getName();
        PostPostingResponse postPostingResponse = postService.posting(dto, userName);
        return Response.success(postPostingResponse);
    }

    @PutMapping("/{postId}")
    public Response<PostPostingResponse> edit(@PathVariable Integer postId, @RequestBody PostPostingRequest dto, Authentication authentication){
        String userName = authentication.getName();
        PostPostingResponse postPostingResponse = postService.edit(postId, dto, userName);
        return Response.success(postPostingResponse);
    }
}
