package io.leanteach.assessment.service;

import io.leanteach.assessment.domain.Candidate;
import io.leanteach.assessment.dto.CandidateDto;
import io.leanteach.assessment.dto.EmployeeDto;
import io.leanteach.assessment.dto.EmployeeRegistrationDto;

public interface EmployeeService {

    EmployeeDto save(EmployeeRegistrationDto employeeRegistrationDto);

}
