package com.vikas.ConnectSocial.service;

import com.vikas.ConnectSocial.dto.PostRequest;
import com.vikas.ConnectSocial.dto.PostResponse;
import com.vikas.ConnectSocial.exceptions.PostNotFoundException;
import com.vikas.ConnectSocial.exceptions.UserNotFoundException;

import java.util.List;

public interface IPostService {
    public PostResponse createPost(PostRequest postRequest, int userId) throws UserNotFoundException;
    public String deletePost(int postId, int userId) throws Exception;
    public List<PostResponse> findPostByUserId(int userId) throws UserNotFoundException;
    public PostResponse findPostById(int postId) throws PostNotFoundException;
    public List<PostResponse> findAllPosts() throws PostNotFoundException;
    public PostResponse savePost(int postId, int userId) throws UserNotFoundException, PostNotFoundException;
    public PostResponse likePost(int postId, int userId) throws UserNotFoundException, PostNotFoundException;

}
