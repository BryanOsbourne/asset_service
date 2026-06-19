package co.com.assets_service.repository;

import java.util.Optional;
import co.com.assets_service.model.State;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    Optional<State> findByName(String name);
    boolean existsByName(String name);
}