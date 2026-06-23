package co.com.assets_service.repository;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import co.com.assets_service.model.MaintenancePlan;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MaintenancePlanRepository extends JpaRepository<MaintenancePlan, Long> {
    Optional<MaintenancePlan> findByName(String name);
}