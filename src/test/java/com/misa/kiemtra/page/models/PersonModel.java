package com.misa.kiemtra.page.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonModel {
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String passWord;
}
