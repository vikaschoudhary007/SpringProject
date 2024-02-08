package com.vikas.ConnectSocial.service;

import com.vikas.ConnectSocial.dto.CommentRequest;
import com.vikas.ConnectSocial.dto.CommentResponse;
import com.vikas.ConnectSocial.dto.PostResponse;
import com.vikas.ConnectSocial.dto.UserResponse;
import com.vikas.ConnectSocial.model.Comment;
import com.vikas.ConnectSocial.model.Post;
import com.vikas.ConnectSocial.model.User;
import com.vikas.ConnectSocial.repository.CommentRepository;
import com.vikas.ConnectSocial.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements ICommentService{
    private final CommentRepository commentRepository;
    private final IUserService userService;
    private final IPostService postService;
    private final PostRepository postRepository;

    @Override
    public CommentResponse createComment(CommentRequest commentRequest, int postId, int userId) {

        UserResponse userResponse = userService.getUserById(userId);
        User user = mapToUser(userResponse);

        PostResponse postResponse = postService.findPostById(postId);
        Post post = mapToPost(postResponse);

        Comment newComment = Comment.builder()
                .content(commentRequest.getContent())
                .user(user)
                .createdAt(LocalDateTime.now())
                .liked(new ArrayList<>())
                .build();

        post.getComments().add(newComment);

        postRepository.save(post);

        return mapToCommentResponse(commentRepository.save(newComment));
    }

    @Override
    public CommentResponse getCommentById(int commentId) throws Exception {

        Optional<Comment> commentOptional = commentRepository.findById(commentId);

        if(commentOptional.isPresent()){
            return mapToCommentResponse(commentOptional.get());
        }else {
            throw new Exception("Comment not found : " + commentId);
        }
    }

    @Override
    public CommentResponse likeComment(int commentId, int userId) throws Exception {

        CommentResponse commentResponse = getCommentById(commentId);
        Comment comment = mapToComment(commentResponse);

        UserResponse userResponse = userService.getUserById(userId);
        User user = mapToUser(userResponse);

        if(!comment.getLiked().contains(user)){
            comment.getLiked().add(user);
        }else{
            comment.getLiked().remove(user);
        }

        return mapToCommentResponse(commentRepository.save(comment));
    }

    /************* Map Comment to CommentResponse ***********************/

    private CommentResponse mapToCommentResponse(Comment comment){
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .user(comment.getUser())
                .liked(comment.getLiked())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    private User mapToUser(UserResponse userResponse){
        return User.builder()
                .id(userResponse.getId())
                .firstName(userResponse.getFirstName())
                .lastName(userResponse.getLastName())
                .email(userResponse.getEmail())
                .gender(userResponse.getGender())
                .followers(userResponse.getFollowers())
                .following(userResponse.getFollowing())
                .savedPosts(userResponse.getSavedPosts())
                .build();
    }

    private Comment mapToComment(CommentResponse commentResponse){
        return Comment.builder()
                .id(commentResponse.getId())
                .content(commentResponse.getContent())
                .user(commentResponse.getUser())
                .liked(commentResponse.getLiked())
                .createdAt(commentResponse.getCreatedAt())
                .build();
    }

    private Post mapToPost(PostResponse postResponse){
        return Post.builder()
                .id(postResponse.getId())
                .caption(postResponse.getCaption())
                .image(postResponse.getImage())
                .video(postResponse.getVideo())
                .user(postResponse.getUser())
                .createdAt(postResponse.getCreatedAt())
                .liked(postResponse.getLiked())
                .comments(postResponse.getComments())
                .build();
    }
}
