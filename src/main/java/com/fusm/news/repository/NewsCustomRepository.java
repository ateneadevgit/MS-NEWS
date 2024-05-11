package com.fusm.news.repository;

import com.fusm.news.model.NewsModel;
import com.fusm.news.model.NewsModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsCustomRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<NewsModel> callDynamicSelect(Integer[] campus) {
        String sql = "SELECT * FROM get_news(?)";
        return jdbcTemplate.query(sql, new Object[] {campus},
                new NewsModelMapper()
        );
    }

}
