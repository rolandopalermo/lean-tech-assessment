package io.leanteach.assessment.dto.report;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmployeeEntryDto {

    private Long id;
    private BigDecimal salary;
    private EmployeePersonDto person;

    public EmployeeEntryDto(Long id, BigDecimal salary, EmployeePersonDto person) {
        this.id = id;
        this.salary = salary;
        this.person = person;
    }

}
