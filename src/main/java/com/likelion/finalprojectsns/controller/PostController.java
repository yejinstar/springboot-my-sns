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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @GetMapping("")
    public Response<PageInfoResponse> get(
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable/*,
                                             Authentication authentication*/){
        PageInfoResponse posts = postService.get(pageable);
        return Response.success(posts);
    }

    @GetMapping("/{postId}")
    public Response<PostGetResponse> getOne(@PathVariable Integer postId /*Authentication authentication*/){
        PostGetResponse postGetResponse = postService.getOne(postId);
        return Response.success(postGetResponse);
    }
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

    @DeleteMapping("/{postId}")
    public Response<PostPostingResponse> delete(@PathVariable Integer postId, Authentication authentication){
        String userName = authentication.getName();
        PostPostingResponse postPostingResponse = postService.delete(postId, userName);
        return Response.success(postPostingResponse);
    }
}
