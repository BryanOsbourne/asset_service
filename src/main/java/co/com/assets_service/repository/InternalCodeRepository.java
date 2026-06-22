package co.com.assets_service.repository;

import java.util.Optional;
import co.com.assets_service.model.InternalCode;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface InternalCodeRepository extends JpaRepository<InternalCode, Long> {
    boolean existsByPrefix(String prefix);
    boolean existsBySeries(String series);
    Optional<InternalCode> findByPrefix(String prefix);
    Optional<InternalCode> findBySeries(String series);
}