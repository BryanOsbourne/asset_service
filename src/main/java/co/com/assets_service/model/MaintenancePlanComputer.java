package co.com.assets_service.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonBackReference;
import co.com.assets_service.enums.MaintenancePlanningState;

@Getter
@Setter
@Entity
@Table(
        name = "maintenance_plan_computer",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"computer_id", "maintenance_plan_id"}
                )
        }
)
public class MaintenancePlanComputer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime datePlanning;

    private LocalDateTime dateExecuted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "computer_id", nullable = false)
    @JsonBackReference
    private Computer computer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maintenance_plan_id", nullable = false)
    @JsonBackReference
    private MaintenancePlan maintenancePlan;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MaintenancePlanningState state;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dateCreation;

    @UpdateTimestamp
    private LocalDateTime dateModification;
}