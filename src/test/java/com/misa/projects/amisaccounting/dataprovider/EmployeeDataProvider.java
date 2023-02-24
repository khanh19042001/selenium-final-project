package com.misa.projects.amisaccounting.dataprovider;

import com.misa.helpers.ExcelHelpers;
import com.misa.projects.amisaccounting.models.CustomFieldModel;
import com.misa.projects.amisaccounting.models.EmployeeModel;
import lombok.var;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDataProvider {
    final ExcelHelpers xlsReader = new ExcelHelpers();

    @DataProvider(name = "Employee")
    public Object[][] employeeDataProvider(){
        String path = System.getProperty("user.dir");
        String file = "/src/test/resources/datatest/khanh/Employee.xlsx";

        String filePath = path + file;

        var data = xlsReader.readXLSData(filePath);
        List<EmployeeModel> employeeModels = new ArrayList<>();
        data.entrySet().stream().filter(v -> !v.getKey().equals("0")).forEach(v -> {
            var rowData = data.get(v.getKey());
            var employee = EmployeeModel.builder().firstName(rowData.get(1)).middleName(rowData.get(2)).lastName(rowData.get(3)).employeeId(rowData.get(4)).nickName(rowData.get(5)).otherId(rowData.get(6)).licenseNumber(rowData.get(7)).dayLicense(rowData.get(8)).monthLicense(rowData.get(9)).yearLicense(rowData.get(10)).ssnNumber(rowData.get(11)).sinNumber(rowData.get(12)).nationality(rowData.get(13)).status(rowData.get(14)).dayOfBirth(rowData.get(15)).monthOfBirth(rowData.get(16)).yearOfBirth(rowData.get(17)).gender(rowData.get(18)).build();
            employeeModels.add(employee);
        });
        System.out.println("Result");
        Object[][] result = new Object[employeeModels.size()][1];
        for (int i = 0; i < employeeModels.size(); i++) {
            result[i][0] = employeeModels.get(i);
        }
        return result;
    }
}
