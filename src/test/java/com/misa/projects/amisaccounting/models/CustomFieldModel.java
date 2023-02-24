package com.misa.projects.amisaccounting.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomFieldModel {
    private String name;
    private String screen;
    private String type;
    private String select;
}
