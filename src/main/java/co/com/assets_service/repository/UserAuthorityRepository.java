package co.com.assets_service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.assets_service.model.UserAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Long> {
    Page<UserAuthority> findAllByUserId(Long userId, Pageable pageable);
    boolean existsByUserIdAndAuthorityId(Long userId, Long authorityId);
}