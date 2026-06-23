package co.com.assets_service.repository;

import java.util.Optional;
import co.com.assets_service.model.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findById(Long id);
    Optional<Employee> findByNameAndLastNameAndSecondLastName(String name, String lastName, String secondLastName);
}