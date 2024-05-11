package com.fusm.news.repository;

import com.fusm.news.entity.NewsImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INewsImagesRepository extends JpaRepository<NewsImages, Integer> {

    List<NewsImages> findAllByNewsId_NewId(Integer newsId);

}
