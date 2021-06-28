package io.leanteach.assessment.service;

import io.leanteach.assessment.domain.Candidate;
import io.leanteach.assessment.domain.Employee;
import io.leanteach.assessment.domain.Position;
import io.leanteach.assessment.dto.EmployeeDto;
import io.leanteach.assessment.dto.EmployeeRegistrationDto;
import io.leanteach.assessment.exception.AlreadyExistsException;
import io.leanteach.assessment.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CandidateService candidateService;
    private final PositionService positionService;

    @Override
    public EmployeeDto save(EmployeeRegistrationDto employeeRegistrationDto) {
        validateIfExists(employeeRegistrationDto);
        Candidate candidate = candidateService.findFirst(employeeRegistrationDto.getIdCandidate());
        Position position = positionService.findFirst(employeeRegistrationDto.getIdPosition());
        Employee employee = save(candidate, position, employeeRegistrationDto);
        employeeRepository.save(employee);
        return EmployeeDto.builder()
                .id(employee.getId())
                .position(positionService.toDto(position))
                .person(candidateService.toDto(candidate))
                .salary(employeeRegistrationDto.getSalary())
                .build();
    }

    private Employee save(Candidate candidate, Position position, EmployeeRegistrationDto employeeRegistrationDto) {
        return Employee.builder()
                .candidate(candidate)
                .position(position)
                .salary(employeeRegistrationDto.getSalary())
                .build();
    }

    private void validateIfExists(EmployeeRegistrationDto employeeRegistrationDto) {
        Optional<Employee> optionalEmployee = employeeRepository.findFirstByCandidateIdAndPositionId(
                employeeRegistrationDto.getIdCandidate(), employeeRegistrationDto.getIdPosition());
        if (optionalEmployee.isPresent()) {
            throw new AlreadyExistsException(
                    format("The employee associate with the candidate %s and the position %s already exist",
                            employeeRegistrationDto.getIdCandidate(),
                            employeeRegistrationDto.getIdPosition()));
        }
    }

}
