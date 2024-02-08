package com.vikas.ConnectSocial.repository;

import com.vikas.ConnectSocial.model.Chat;
import com.vikas.ConnectSocial.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {

    public List<Chat> findByUsersId(int userId);
    @Query("select c from Chat c Where :user1 Member of c.users And :user2 Member of c.users")
    public Chat findChatByUsersId(@Param("user1") User user1, @Param("user2") User user2);

}
