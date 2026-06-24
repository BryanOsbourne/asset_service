package co.com.assets_service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import co.com.assets_service.model.MaintenanceActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface MaintenanceActivityRepository extends JpaRepository<MaintenanceActivity, Long> {
    List<MaintenanceActivity> findAllByMaintenanceComputerId(Long maintenanceComputerId);
    Page<MaintenanceActivity> findAllByMaintenanceComputerId(Long maintenanceComputerId, Pageable pageable);
}