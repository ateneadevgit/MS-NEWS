package com.fusm.news.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsRequest {

    private List<Integer> campus;
    private String tittle;
    private String cover;
    private List<String> images;
    private String content;
    private String createdBy;

}
