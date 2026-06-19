package co.com.assets_service.repository;

import co.com.assets_service.model.Authority;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}