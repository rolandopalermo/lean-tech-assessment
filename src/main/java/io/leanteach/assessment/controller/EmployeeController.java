package io.leanteach.assessment.controller;

import io.leanteach.assessment.dto.CandidateDto;
import io.leanteach.assessment.dto.EmployeeRegistrationDto;
import io.leanteach.assessment.dto.HttpResponseDto;
import io.leanteach.assessment.dto.UpdateEmployeeDto;
import io.leanteach.assessment.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = {"api/v1/employees"})
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<HttpResponseDto<CandidateDto>> save(@RequestBody EmployeeRegistrationDto employeeRegistrationDto) {
        return new ResponseEntity<>(new HttpResponseDto(true, employeeService.save(employeeRegistrationDto)), HttpStatus.CREATED);
    }

    @PutMapping("{employee-id}")
    public ResponseEntity<HttpResponseDto<CandidateDto>> update(@PathVariable("employee-id") Long employeeId,
                                                                @RequestBody UpdateEmployeeDto updateEmployeeDto) {
        return new ResponseEntity<>(new HttpResponseDto(true, employeeService.update(employeeId, updateEmployeeDto)), HttpStatus.OK);
    }

    @DeleteMapping("{employee-id}")
    public ResponseEntity<HttpResponseDto<?>> delete(@PathVariable("employee-id") Long employeeId) {
        employeeService.delete(employeeId);
        return new ResponseEntity<>(new HttpResponseDto(true, null), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<HttpResponseDto<Page<CandidateDto>>> findAll(@RequestParam(value = "employeeName") Optional<String> employeeName,
                                                                       @RequestParam(value = "positionId") Optional<Long> positionId,
                                                                       Pageable pageable) {
        return new ResponseEntity<>(new HttpResponseDto(true, employeeService.findAll(employeeName, positionId, pageable)), HttpStatus.OK);
    }

    @GetMapping("report")
    public ResponseEntity<HttpResponseDto<?>> findAllDetails() {
        return new ResponseEntity<>(new HttpResponseDto(true, employeeService.findAllDetails()), HttpStatus.OK);
    }

}
