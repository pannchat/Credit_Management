package capstone.project.credit_manager.repository;


import capstone.project.credit_manager.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}