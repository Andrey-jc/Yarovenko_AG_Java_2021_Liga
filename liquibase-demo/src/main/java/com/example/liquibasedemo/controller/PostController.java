package com.example.liquibasedemo.controller;

import com.example.liquibasedemo.dto.PostUserDTO;
import com.example.liquibasedemo.entity.PostUser;
import com.example.liquibasedemo.entity.User;
import com.example.liquibasedemo.exceptions_handling.NoSuchExceptionSocialNetwork;
import com.example.liquibasedemo.services.interfaces.FriendsService;
import com.example.liquibasedemo.services.interfaces.PostService;
import com.example.liquibasedemo.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private PostService postService;
    private UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService, FriendsService friendsService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/posts/{id}")
    public PostUserDTO getPost(@PathVariable("id") int id) {
        PostUserDTO postUserDTO = postService.getPostDTO(id);
        if (postUserDTO == null) {
            throw new NoSuchExceptionSocialNetwork("There is no post with ID = " +
                    id + " in Database");
        }
        return postUserDTO;
    }

    @GetMapping("/posts")
    public List<PostUserDTO> showAllPosts() {
        return postService.getAllPost();
    }

    /**
     * @param id      user id
     * @param content содержимое поста
     * @return возвращаем json записи
     */
    @PostMapping("/posts")
    public PostUserDTO addNewPost(@RequestParam("id") int id, @RequestParam("content") String content) {
        User user = userService.getUser(id);
        PostUser post = new PostUser(user, content);
        postService.savePost(post);
        return postService.getPostDTO(post.getId());
    }

    @PutMapping("/posts")
    public PostUser updatePost(@RequestParam("id") int id, @RequestParam("content") String content) {
        PostUser post = postService.getPost(id);
        post.setContent(content);
        postService.savePost(post);
        return post;
    }

    @DeleteMapping("/posts/{id}")
    public String deleteUser(@PathVariable int id) {
        PostUser postUser = postService.getPost(id);
        if (postUser == null) {
            throw new NoSuchExceptionSocialNetwork("There is no post with ID = " +
                    id + " in Database");
        }
        postService.deletePost(id);
        return "School with ID = " + id + " was deleted";
    }
}
