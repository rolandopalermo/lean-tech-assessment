package io.leanteach.assessment.controller;

import io.leanteach.assessment.dto.CandidateDto;
import io.leanteach.assessment.dto.EmployeeRegistrationDto;
import io.leanteach.assessment.dto.HttpResponseDto;
import io.leanteach.assessment.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"api/v1/employees"})
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<HttpResponseDto<CandidateDto>> save(@RequestBody EmployeeRegistrationDto employeeRegistrationDto) {
        return new ResponseEntity<>(new HttpResponseDto(true, employeeService.save(employeeRegistrationDto)), HttpStatus.CREATED);
    }

}
