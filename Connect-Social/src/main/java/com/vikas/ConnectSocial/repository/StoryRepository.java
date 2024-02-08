package com.vikas.ConnectSocial.repository;

import com.vikas.ConnectSocial.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StoryRepository extends JpaRepository<Story, Integer> {
    public List<Story> findByUserId(int userId);
}
