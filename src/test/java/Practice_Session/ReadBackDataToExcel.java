package Practice_Session;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadBackDataToExcel {

	public static void main(String[] args) throws Throwable, Exception {
		FileInputStream fis = new FileInputStream(".//src//test//resources//TestData1.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Sheet1");
		Row row = sh.getRow(1);
		Cell cel = row.createCell(4);
		cel.setCellType(CellType.STRING);
		cel.setCellValue("PASS");
		String data = cel.getStringCellValue();
		System.out.println(data);
		
		FileOutputStream fos = new FileOutputStream(".//src//test//resources//TestData1.xlsx");
		wb.write(fos);
		wb.close();
		System.out.println("Executed");
	}

}
