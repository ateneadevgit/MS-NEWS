package com.fusm.news.service;

import com.fusm.news.model.NewsModel;
import com.fusm.news.model.NewsRequest;
import com.fusm.news.model.NewsSearch;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface INewService {

    void createNew(NewsRequest newsRequest);
    List<NewsModel> getNews(NewsSearch newsSearch);
    void updateNews(NewsRequest newsRequest, Integer newId);
    void disableNews(Integer newId);

}
