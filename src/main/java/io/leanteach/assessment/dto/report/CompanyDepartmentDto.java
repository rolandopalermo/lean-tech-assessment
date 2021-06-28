package io.leanteach.assessment.dto.report;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CompanyDepartmentDto {

    private Long id;
    private String name;
    private List<EmployeeEntryDto> employees = new ArrayList<>();

}
