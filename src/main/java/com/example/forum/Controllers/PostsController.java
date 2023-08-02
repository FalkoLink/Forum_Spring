package com.example.forum.Controllers;

import com.example.forum.Models.Post;
import com.example.forum.dao.PostDAO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostsController {
    private PostDAO postDAO;

    @Autowired
    public PostsController(PostDAO postDAO){
        this.postDAO = postDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("posts", postDAO.index());
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("post", postDAO.show(id));
        return "post";
    }

    @GetMapping("/new")
    public String newPost(Model model){
        Post post = new Post();
        model.addAttribute(post);
        return "create";
    }

    @PostMapping()
    public String create(@ModelAttribute("post") @Valid Post post, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "create";
        postDAO.save(post);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("post", postDAO.show(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("post") @Valid Post post, BindingResult bindingResult, @PathVariable int id){
        if (bindingResult.hasErrors())
            return "edit";
        postDAO.update(post, id);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        postDAO.delete(id);
        return "redirect:/";
    }
}
