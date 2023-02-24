package com.misa.kiemtra.page.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComputerModel {
    private String name;
    private String introduced;
    private String discontinued;
    private String company;
}
