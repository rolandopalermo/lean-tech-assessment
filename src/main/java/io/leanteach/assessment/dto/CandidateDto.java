package io.leanteach.assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDto {

    private Long id;
    private String idNumber;
    private String name;
    private String lastName;
    private String address;
    private String cellPhone;
    private String cityName;

}
