package com.carwash.order_service.service.Implementation;

import com.carwash.order_service.dto.PlanDTO;
import com.carwash.order_service.exception.ResourceNotFoundException;
import com.carwash.order_service.model.Plan;
import com.carwash.order_service.repository.PlanDAO;
import com.carwash.order_service.service.PlanService;
import com.carwash.order_service.config.RabbitMQConfig;
import com.carwash.order_service.event.PlanCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanServiceImp implements PlanService {

    private final PlanDAO planDAO;
    private final RabbitTemplate rabbitTemplate;

    public PlanServiceImp(PlanDAO planDAO, RabbitTemplate rabbitTemplate) {
        this.planDAO = planDAO;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public PlanDTO createPackage(PlanDTO dto) {
        Plan pkg = new Plan(null, dto.getPackageName(), dto.getDescription(),
                dto.getPrice(), dto.getCreatedBy());
        pkg = planDAO.save(pkg);

        // Publish PlanCreatedEvent
        publishPlanCreatedEvent(pkg);

        return toDTO(pkg);
    }

    @Override
    public List<PlanDTO> getAllPackages() {
        return planDAO.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public Plan getPackageById(Long id) {
        Plan pkg = planDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));
        return pkg;
    }

    private PlanDTO toDTO(Plan p) {
        return new PlanDTO(p.getId(), p.getPackageName(), p.getDescription(),
                p.getPrice(), p.getCreatedBy());
    }

    private void publishPlanCreatedEvent(Plan plan) {
        PlanCreatedEvent event = new PlanCreatedEvent(
                plan.getId(),
                plan.getPackageName(),
                plan.getPrice()
        );

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.ORDER_EXCHANGE,
                "plan.created",
                event
        );
    }
}