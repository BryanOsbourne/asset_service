package co.com.assets_service.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import co.com.assets_service.model.MaintenancePlanComputer;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MaintenancePlanComputerRepository extends JpaRepository<MaintenancePlanComputer, Long> {
    Page<MaintenancePlanComputer> findAllByComputerId(Long computerId, Pageable pageable);
    Page<MaintenancePlanComputer> findAllByMaintenancePlanId(Long maintenancePlanId, Pageable pageable);
    Optional<MaintenancePlanComputer> findByComputerIdAndMaintenancePlanId(Long computerId, Long maintenancePlanId);
}