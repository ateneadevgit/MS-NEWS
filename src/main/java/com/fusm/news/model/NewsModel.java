package com.fusm.news.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsModel {

    private Integer newId;
    private String tittle;
    private String cover;
    private Date createdAt;
    private String content;
    private List<String> campus;
    private List<String> images;

}
