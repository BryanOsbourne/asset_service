package co.com.assets_service.repository;

import java.util.Optional;
import co.com.assets_service.model.CostCenter;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CostCenterRepository extends JpaRepository<CostCenter, Long> {
    Optional<CostCenter> findByName(String name);
    boolean existsByName(String name);
}