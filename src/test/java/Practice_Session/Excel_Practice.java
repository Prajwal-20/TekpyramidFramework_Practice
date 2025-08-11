package Practice_Session;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel_Practice {

	public static void main(String[] args) throws Throwable, Exception {
		
		FileInputStream fis = new FileInputStream(".//src//test//resources//TestData1.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Sheet1");
		int rowCount1 = sh.getLastRowNum();
		System.out.println(rowCount1);
		Row row = sh.getRow(1);
		int rowCount = row.getRowNum();
		System.out.println(rowCount);
		Cell cel = row.getCell(1);
		String data = cel.getStringCellValue();
		System.out.println(data);
	}

}
