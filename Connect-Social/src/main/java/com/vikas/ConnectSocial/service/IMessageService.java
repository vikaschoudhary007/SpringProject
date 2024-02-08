package com.vikas.ConnectSocial.service;

import com.vikas.ConnectSocial.dto.MessageRequest;
import com.vikas.ConnectSocial.dto.MessageResponse;

import java.util.List;

public interface IMessageService {

    public MessageResponse createMessage(MessageRequest messageRequest, int userId, int chatId) throws Exception;
    public List<MessageResponse> getMessagesByChatId(int chatId) throws Exception;
}
