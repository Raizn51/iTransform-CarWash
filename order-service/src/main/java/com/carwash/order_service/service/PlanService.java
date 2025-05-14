package com.carwash.order_service.service;


import com.carwash.order_service.dto.PlanDTO;
import com.carwash.order_service.model.Plan;

import java.util.List;

public interface PlanService {
    PlanDTO createPackage(PlanDTO dto);
    List<PlanDTO> getAllPackages();
    Plan getPackageById(Long id);
}
