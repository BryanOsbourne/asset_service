package co.com.assets_service.repository;

import java.util.Optional;
import co.com.assets_service.model.Manufacturer;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    boolean existsByName(String name);
    Optional<Manufacturer> findByName(String name);
}