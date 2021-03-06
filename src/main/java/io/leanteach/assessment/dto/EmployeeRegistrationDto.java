package io.leanteach.assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRegistrationDto {

    private long candidateId;
    private long positionId;
    private BigDecimal salary;

}
