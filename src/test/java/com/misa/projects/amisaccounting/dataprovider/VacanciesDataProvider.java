package com.misa.projects.amisaccounting.dataprovider;

import com.misa.helpers.ExcelHelpers;
import com.misa.projects.amisaccounting.models.EmployeeModel;
import com.misa.projects.amisaccounting.models.VacanciesModel;
import lombok.var;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class VacanciesDataProvider {
    final ExcelHelpers xlsReader = new ExcelHelpers();

    @DataProvider(name = "Vacancies")
    public Object[][] employeeDataProvider(){
        String path = System.getProperty("user.dir");
        String file = "/src/test/resources/datatest/khanh/Vacancies.xlsx";

        String filePath = path + file;

        var data = xlsReader.readXLSData(filePath);
        List<VacanciesModel> vacanciesModels = new ArrayList<>();
        data.entrySet().stream().filter(v -> !v.getKey().equals("0")).forEach(v -> {
            var rowData = data.get(v.getKey());
            var vacancies = VacanciesModel.builder().vacanciesName(rowData.get(1)).job(rowData.get(2)).hiringManager(rowData.get(3)).number(rowData.get(4)).build();
            vacanciesModels.add(vacancies);
        });
        System.out.println("Result");
        Object[][] result = new Object[vacanciesModels.size()][1];
        for (int i = 0; i < vacanciesModels.size(); i++) {
            result[i][0] = vacanciesModels.get(i);
        }
        return result;
    }
}
