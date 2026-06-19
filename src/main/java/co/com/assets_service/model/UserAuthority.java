package co.com.assets_service.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Getter
@Setter
@Entity
@Table(
        name = "user_authority",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_user_user_authority",
                        columnNames = {"user_id", "authority_id"}
                )
        }
)
public class UserAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authority_id", nullable = false)
    @JsonBackReference
    private Authority authority;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dateCreation;

}