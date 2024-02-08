package com.vikas.ConnectSocial.service;

import com.vikas.ConnectSocial.dto.ReelRequest;
import com.vikas.ConnectSocial.dto.ReelResponse;

import java.util.List;

public interface IReelService {

    public ReelResponse createReel(ReelRequest reelRequest, int userId);
    public List<ReelResponse> getAllReels();
    public List<ReelResponse> getReelsByUserId(int userId);

}
