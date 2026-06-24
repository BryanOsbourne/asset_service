package co.com.assets_service.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;
import co.com.assets_service.enums.MaintenanceTypeActivity;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Setter
@Getter
@Entity
@Table(name = "maintenanceActivity")
public class MaintenanceActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 254, nullable = false)
    private String observation;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MaintenanceTypeActivity maintenanceTypeActivity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maintenance_computer_id", nullable = false)
    @JsonBackReference
    private MaintenanceComputer maintenanceComputer;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dateCreation;

    @UpdateTimestamp
    private LocalDateTime dateModification;

}