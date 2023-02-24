package com.misa.common;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class XLSReader {
    private static XSSFWorkbook ExcelBook;
    private static XSSFSheet ExcelSheet;
    private static XSSFCell Cell;
    private static XSSFRow Row;

    public static String getCellData(int rowNum, int colNum) {
        Cell = ExcelSheet.getRow(rowNum).getCell(colNum);
        if(Cell==null){
            return "";
        }
        switch (Cell.getCellType()) {
            case NUMERIC:
                int numData = (int) Cell.getNumericCellValue();
                return String.valueOf(numData);
            case STRING:
                String stringData = Cell.getStringCellValue();
                return stringData;
            case BLANK:
                return "";
            case BOOLEAN:
                Boolean booleanData =  Cell.getBooleanCellValue();
                return booleanData.toString();
            default:
                return "error";
        }
    }
    public static String [][] getTableArray(String filePath, String sheetName) throws IOException {
        String [][] tableArray = null;
        FileInputStream ExcelFile = new FileInputStream(filePath);
        ExcelBook = new XSSFWorkbook(ExcelFile);
        ExcelSheet = ExcelBook.getSheet(sheetName);
        int startRow = 1;
        int startCol = 1;
        int ci, cj;
        int totalRows = ExcelSheet.getLastRowNum();//tra ve gia tri cua hang trong excel -1
        int totalCols = ExcelSheet.getRow(0).getLastCellNum()-1;
        tableArray = new String[totalRows][totalCols];
        for (int i = startRow; i<=totalRows; i++, ci++){
            ci=i-1;
            for (int j = startCol; j<=totalCols; j++, cj++){
                cj=j-1;
                tableArray[ci][cj]= getCellData(i,j);
            }
        }
        return tableArray;
    }
}
