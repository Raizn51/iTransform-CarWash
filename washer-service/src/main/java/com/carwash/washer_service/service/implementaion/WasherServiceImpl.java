package com.carwash.washer_service.service.implementaion;


import com.carwash.washer_service.dto.WasherDTO;
import com.carwash.washer_service.exception.*;
import com.carwash.washer_service.model.Washer;
import com.carwash.washer_service.repository.WasherDAO;
import com.carwash.washer_service.service.WasherService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WasherServiceImpl implements WasherService {

    private final WasherDAO washerDAO;

    public WasherServiceImpl(WasherDAO washerDAO) {
        this.washerDAO = washerDAO;
    }

    @Override
    public WasherDTO createWasher(WasherDTO washerDTO) {
        Washer washer = new Washer(washerDTO);
        return toDTO(washerDAO.save(washer));
    }

    private WasherDTO toDTO(Washer washer) {
        return new WasherDTO(washer);

    }

    @Override
    public WasherDTO updateWasher(Long id, WasherDTO washerDTO) {
        Washer washer = (Washer) washerDAO.findAllById(id);
        washer.setFullName(washerDTO.getFullName());
        washer.setPhoneNumber(washerDTO.getPhoneNumber());
        washer.setProfileImageUrl(washerDTO.getProfileImageUrl());
        return toDTO(washerDAO.save(washer));
    }

    @Override
    public WasherDTO getWasherById(Long id) {
        return toDTO(washerDAO.findById(id)
                .orElseThrow(() -> new WasherNotFoundException("Washer not found with id " + id)));
    }

    @Override
    public List<WasherDTO> getAllWashers() {
        return toDTO(washerDAO.findAll());
    }

    private List<WasherDTO> toDTO(List<Washer> all) {
        List<WasherDTO> wash= new ArrayList<>();
        for(int i=0; i<all.size();i++)
        {
            wash.add(toDTO(all.get(i)));
        }
        return wash;
    }

    @Override
    public void deleteWasher(Long id) {
        washerDAO.deleteById(id);
    }

    @Override
    public WasherDTO updateProfile(Long id, WasherDTO washerDTO) {
        return updateWasher(id, washerDTO);
    }

    @Override
    public WasherDTO assignOrder(Long washerId, Long orderId) {
        Washer washer = (Washer) washerDAO.findAllById(washerId);
        washer.getOrderIds().add(orderId);
        return toDTO(washerDAO.save(washer));
    }


    @Override
    public WasherDTO updateProfileImage(Long id, String imageUrl) {
        return null;
    }
}
