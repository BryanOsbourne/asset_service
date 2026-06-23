package co.com.assets_service.model;

import lombok.Setter;
import lombok.Getter;
import java.util.List;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Getter
@Setter
@Entity
@Table(name = "computer")
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Column(length = 50, nullable = false)
    private String serialNumber;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean isEnabled;

    @Column(length = 50, nullable = false)
    private String model;

    @ManyToOne
    @JoinColumn(name = "internal_code_id", nullable = false)
    @JsonBackReference
    private InternalCode internalCode;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    @JsonBackReference
    private State state;

    @ManyToOne
    @JoinColumn(name = "type_computer_id", nullable = false)
    @JsonBackReference
    private TypeComputer typeComputer;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id", nullable = false)
    @JsonBackReference
    private Manufacturer manufacturer;

    @OneToMany(
            mappedBy = "computer",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference
    private List<MaintenancePlanComputer> maintenancePlanComputers;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dateCreation;

    @UpdateTimestamp
    private LocalDateTime dateModification;
}