package co.com.assets_service.model;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;
import java.util.Collection;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String lastName;

    @Column(length = 20, unique = true, nullable = false)
    private String username;

    @Column(length = 100, nullable = false)
    private String password;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dateCreation;

    @UpdateTimestamp
    private LocalDateTime dateModification;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean isEnabled;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private Set<UserAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities
                .stream()
                .map(s -> new SimpleGrantedAuthority(s.getAuthority().getCode()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

}