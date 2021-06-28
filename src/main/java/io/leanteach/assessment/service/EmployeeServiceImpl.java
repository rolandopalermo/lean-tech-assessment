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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CandidateService candidateService;
    private final PositionService positionService;

    @Override
    public EmployeeDto save(EmployeeRegistrationDto employeeRegistrationDto) {
        validateIfExists(employeeRegistrationDto.getCandidateId(), employeeRegistrationDto.getPositionId());
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

    @Override
    public Page<EmployeeDto> findAll(Optional<String> employeeName, Optional<Long> positionId, Pageable pageable) {
        String employeeNameValue = employeeName.orElse(null);
        Long positionIdValue = positionId.orElse(null);
        Page<Employee> page = employeeRepository.findAll(employeeNameValue, positionIdValue, pageable);
        return new PageImpl<>(
                page.getContent().stream().map(this::toDto).collect(Collectors.toList()),
                page.getPageable(),
                page.getTotalElements());
    }

    private Employee save(Candidate candidate, Position position, EmployeeRegistrationDto employeeRegistrationDto) {
        return Employee.builder()
                .candidate(candidate)
                .position(position)
                .salary(employeeRegistrationDto.getSalary())
                .build();
    }

    private void validateIfExists(long candidateId, long positionId) {
        Optional<Employee> optionalEmployee = employeeRepository.findFirstByCandidateIdAndPositionId(candidateId, positionId);
        if (optionalEmployee.isPresent()) {
            throw new AlreadyExistsException(format("The employee associate with the candidate %s and the position %s already exist", candidateId, positionId));
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

    private EmployeeDto toDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .position(positionService.toDto(employee.getPosition()))
                .person(candidateService.toDto(employee.getCandidate()))
                .salary(employee.getSalary())
                .build();
    }

}
