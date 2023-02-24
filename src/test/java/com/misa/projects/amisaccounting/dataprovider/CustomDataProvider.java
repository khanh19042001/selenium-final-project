package com.misa.projects.amisaccounting.dataprovider;

import com.misa.common.XLSReader;
import com.misa.helpers.ExcelHelpers;
import com.misa.projects.amisaccounting.models.CustomFieldModel;
import lombok.var;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomDataProvider {
    final ExcelHelpers xlsReader = new ExcelHelpers();

    @DataProvider(name = "CustomField")
    public Object[][] loginDataProvider2(){
        String path = System.getProperty("user.dir");
        String file = "/src/test/resources/datatest/khanh/CustomField.xlsx";

        String filePath = path + file;

        var data = xlsReader.readXLSData(filePath);
        List<CustomFieldModel> customFieldModels = new ArrayList<>();
        data.entrySet().stream().filter(v -> !v.getKey().equals("0")).forEach(v -> {
            var rowData = data.get(v.getKey());
            var custom = CustomFieldModel.builder().name(rowData.get(1)).screen(rowData.get(2)).type(rowData.get(3)).select(rowData.get(4)).build();
            customFieldModels.add(custom);
        });
        System.out.println("Result");
        Object[][] result = new Object[customFieldModels.size()][1];
        for (int i = 0; i < customFieldModels.size(); i++) {
            result[i][0] = customFieldModels.get(i);
        }
        return result;
    }
}



