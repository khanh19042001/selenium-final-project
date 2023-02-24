package com.misa.kiemtra.page.dataprovider;

import com.misa.helpers.ExcelHelpers;
import com.misa.kiemtra.page.models.ComputerModel;
import com.misa.kiemtra.page.models.PersonModel;
import lombok.var;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class PersonDataProvider {
    final ExcelHelpers xlsReader = new ExcelHelpers();

    @DataProvider(name = "PersonData")
    public Object[][] personData() {
        String path = System.getProperty("user.dir");
        String file = "/src/test/resources/datatest/khanh/PersonData.xlsx";

        String filePath = path + file;

        var data = xlsReader.readXLSData(filePath);
        List<PersonModel> personModels = new ArrayList<>();
        data.entrySet().stream().filter(v -> !v.getKey().equals("0")).forEach(v -> {
            var rowData = data.get(v.getKey());
            var person = PersonModel.builder().firstName(rowData.get(1)).lastName(rowData.get(2)).email(rowData.get(3)).userName(rowData.get(4)).passWord(rowData.get(5)).build();
            personModels.add(person);
        });
        System.out.println("Result");
        Object[][] result = new Object[personModels.size()][1];
        for (int i = 0; i < personModels.size(); i++) {
            result[i][0] = personModels.get(i);
        }
        return result;
    }
}
