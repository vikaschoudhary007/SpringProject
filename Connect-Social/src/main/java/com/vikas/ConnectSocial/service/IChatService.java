package com.vikas.ConnectSocial.service;

import com.vikas.ConnectSocial.dto.ChatRequest;
import com.vikas.ConnectSocial.dto.ChatResponse;

import java.util.List;

public interface IChatService {

    public ChatResponse createChat(ChatRequest chatRequest, int userId1, int userId2);
    public ChatResponse getChatById(int chatId) throws Exception;

    public List<ChatResponse> getChatsByUserId(int userId);
}
