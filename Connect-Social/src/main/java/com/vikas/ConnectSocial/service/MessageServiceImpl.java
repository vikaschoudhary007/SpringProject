package com.vikas.ConnectSocial.service;

import com.vikas.ConnectSocial.dto.ChatResponse;
import com.vikas.ConnectSocial.dto.MessageRequest;
import com.vikas.ConnectSocial.dto.MessageResponse;
import com.vikas.ConnectSocial.dto.UserResponse;
import com.vikas.ConnectSocial.model.Chat;
import com.vikas.ConnectSocial.model.Message;
import com.vikas.ConnectSocial.model.User;
import com.vikas.ConnectSocial.repository.ChatRepository;
import com.vikas.ConnectSocial.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements IMessageService{

    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final IUserService userService;
    private final IChatService chatService;
    @Override
    public MessageResponse createMessage(MessageRequest messageRequest, int userId, int chatId) throws Exception {

        UserResponse userResponse = userService.getUserById(userId);
        User user = mapToUser(userResponse);

        ChatResponse chatResponse = chatService.getChatById(chatId);
        Chat chat = mapToChat(chatResponse);

        Message newMessage = Message.builder()
                .content(messageRequest.getContent())
                .image(messageRequest.getImage())
                .user(user)
                .chat(chat)
                .timestamp(LocalDateTime.now())
                .build();

        chat.getMessages().add(newMessage);
        chatRepository.save(chat);

        return mapToMessageResponse(messageRepository.save(newMessage));
    }

    @Override
    public List<MessageResponse> getMessagesByChatId(int chatId) throws Exception {

        chatService.getChatById(chatId);
        List<Message> messages = messageRepository.findByChatId(chatId);
        return messages.stream().map(this::mapToMessageResponse).toList();
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

    private Chat mapToChat(ChatResponse chatResponse){
        return Chat.builder()
                .id(chatResponse.getId())
                .chatName(chatResponse.getChatName())
                .chatImage(chatResponse.getChatImage())
                .users(chatResponse.getUsers())
                .timeStamp(chatResponse.getTimeStamp())
                .messages(chatResponse.getMessages())
                .build();
    }

    private MessageResponse mapToMessageResponse(Message message){
        return MessageResponse.builder()
                .id(message.getId())
                .content(message.getContent())
                .image(message.getImage())
                .user(message.getUser())
                .chat(message.getChat())
                .timestamp(message.getTimestamp())
                .build();
    }
}
