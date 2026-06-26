package co.com.assets_service.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import co.com.assets_service.model.MaintenanceComputer;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MaintenanceComputerRepository extends JpaRepository<MaintenanceComputer, Long> {
    Optional<MaintenanceComputer> findByMaintenancePlanComputerId(Long maintenancePlanId);
    Page<MaintenanceComputer> findAllByMaintenancePlanComputerId(Long maintenancePlanComputerId, Pageable pageable);
}