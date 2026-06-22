package co.com.assets_service.repository;

import java.util.Optional;
import co.com.assets_service.model.Computer;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {
    boolean existsByName(String name);
    boolean existsByInternalCode(String internalCode);
    Optional<Computer> findByName(String name);
    Optional<Computer> findByInternalCode(String internalCode);
}