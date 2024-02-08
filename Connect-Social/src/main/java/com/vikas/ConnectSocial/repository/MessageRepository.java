package com.vikas.ConnectSocial.repository;

import com.vikas.ConnectSocial.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    public List<Message> findByChatId(int chatId);
}
