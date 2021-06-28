package io.leanteach.assessment.repository;

import io.leanteach.assessment.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findFirstByCandidateIdAndPositionId(long candidateId, long positionId);

}
