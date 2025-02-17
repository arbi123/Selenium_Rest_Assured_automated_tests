package Utilities;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {

    public static Workbook getWorkbook(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook;
        if(filePath.toLowerCase().endsWith("xlsx")) {
            workbook = new XSSFWorkbook(fis);
        } else if(filePath.toLowerCase().endsWith("xls")) {
            workbook = new HSSFWorkbook(fis);
        } else {
            fis.close();
            throw new IllegalArgumentException("The specified file is not an Excel file");
        }
        fis.close();
        return workbook;
    }


    public static String getCellData(String filePath, String sheetName, int rowNum, int cellNum) throws IOException {
        Workbook workbook = getWorkbook(filePath);
        Sheet sheet = workbook.getSheet(sheetName);
        if(sheet == null) {
            workbook.close();
            throw new IllegalArgumentException("Sheet " + sheetName + " does not exist");
        }
        Row row = sheet.getRow(rowNum);
        if(row == null) {
            workbook.close();
            return "";
        }
        Cell cell = row.getCell(cellNum);
        if(cell == null) {
            workbook.close();
            return "";
        }
        DataFormatter formatter = new DataFormatter();
        String cellValue = formatter.formatCellValue(cell);
        workbook.close();
        return cellValue;
    }


    public static void setCellData(String filePath, String sheetName, int rowNum, int cellNum, String data) throws IOException {
        File file = new File(filePath);
        Workbook workbook;
        Sheet sheet;

        if(file.exists()){
            FileInputStream fis = new FileInputStream(file);
            if(filePath.toLowerCase().endsWith("xlsx")){
                workbook = new XSSFWorkbook(fis);
            } else if(filePath.toLowerCase().endsWith("xls")){
                workbook = new HSSFWorkbook(fis);
            } else {
                fis.close();
                throw new IllegalArgumentException("The specified file is not an Excel file");
            }
            fis.close();
            sheet = workbook.getSheet(sheetName);
            if(sheet == null){
                sheet = workbook.createSheet(sheetName);
            }
        } else {
            if(filePath.toLowerCase().endsWith("xlsx")){
                workbook = new XSSFWorkbook();
            } else if(filePath.toLowerCase().endsWith("xls")){
                workbook = new HSSFWorkbook();
            } else {
                throw new IllegalArgumentException("The specified file is not an Excel file");
            }
            sheet = workbook.createSheet(sheetName);
        }

        Row row = sheet.getRow(rowNum);
        if(row == null){
            row = sheet.createRow(rowNum);
        }
        Cell cell = row.getCell(cellNum);
        if(cell == null){
            cell = row.createCell(cellNum);
        }
        cell.setCellValue(data);

        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        fos.close();
        workbook.close();
    }


    public static void createExcel(String filePath, String sheetName) throws IOException {
        Workbook workbook;
        if(filePath.toLowerCase().endsWith("xlsx")){
            workbook = new XSSFWorkbook();
        } else if(filePath.toLowerCase().endsWith("xls")){
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not an Excel file");
        }
        workbook.createSheet(sheetName);
        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();
        workbook.close();
    }

    public static int getRowCount(String filePath, String sheetName) throws IOException {
        Workbook workbook = getWorkbook(filePath);
        Sheet sheet = workbook.getSheet(sheetName);
        if(sheet == null){
            workbook.close();
            throw new IllegalArgumentException("Sheet " + sheetName + " does not exist");
        }
        int rowCount = sheet.getLastRowNum() + 1;
        workbook.close();
        return rowCount;
    }

    public static int getCellCount(String filePath, String sheetName, int rowNum) throws IOException {
        Workbook workbook = getWorkbook(filePath);
        Sheet sheet = workbook.getSheet(sheetName);
        if(sheet == null){
            workbook.close();
            throw new IllegalArgumentException("Sheet " + sheetName + " does not exist");
        }
        Row row = sheet.getRow(rowNum);
        if(row == null){
            workbook.close();
            return 0;
        }

        int cellCount = row.getLastCellNum();
        workbook.close();
        return cellCount;
    }


    public static String[][] getSheetData(String filePath, String sheetName) throws IOException {
        Workbook workbook = getWorkbook(filePath);
        Sheet sheet = workbook.getSheet(sheetName);
        if(sheet == null){
            workbook.close();
            throw new IllegalArgumentException("Sheet " + sheetName + " does not exist");
        }

        int rowCount = sheet.getLastRowNum() + 1;
        int cellCount = sheet.getRow(0).getLastCellNum();

        String[][] data = new String[rowCount][cellCount];
        DataFormatter formatter = new DataFormatter();

        for (int i = 0; i < rowCount; i++){
            Row row = sheet.getRow(i);
            for (int j = 0; j < cellCount; j++){
                if(row != null){
                    Cell cell = row.getCell(j);
                    data[i][j] = formatter.formatCellValue(cell);
                } else {
                    data[i][j] = "";
                }
            }
        }

        workbook.close();
        return data;
    }

    public static void clearSheetData(String filePath, String sheetName, boolean keepHeaders) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("File not found: " + filePath);
        }

        Workbook workbook;
        try (FileInputStream fis = new FileInputStream(file)) {
            workbook = new XSSFWorkbook(fis);
        }

        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet '" + sheetName + "' not found in workbook");
        }

        int startRow = keepHeaders ? 1 : 0;
        int lastRow = sheet.getLastRowNum();

        // Clear cell contents
        for (int i = startRow; i <= lastRow; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        cell.setBlank();
                    }
                }
            }
        }


        try (FileOutputStream fos = new FileOutputStream(file)) {
            workbook.write(fos);
        } finally {
            workbook.close();
        }
    }
}

