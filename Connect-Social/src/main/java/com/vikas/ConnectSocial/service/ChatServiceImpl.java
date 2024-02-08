package com.vikas.ConnectSocial.service;

import com.vikas.ConnectSocial.dto.UserResponse;
import com.vikas.ConnectSocial.model.Chat;
import com.vikas.ConnectSocial.model.User;
import com.vikas.ConnectSocial.repository.ChatRepository;
import com.vikas.ConnectSocial.dto.ChatRequest;
import com.vikas.ConnectSocial.dto.ChatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements IChatService{

    private final ChatRepository chatRepository;
    private final IUserService userService;
    @Override
    public ChatResponse createChat(ChatRequest chatRequest, int userId1, int userId2) {
        UserResponse userResponse1 = userService.getUserById(userId1);
        User user1 = mapToUser(userResponse1);
        UserResponse userResponse2 = userService.getUserById(userId2);
        User user2 = mapToUser(userResponse2);

        Chat existingChat = chatRepository.findChatByUsersId(user1, user2);

        if(existingChat != null){
            return mapToChatResponse(existingChat);
        }

        Chat newChat = Chat.builder()
                .chatName(chatRequest.getChatName())
                .chatImage(chatRequest.getChatImage())
                .users(new ArrayList<>(List.of(user1, user2)))
                .timeStamp(LocalDateTime.now())
                .messages(new ArrayList<>())
                .build();

        return mapToChatResponse(chatRepository.save(newChat));
    }

    @Override
    public ChatResponse getChatById(int chatId) throws Exception {

        Optional<Chat> chatOptional = chatRepository.findById(chatId);

        if(chatOptional.isEmpty()){
            throw new Exception("Chat not found with id : " + chatId);
        }
        return mapToChatResponse(chatOptional.get());
    }

    @Override
    public List<ChatResponse> getChatsByUserId(int userId) {

        List<Chat> chats = chatRepository.findByUsersId(userId);
        return chats.stream().map(this::mapToChatResponse).toList();
    }


    private ChatResponse mapToChatResponse(Chat chat){
     return ChatResponse.builder()
             .id(chat.getId())
             .chatName(chat.getChatName())
             .chatImage(chat.getChatImage())
             .users(chat.getUsers())
             .timeStamp(chat.getTimeStamp())
             .messages(chat.getMessages())
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
}
