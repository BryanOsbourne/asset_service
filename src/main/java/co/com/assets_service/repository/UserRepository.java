package co.com.assets_service.repository;

import java.util.Optional;
import co.com.assets_service.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
        SELECT DISTINCT u 
        FROM User u
        LEFT JOIN FETCH u.authorities ua
        LEFT JOIN FETCH ua.authority
        WHERE u.username = :username
    """)
    Optional<User> findByUsernameWithAuthorities(
            @Param("username") String username
    );

    Optional<User> findByUsername(String username);
}