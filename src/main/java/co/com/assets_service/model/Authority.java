package co.com.assets_service.model;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Getter
@Setter
@Entity
@Table(name = "authority")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String code;

    @Column(length = 100, nullable = false)
    private String description;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dateCreation;

    @OneToMany(mappedBy = "authority")
    @JsonManagedReference
    private Set<UserAuthority> users;
}