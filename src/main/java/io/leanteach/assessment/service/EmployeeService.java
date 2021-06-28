package io.leanteach.assessment.service;

import io.leanteach.assessment.dto.EmployeeDto;
import io.leanteach.assessment.dto.EmployeeRegistrationDto;
import io.leanteach.assessment.dto.UpdateEmployeeDto;

public interface EmployeeService {

    EmployeeDto save(EmployeeRegistrationDto employeeRegistrationDto);

    EmployeeDto update(Long employeeId, UpdateEmployeeDto updateEmployeeDto);

    void delete(long id);

}
