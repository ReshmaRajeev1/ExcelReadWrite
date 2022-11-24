import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class ReadWriteBankDetails {
    public static void readWriteExcel() throws IOException {

        // TODO Auto-generated method stub

        File myFile = new File("D://temp/Bank.xlsx");
        String coulumnValue ="";
        int interest=0;
        int amount=0;
        int years=0;
        int simpleInterest =0;
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
                        }else if(coulumnValue.equalsIgnoreCase("Number of years")) {
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
        simpleInterest = amount+amount;
        System.out.println(simpleInterest);



        // Now, let's write some data into our XLSX file
        Map<String, Object[]> data = new HashMap<String, Object[]>();
        data.put("Simple Interset",new Object[]{"Simple Interset",simpleInterest} );

        // Set to Iterate and add rows into XLS file
        Set<String> newRows = data.keySet();

        // get the last row number to append new data
        int rownum = mySheet.getLastRowNum();

        for (String key : newRows) {
            rownum =rownum+1;
            // Creating a new Row in existing XLSX sheet
            Row row = mySheet.createRow(rownum);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Boolean) {
                    cell.setCellValue((Boolean) obj);
                } else if (obj instanceof Date) {
                    cell.setCellValue((RichTextString) obj);
                } else if (obj instanceof Integer) {
                    cell.setCellValue((Integer) obj);
                }
            }
        }

        // open an OutputStream to save written data into XLSX file
        FileOutputStream os = new FileOutputStream(myFile);
        myWorkBook.write(os);
        System.out.println("Writing on XLSX file Finished ...");

    }
}
