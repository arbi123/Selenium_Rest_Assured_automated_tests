import Utilities.ExcelUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class ExelTest {
    String filePath = "TestData.xlsx";
    String sheetName = "Sheet1";

    @Test(priority = 1,enabled = false)
    public void createExel(){
        try {
        ExcelUtils.createExcel(filePath, sheetName);
        System.out.println("Excel file created successfully: " + filePath);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    @Test(priority = 2)
    public void setData() throws IOException {
        ExcelUtils.setCellData(filePath, sheetName, 0, 0, "Name");
        ExcelUtils.setCellData(filePath, sheetName, 0, 1, "Age");
        ExcelUtils.setCellData(filePath, sheetName, 1, 0, "Alice");
        ExcelUtils.setCellData(filePath, sheetName, 1, 1, "30");
        ExcelUtils.setCellData(filePath, sheetName, 2, 0, "Bob");
        ExcelUtils.setCellData(filePath, sheetName, 2, 1, "25");
        System.out.println("Data written successfully to the Excel file.");

    }
      @Test(priority = 3)
    public void readingData(){
        try {
            System.out.println("Reading data from Excel:");
            System.out.println("Row 0, Column 0: " + ExcelUtils.getCellData(filePath, sheetName, 0, 0));
            System.out.println("Row 0, Column 1: " + ExcelUtils.getCellData(filePath, sheetName, 0, 1));
            System.out.println("Row 1, Column 0: " + ExcelUtils.getCellData(filePath, sheetName, 1, 0));
            System.out.println("Row 1, Column 1: " + ExcelUtils.getCellData(filePath, sheetName, 1, 1));
            System.out.println("Row 2, Column 0: " + ExcelUtils.getCellData(filePath, sheetName, 2, 0));
            System.out.println("Row 2, Column 1: " + ExcelUtils.getCellData(filePath, sheetName, 2, 1));
        }catch (IOException E){
            E.printStackTrace();
        }
}

@Test(priority = 4)
    public String[][] countingRows() throws IOException {
    System.out.println( ExcelUtils.getRowCount(filePath,sheetName));
    System.out.println( ExcelUtils.getCellCount(filePath,sheetName,1)); // 0 based if any one is offside the row it will give error
    String[][] sheetData = ExcelUtils.getSheetData(filePath,sheetName);

    /* Mbasi gjem se sa headers kemi i bejm search seciles row nqs 1 row esht me i madhe se headers fail*/
   return sheetData;
    //Printimi 2d i SheetTable
//    for (int i = 0; i < sheetData.length; i++) {//column
//        for (int j = 0; j < sheetData[i].length; j++) { //row
//            System.out.print(sheetData[i][j] + " ");
//        }
//        System.out.println();
//    }
}
@Test(priority = 5)
    public void deleteData() throws IOException { // Fshin te gjith te dhenat pervec headers nqs create excel esht active ben overwrite gjith filen dhe delete
        ExcelUtils.clearSheetData(filePath,sheetName,true);
}
}

