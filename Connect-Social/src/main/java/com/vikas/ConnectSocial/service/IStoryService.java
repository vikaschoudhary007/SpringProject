package com.vikas.ConnectSocial.service;

import com.vikas.ConnectSocial.dto.StoryRequest;
import com.vikas.ConnectSocial.dto.StoryResponse;

import java.util.List;

public interface IStoryService {
    public StoryResponse createStory(StoryRequest storyRequest, int userId);
    public List<StoryResponse> getStoryByUserId(int userId);
}
