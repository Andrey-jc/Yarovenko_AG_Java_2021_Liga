package com.example.liquibasedemo.services;

import com.example.liquibasedemo.dto.PostUserDTO;
import com.example.liquibasedemo.entity.PostUser;
import com.example.liquibasedemo.repository.PostRepository;
import com.example.liquibasedemo.services.interfaces.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PostUserDTO> getAllPost() {
        List<PostUser> allPosts = postRepository.findAll();
        List<PostUserDTO> postUserList = new ArrayList<>();
        for (PostUser postUser :
                allPosts) {
            PostUserDTO mapPost = modelMapper.map(postUser, PostUserDTO.class);
            postUserList.add(mapPost);
        }
        return postUserList;
    }

    @Transactional
    @Override
    public void savePost(PostUser post) {
        postRepository.save(post);
    }

    @Override
    public PostUserDTO getPostDTO(int id) {
        PostUserDTO postUserDTO = null;
        Optional<PostUser> optional = postRepository.findById(id);
        if (optional.isPresent()) {
            postUserDTO = modelMapper.map(optional.get(), PostUserDTO.class);
        }
        return postUserDTO;
    }

    /**
     * Метод для работы со всеми полями сущности
     *
     * @param id айди поста
     * @return получаем пост в класическом его виде
     */
    @Override
    public PostUser getPost(int id) {
        return postRepository.getById(id);
    }

    @Transactional
    @Override
    public void deletePost(int id) {
        postRepository.deleteById(id);
    }
}
