package io.leanteach.assessment.repository;

import io.leanteach.assessment.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findFirstByCandidateIdAndPositionId(long candidateId, long positionId);

    Optional<Employee> findFirstByIdAndCandidateId(long id, long candidateId);

    @Query(value = "select employee from Employee employee where " +
            "((:employeeName is null or lower(employee.candidate.name) like '%' || lower(cast(:employeeName as string)) || '%') or " +
            "(:employeeName is null or lower(employee.candidate.lastName) like '%' || lower(cast(:employeeName as string)) || '%')) and " +
            "(:positionId is null or employee.position.id = :positionId)")
    Page<Employee> findAll(@Param("employeeName") String employeeName, @Param("positionId") Long positionId, Pageable pageable);

}
