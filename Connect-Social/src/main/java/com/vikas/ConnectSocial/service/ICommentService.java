package com.vikas.ConnectSocial.service;

import com.vikas.ConnectSocial.dto.CommentRequest;
import com.vikas.ConnectSocial.dto.CommentResponse;

public interface ICommentService {

    public CommentResponse createComment(CommentRequest commentRequest, int postId, int userId);
    public CommentResponse getCommentById(int commentId) throws Exception;
    public CommentResponse likeComment(int commentId, int userId) throws Exception;
}
