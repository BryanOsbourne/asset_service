package co.com.assets_service.model;

import lombok.*;
import java.util.List;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Getter
@Setter
@Entity
@Table(name = "cost_center")
public class CostCenter {

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

    @OneToMany(
            mappedBy = "costCenter",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Column(unique = true)
    @JsonManagedReference
    private List<Department> departments;
}
