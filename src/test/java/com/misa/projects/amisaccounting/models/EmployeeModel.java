package com.misa.projects.amisaccounting.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeModel {
    private String firstName;
    private String middleName;
    private String lastName;
    private String employeeId;
    private String nickName;
    private String otherId;
    private String licenseNumber;
    private String dayLicense;
    private String monthLicense;
    private String yearLicense;
    private String ssnNumber;
    private String sinNumber;
    private String nationality;
    private String status;
    private String dayOfBirth;
    private String monthOfBirth;
    private String yearOfBirth;
    private String gender;

}
