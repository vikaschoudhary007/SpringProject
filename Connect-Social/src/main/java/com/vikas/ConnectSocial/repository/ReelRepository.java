package com.vikas.ConnectSocial.repository;

import com.vikas.ConnectSocial.dto.ReelResponse;
import com.vikas.ConnectSocial.model.Reel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReelRepository extends JpaRepository<Reel, Integer> {

//    @Query("select r from Reel r where r.user.id=:userId")
    public List<Reel> findByUserId(int userId);
}
