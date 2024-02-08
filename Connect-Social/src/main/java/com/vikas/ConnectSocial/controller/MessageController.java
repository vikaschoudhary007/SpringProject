package com.vikas.ConnectSocial.controller;

import com.vikas.ConnectSocial.dto.MessageRequest;
import com.vikas.ConnectSocial.dto.MessageResponse;
import com.vikas.ConnectSocial.dto.UserResponse;
import com.vikas.ConnectSocial.service.IMessageService;
import com.vikas.ConnectSocial.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final IMessageService messageService;
    private final IUserService userService;

    @PostMapping("/chats/{chatId}")
    public ResponseEntity<MessageResponse> createMessage(
            @RequestBody MessageRequest messageRequest,
            @RequestHeader("Authorization") String jwt,
            @PathVariable("chatId") int chatId) throws Exception {
        UserResponse signedInUser = userService.getUserByJwt(jwt);
        return new ResponseEntity<>(messageService.createMessage(messageRequest, signedInUser.getId(),chatId), HttpStatus.CREATED);

    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<MessageResponse>> getMessagesByChatId(
            @PathVariable("chatId") int chatId) throws Exception {
        return new ResponseEntity<>(messageService.getMessagesByChatId(chatId), HttpStatus.OK);

    }

}
