package co.com.assets_service.repository;

import java.util.Optional;
import co.com.assets_service.model.TypeComputer;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TypeComputerRepository extends JpaRepository<TypeComputer, Long> {
    boolean existsByName(String name);
    Optional<TypeComputer> findByName(String name);
}