package com.vikas.ConnectSocial.service;

import com.vikas.ConnectSocial.dto.ReelRequest;
import com.vikas.ConnectSocial.dto.ReelResponse;
import com.vikas.ConnectSocial.dto.UserResponse;
import com.vikas.ConnectSocial.model.Reel;
import com.vikas.ConnectSocial.model.User;
import com.vikas.ConnectSocial.repository.ReelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReelServiceImpl implements IReelService{

    private final ReelRepository reelRepository;
    private final IUserService userService;

    @Override
    public ReelResponse createReel(ReelRequest reelRequest, int userId) {

        UserResponse userResponse = userService.getUserById(userId);
        User user = mapToUser(userResponse);

        Reel newReel = Reel.builder()
                .title(reelRequest.getTitle())
                .video(reelRequest.getVideo())
                .user(user)
                .build();

        return mapToReelResponse(reelRepository.save(newReel));
    }

    @Override
    public List<ReelResponse> getAllReels() {
        List<Reel> reels = reelRepository.findAll();
        return reels.stream().map(this::mapToReelResponse).toList();
    }

    @Override
    public List<ReelResponse> getReelsByUserId(int userId) {

        userService.getUserById(userId);
        List<Reel> reels = reelRepository.findByUserId(userId);
        return reels.stream().map(this::mapToReelResponse).toList();
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

    private ReelResponse mapToReelResponse(Reel reel){
        return ReelResponse.builder()
                .id(reel.getId())
                .title(reel.getTitle())
                .video(reel.getVideo())
                .user(reel.getUser())
                .build();
    }

    private Reel mapToReel(ReelResponse reelResponse){
        return Reel.builder()
                .id(reelResponse.getId())
                .title(reelResponse.getTitle())
                .video(reelResponse.getVideo())
                .user(reelResponse.getUser())
                .build();
    }
}
