package capstone.project.credit_manager.repository;

import capstone.project.credit_manager.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
