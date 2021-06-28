package io.leanteach.assessment.service;

import io.leanteach.assessment.dto.EmployeeDto;
import io.leanteach.assessment.dto.EmployeeRegistrationDto;
import io.leanteach.assessment.dto.UpdateEmployeeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EmployeeService {

    EmployeeDto save(EmployeeRegistrationDto employeeRegistrationDto);

    EmployeeDto update(Long employeeId, UpdateEmployeeDto updateEmployeeDto);

    void delete(long id);

    Page<EmployeeDto> findAll(Optional<String> employeeName, Optional<Long> positionId, Pageable pageable);

    Object findAllDetails();

}
