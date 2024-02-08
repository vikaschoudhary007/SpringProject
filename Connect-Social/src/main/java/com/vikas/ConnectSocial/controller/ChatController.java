package com.vikas.ConnectSocial.controller;

import com.vikas.ConnectSocial.dto.ChatRequest;
import com.vikas.ConnectSocial.dto.ChatResponse;
import com.vikas.ConnectSocial.dto.UserResponse;
import com.vikas.ConnectSocial.service.IChatService;
import com.vikas.ConnectSocial.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
@RequiredArgsConstructor
public class ChatController {

    private final IChatService chatService;
    private final IUserService userService;
    @PostMapping("/user/{userId2}")
    public ResponseEntity<ChatResponse> createChat(@RequestBody ChatRequest chatRequest,
                                                   @RequestHeader("Authorization") String jwt,
                                                   @PathVariable("userId2") int userId2){
        UserResponse signedInUser = userService.getUserByJwt(jwt);
        return new ResponseEntity<>(chatService.createChat(chatRequest,
                signedInUser.getId(), userId2), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<ChatResponse>> getChatsByUserId( @RequestHeader("Authorization") String jwt){
        UserResponse signedInUser = userService.getUserByJwt(jwt);
        return new ResponseEntity<>(chatService.getChatsByUserId(signedInUser.getId()), HttpStatus.OK);
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<ChatResponse> getChatById(@PathVariable("chatId") int chatId) throws Exception {
        return new ResponseEntity<>(chatService.getChatById(chatId), HttpStatus.CREATED);
    }
}
