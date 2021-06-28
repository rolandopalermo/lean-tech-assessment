package io.leanteach.assessment.dto;

import io.leanteach.assessment.dto.report.EmployeeEntryDto;
import io.leanteach.assessment.dto.report.EmployeePersonDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeInfoDto {

    private long positionId;
    private String positionName;
    private EmployeeEntryDto employee;

    public EmployeeInfoDto(long positionId, String positionName, Long id, BigDecimal salary, String idNumber, String name, String lastName, String address, String cellPhone, String cityName) {
        this.positionId = positionId;
        this.positionName = positionName;
        employee = new EmployeeEntryDto(id, salary, new EmployeePersonDto(idNumber, name, lastName, address, cellPhone, cityName));
    }

}