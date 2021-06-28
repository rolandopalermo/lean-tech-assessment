package io.leanteach.assessment.dto.report;

import lombok.Data;

@Data
public class EmployeePersonDto {

    private String idNumber;
    private String name;
    private String lastName;
    private String address;
    private String cellPhone;
    private String cityName;

    public EmployeePersonDto(String idNumber, String name, String lastName, String address, String cellPhone, String cityName) {
        this.idNumber = idNumber;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.cellPhone = cellPhone;
        this.cityName = cityName;
    }

}
