package co.com.assets_service.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

@Setter
@Getter
@Entity
@Table(name = "state")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    private String name;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dateCreation;

    @UpdateTimestamp
    private LocalDateTime dateModification;

}