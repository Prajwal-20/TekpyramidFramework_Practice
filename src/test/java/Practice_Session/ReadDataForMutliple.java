package Practice_Session;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataForMutliple {

	public static void main(String[] args) throws Exception, Throwable {
		FileInputStream fis = new FileInputStream(".//src//test//resources//TestData1.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Sheet2");
	//	Row row = sh.getRow(1);
//		String cel = row.getCell(0).getStringCellValue();
//		String cel1 = row.getCell(1).getStringCellValue();
//		System.out.println(cel + " " + cel1);
		
		//for all test data
		int rowCount = sh.getLastRowNum();
		for(int i = 0;i<=rowCount;i++) {
			Row row = sh.getRow(i);
			String cel = row.getCell(0).getStringCellValue();
			String cel1 = row.getCell(1).getStringCellValue();
			System.out.println(cel + " " + cel1);
		}
	
	}

}
