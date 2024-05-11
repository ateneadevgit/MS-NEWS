package com.fusm.news.service.impl;

import com.fusm.news.entity.News;
import com.fusm.news.entity.NewsCampus;
import com.fusm.news.entity.NewsImages;
import com.fusm.news.model.NewsModel;
import com.fusm.news.model.NewsRequest;
import com.fusm.news.model.NewsSearch;
import com.fusm.news.repository.INewRepository;
import com.fusm.news.repository.INewsCampusRepository;
import com.fusm.news.repository.INewsImagesRepository;
import com.fusm.news.repository.NewsCustomRepository;
import com.fusm.news.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NewService implements INewService {

    @Autowired
    private INewRepository newRepository;

    @Autowired
    private INewsCampusRepository newsCampusRepository;

    @Autowired
    private INewsImagesRepository newsImagesRepository;

    @Autowired
    private NewsCustomRepository newsCustomRepository;


    @Override
    public void createNew(NewsRequest newsRequest) {
        News news = newRepository.save(
                News.builder()
                        .tittle(newsRequest.getTittle())
                        .content(newsRequest.getContent())
                        .cover(newsRequest.getCover())
                        .createdBy(newsRequest.getCreatedBy())
                        .createdAt(new Date())
                        .enabled(true)
                        .build()
        );
        createNewsCampus(newsRequest.getCampus(), news);
        createNewsImages(newsRequest.getImages(), news);
    }

    @Override
    public List<NewsModel> getNews(NewsSearch newsSearch) {
        List<Integer> campus = (newsSearch.getCampus().isEmpty()) ? null : newsSearch.getCampus();
        return newsCustomRepository.callDynamicSelect((campus != null) ?
                campus.toArray(new Integer[0]) : null);
    }

    @Override
    public void updateNews(NewsRequest newsRequest, Integer newId) {
        Optional<News> newsOptional = newRepository.findById(newId);
        if (newsOptional.isPresent()) {
            News news = newsOptional.get();
            news.setTittle(newsRequest.getTittle());
            news.setContent(newsRequest.getContent());
            news.setCover(newsRequest.getCover());
            disableAndCreateCampus(newsRequest.getCampus(), newId);
            disableAndCreateImages(newsRequest.getImages(), newId);
            newRepository.save(news);
        }
    }

    @Override
    public void disableNews(Integer newId) {
        Optional<News> newsOptional = newRepository.findById(newId);
        if (newsOptional.isPresent()) {
            News news = newsOptional.get();
            news.setEnabled(false);
            newRepository.save(news);
        }
    }

    private void createNewsCampus(List<Integer> campus, News news) {
        for (Integer campusId : campus) {
            newsCampusRepository.save(
                    NewsCampus.builder()
                            .campusId(campusId)
                            .newsId(news)
                            .enabled(true)
                            .build()
            );
        }
    }

    private void createNewsImages(List<String> images, News news) {
        for (String url : images) {
            newsImagesRepository.save(
                    NewsImages.builder()
                            .imageUrl(url)
                            .newsId(news)
                            .enabled(true)
                            .build()
            );
        }
    }

    private void disableAndCreateCampus(List<Integer> campusId, Integer newsId) {
        List<NewsCampus> campusList = newsCampusRepository.findAllByNewsId_NewId(newsId);
        for (NewsCampus campus : campusList) {
            if (campus.getEnabled().equals(false) && campusId.contains(campus.getCampusId())) {
                campus.setEnabled(true);
                newsCampusRepository.save(campus);
                campusId.remove(campus.getCampusId());
            } else if (campus.getEnabled().equals(true) && campusId.contains(campus.getCampusId())) {
                campusId.remove(campus.getCampusId());
            } else if (!campusId.contains(campus.getCampusId())) {
                campus.setEnabled(false);
                newsCampusRepository.save(campus);
                campusId.remove(campus.getCampusId());
            }
        }
        Optional<News> newsOptional = newRepository.findById(newsId);
        newsOptional.ifPresent(news -> createNewsCampus(campusId, news));
    }

    private void disableAndCreateImages(List<String> imagesUpdated, Integer newsId) {
        List<NewsImages> imagesList = newsImagesRepository.findAllByNewsId_NewId(newsId);
        for (NewsImages image : imagesList) {
            image.setEnabled(false);
            newsImagesRepository.save(image);
        }
        Optional<News> newsOptional = newRepository.findById(newsId);
        newsOptional.ifPresent(news -> createNewsImages(imagesUpdated, news));
    }

}
