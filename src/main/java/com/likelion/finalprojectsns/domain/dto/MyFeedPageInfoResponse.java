package com.likelion.finalprojectsns.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyFeedPageInfoResponse {
    private List<PostGetResponse> content;
    private Pageable pageable;
    private Boolean last;
    private Integer totalPages;
    private Long totalElements;
    private Integer size;
    private Integer number;
    private Sort sort;
    private Integer numberOfElements;
    private Boolean first;
    private Boolean empty;
}