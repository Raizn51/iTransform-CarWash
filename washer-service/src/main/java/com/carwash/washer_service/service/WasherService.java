package com.carwash.washer_service.service;



import com.carwash.washer_service.dto.WasherDTO;

import java.util.List;

public interface WasherService {
    WasherDTO createWasher(WasherDTO washerDTO);
    WasherDTO updateWasher(Long id, WasherDTO washerDTO);
    WasherDTO getWasherById(Long id);
    List<WasherDTO> getAllWashers();
    void deleteWasher(Long id);
    WasherDTO updateProfile(Long id, WasherDTO washerDTO);
    WasherDTO assignOrder(Long washerId, Long orderId);

    WasherDTO updateProfileImage(Long id, String imageUrl);
}
