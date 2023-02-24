package com.misa.kiemtra.page.dataprovider;

import com.misa.helpers.ExcelHelpers;
import com.misa.kiemtra.page.models.ComputerModel;
import com.misa.projects.amisaccounting.models.CustomFieldModel;
import lombok.var;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class ComputerDataProvider {
    final ExcelHelpers xlsReader = new ExcelHelpers();

    @DataProvider(name = "ComputerData")
    public Object[][] computerData(){
        String path = System.getProperty("user.dir");
        String file = "/src/test/resources/datatest/khanh/Computer.xlsx";

        String filePath = path + file;

        var data = xlsReader.readXLSData(filePath);
        List<ComputerModel> computerModels = new ArrayList<>();
        data.entrySet().stream().filter(v -> !v.getKey().equals("0")).forEach(v -> {
            var rowData = data.get(v.getKey());
            var computer = ComputerModel.builder().name(rowData.get(1)).introduced(rowData.get(2)).discontinued(rowData.get(3)).company(rowData.get(4)).build();
            computerModels.add(computer);
        });
        System.out.println("Result");
        Object[][] result = new Object[computerModels.size()][1];
        for (int i = 0; i < computerModels.size(); i++) {
            result[i][0] = computerModels.get(i);
        }
        return result;
    }
}
