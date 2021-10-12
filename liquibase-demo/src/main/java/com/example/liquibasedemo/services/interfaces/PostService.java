package com.example.liquibasedemo.services.interfaces;

import com.example.liquibasedemo.dto.PostUserDTO;
import com.example.liquibasedemo.entity.PostUser;

import java.util.List;

public interface PostService {
    List<PostUserDTO> getAllPost();

    void savePost(PostUser post);

    PostUserDTO getPostDTO(int id);

    PostUser getPost(int id);

    void deletePost(int id);
}
