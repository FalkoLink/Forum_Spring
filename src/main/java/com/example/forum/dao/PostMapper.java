package com.example.forum.dao;

import com.example.forum.Models.Post;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class PostMapper implements RowMapper<Post> {

    @Override
    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
        Post post = new Post();

        post.setId(rs.getInt("id"));
        post.setTitle(rs.getString("title"));
        post.setText(rs.getString("text"));
        post.setCreated_on(LocalDateTime.now());

        return post;
    }
}
