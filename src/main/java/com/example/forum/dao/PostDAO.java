package com.example.forum.dao;

import com.example.forum.Models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Post> index(){
        return jdbcTemplate.query("SELECT*FROM posts", new BeanPropertyRowMapper<>(Post.class));
    }

    public Post show(int id){
        return jdbcTemplate.query("SELECT*FROM posts WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Post.class))
                .stream().findAny().orElse(null);
    }

    public void save(Post post){
        jdbcTemplate.update("INSERT INTO posts(title, text) VALUES (?,?)", post.getTitle(),post.getText());
    }

    public void update(Post post, int id){
        jdbcTemplate.update("UPDATE posts SET title=?, text=? WHERE id=?", post.getTitle(),post.getText(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM posts WHERE id=?", id);
    }
}
