package io.leanteach.assessment.service;

import io.leanteach.assessment.domain.Candidate;
import io.leanteach.assessment.domain.Employee;
import io.leanteach.assessment.domain.Position;
import io.leanteach.assessment.dto.EmployeeDto;
import io.leanteach.assessment.dto.EmployeeRegistrationDto;
import io.leanteach.assessment.dto.UpdateEmployeeDto;
import io.leanteach.assessment.exception.AlreadyExistsException;
import io.leanteach.assessment.exception.ResourceNotFoundException;
import io.leanteach.assessment.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Candidate candidate = candidateService.findFirst(employeeRegistrationDto.getCandidateId());
        Position position = positionService.findFirst(employeeRegistrationDto.getPositionId());
        Employee employee = save(candidate, position, employeeRegistrationDto);
        employeeRepository.save(employee);
        return toDto(employee, candidate, position);
    }

    @Override
    @Transactional
    public EmployeeDto update(Long employeeId, UpdateEmployeeDto updateEmployeeDto) {
        Optional<Employee> optionalEmployee = employeeRepository.findFirstByIdAndCandidateId(employeeId, updateEmployeeDto.getPerson().getId());
        optionalEmployee.orElseThrow(() -> new ResourceNotFoundException(
                format("The employee [%s] associate with the person [%s] does not exist", employeeId, updateEmployeeDto.getPerson().getId())));
        Candidate candidate = candidateService.update(updateEmployeeDto.getPerson());
        Employee employee = optionalEmployee.get();
        employee.setPosition(positionService.findFirst(updateEmployeeDto.getPositionId()));
        employee.setSalary(updateEmployeeDto.getSalary());
        return toDto(employeeRepository.save(employee), candidate, employee.getPosition());
    }

    @Override
    public void delete(long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        optionalEmployee.orElseThrow(() -> new ResourceNotFoundException(
                format("The employee [%s] does not exist", id)));
        employeeRepository.deleteById(id);
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
                employeeRegistrationDto.getCandidateId(), employeeRegistrationDto.getPositionId());
        if (optionalEmployee.isPresent()) {
            throw new AlreadyExistsException(
                    format("The employee associate with the candidate %s and the position %s already exist",
                            employeeRegistrationDto.getCandidateId(),
                            employeeRegistrationDto.getPositionId()));
        }
    }

    private EmployeeDto toDto(Employee employee, Candidate candidate, Position position) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .position(positionService.toDto(position))
                .person(candidateService.toDto(candidate))
                .salary(employee.getSalary())
                .build();
    }

}
