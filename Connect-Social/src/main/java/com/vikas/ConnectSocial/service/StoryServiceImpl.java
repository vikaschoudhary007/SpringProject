package com.vikas.ConnectSocial.service;

import com.vikas.ConnectSocial.dto.StoryRequest;
import com.vikas.ConnectSocial.dto.StoryResponse;
import com.vikas.ConnectSocial.dto.UserResponse;
import com.vikas.ConnectSocial.model.Story;
import com.vikas.ConnectSocial.model.User;
import com.vikas.ConnectSocial.repository.StoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoryServiceImpl implements IStoryService{

    private final StoryRepository storyRepository;
    private final IUserService userService;
    @Override
    public StoryResponse createStory(StoryRequest storyRequest, int userId) {

        UserResponse userResponse = userService.getUserById(userId);
        User user = mapToUser(userResponse);

        Story newStory = Story.builder()
                .user(user)
                .image(storyRequest.getImage())
                .caption(storyRequest.getCaption())
                .createdAt(LocalDateTime.now())
                .build();

        return mapToStoryResponse(storyRepository.save(newStory));
    }

    @Override
    public List<StoryResponse> getStoryByUserId(int userId) {

        userService.getUserById(userId);
        List<Story> stories = storyRepository.findByUserId(userId);
        return stories.stream().map(this::mapToStoryResponse).toList();
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

    private StoryResponse mapToStoryResponse(Story story){
        return StoryResponse.builder()
                .id(story.getId())
                .user(story.getUser())
                .image(story.getImage())
                .caption(story.getCaption())
                .createdAt(story.getCreatedAt())
                .build();
    }
}
