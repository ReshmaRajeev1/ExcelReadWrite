package bankdetails;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class GetBankDetails {
 public  static   int interest=0;
   public  static int amount=0;
   public  static int years=0;

    public static void readBankDetails() throws IOException {

        // TODO Auto-generated method stub

        File myFile = new File("D://temp/Bank.xlsx");
        String coulumnValue ="";

        FileInputStream fis = new FileInputStream(myFile);

        // Finds the workbook instance for XLSX file
        XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);

        // Return first sheet from the XLSX workbook
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);

        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = mySheet.iterator();

        // Traversing over each row of XLSX file
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            // For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {

                Cell cell = cellIterator.next();

                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        System.out.print(cell.getStringCellValue() + "\t");
                        coulumnValue=  cell.getStringCellValue();

                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getNumericCellValue() + "\t");
                        if(coulumnValue.equalsIgnoreCase("Interest")){
                            interest = (int) cell.getNumericCellValue();
                        }else if(coulumnValue.equalsIgnoreCase("Principal Amount")){
                            amount = (int) cell.getNumericCellValue();
                        }else if(coulumnValue.equalsIgnoreCase("Years")) {
                            years = (int) cell.getNumericCellValue();
                        }
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        System.out.print(cell.getBooleanCellValue() + "\t");
                        break;


                }
            }
            System.out.println("");
        }


    }
}
