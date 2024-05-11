package com.fusm.news.repository;

import com.fusm.news.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INewRepository extends JpaRepository<News, Integer> {

    @Query(
            value = "select * from news " +
                    "where enabled = true " +
                    "order by created_at desc ",
            nativeQuery = true
    )
    List<News> findAllByNews();

}
