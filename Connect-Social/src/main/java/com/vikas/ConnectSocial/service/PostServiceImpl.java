package com.vikas.ConnectSocial.service;

import com.vikas.ConnectSocial.dto.PostRequest;
import com.vikas.ConnectSocial.dto.PostResponse;
import com.vikas.ConnectSocial.dto.UserResponse;
import com.vikas.ConnectSocial.exceptions.PostNotFoundException;
import com.vikas.ConnectSocial.exceptions.UserNotFoundException;
import com.vikas.ConnectSocial.model.Post;
import com.vikas.ConnectSocial.model.User;
import com.vikas.ConnectSocial.repository.PostRepository;
import com.vikas.ConnectSocial.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements IPostService{

    private final PostRepository postRepository;
    private final UserServiceImpl userService;
    private final UserRepository userRepository;
    @Override
    public PostResponse createPost(PostRequest postRequest, int userId) throws UserNotFoundException {

        UserResponse userResponse = userService.getUserById(userId);

        User user = mapToUser(userResponse);

        Post newPost = Post.builder()
                .caption(postRequest.getCaption())
                .image(postRequest.getImage())
                .video(postRequest.getVideo())
                .user(user)
                .createdAt(LocalDate.now())
                .liked(new ArrayList<>())
                .comments(new ArrayList<>())
                .build();

        Post savedPost = postRepository.save(newPost);
        return mapToPostResponse(savedPost);

    }

    @Override
    public String deletePost(int postId, int userId) throws Exception {

        UserResponse userResponse = userService.getUserById(userId);
        User user = mapToUser(userResponse);
        PostResponse postResponse = findPostById(postId);

        if(postResponse.getUser().getId() != user.getId()){
            throw new Exception("Not Authorized");
        }

        Post post = mapToPost(postResponse);

        postRepository.delete(post);
        return "Post deleted successfully";
    }

    @Override
    public List<PostResponse> findPostByUserId(int userId) throws UserNotFoundException {

        List<Post> posts = postRepository.findPostByUserId(userId);
        return posts.stream().map(this::mapToPostResponse).toList();
    }

    @Override
    public PostResponse findPostById(int postId) throws PostNotFoundException {

        Optional<Post> postOptional = postRepository.findById(postId);

        if(postOptional.isPresent()){
            return mapToPostResponse(postOptional.get());
        }else {
            throw new PostNotFoundException("Post Not Found : "+ postId);
        }

    }

    @Override
    public List<PostResponse> findAllPosts() throws PostNotFoundException {

        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapToPostResponse).toList();
    }

    @Override
    public PostResponse savePost(int postId, int userId) throws UserNotFoundException, PostNotFoundException {

        UserResponse userResponse = userService.getUserById(userId);
        User user = mapToUser(userResponse);
        PostResponse postResponse = findPostById(postId);
        Post post = mapToPost(postResponse);

        if(user.getSavedPosts().contains(post)){
            user.getSavedPosts().remove(post);
        }else{
            user.getSavedPosts().add(post);
        }

        userRepository.save(user);
        return mapToPostResponse(post);
    }

    @Override
    public PostResponse likePost(int postId, int userId) throws UserNotFoundException, PostNotFoundException {

        UserResponse userResponse = userService.getUserById(userId);
        User user = mapToUser(userResponse);
        PostResponse postResponse = findPostById(postId);
        Post post = mapToPost(postResponse);

        if(post.getLiked().contains(user)){
            post.getLiked().remove(user);
        }else {
            post.getLiked().add(user);
        }

        return mapToPostResponse(postRepository.save(post));
    }


    /************* MAP POST to Post Response ***********************/

    private PostResponse mapToPostResponse(Post post){
        return PostResponse.builder()
                .id(post.getId())
                .caption(post.getCaption())
                .image(post.getImage())
                .video(post.getVideo())
                .user(post.getUser())
                .createdAt(post.getCreatedAt())
                .liked(post.getLiked())
                .comments(post.getComments())
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

    /*****************************************************************/

}
