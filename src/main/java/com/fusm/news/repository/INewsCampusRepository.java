package com.fusm.news.repository;

import com.fusm.news.entity.NewsCampus;
import com.fusm.news.entity.NewsImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INewsCampusRepository extends JpaRepository<NewsCampus, Integer> {

    List<NewsCampus> findAllByNewsId_NewId(Integer newsId);

}
