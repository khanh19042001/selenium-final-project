package com.misa.projects.amisaccounting.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacanciesModel {

    private String vacanciesName;
    private String job;
    private String hiringManager;
    private String number;
}
