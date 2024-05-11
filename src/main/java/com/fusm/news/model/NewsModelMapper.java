package com.fusm.news.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;

public class NewsModelMapper implements RowMapper<NewsModel> {

    @Override
    public NewsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return NewsModel.builder()
                .newId(rs.getInt("id_new"))
                .content(rs.getString("content"))
                .cover(rs.getString("cover"))
                .createdAt(rs.getDate("created_at"))
                .tittle(rs.getString("tittle"))
                .campus(Arrays.asList(rs.getString("campus").split(",")))
                .images(Arrays.asList(rs.getString("images").split(",")))
                .build();
    }

}
