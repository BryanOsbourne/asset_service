package co.com.assets_service.repository;

import java.util.Optional;
import co.com.assets_service.model.Monitor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MonitorRepository extends JpaRepository<Monitor, Long> {
    boolean existsByName(String name);
    boolean existsByInternalCode(String internalCode);
    Optional<Monitor> findByName(String name);
    Optional<Monitor> findByInternalCode(String internalCode);
}