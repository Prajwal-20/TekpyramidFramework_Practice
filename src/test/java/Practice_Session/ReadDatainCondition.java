package Practice_Session;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDatainCondition {

	public static void main(String[] args) throws Throwable, Exception {
		String expectedTestId = "tc_02";
		FileInputStream fis = new FileInputStream(".//src//test//resources//TestData1.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Sheet1");
		int rowCount = sh.getLastRowNum();
		for(int i=0;i<=rowCount;i++) {
			try {
			String data = sh.getRow(i).getCell(0).toString();
			//System.out.println(data);
			if(data.equals(expectedTestId)) {
				String row1 = sh.getRow(i).getCell(1).toString();
				String row2 = sh.getRow(i).getCell(2).toString();
				String row3 = sh.getRow(i).getCell(3).toString();
				System.out.println(data + " " + row1 + " " + row2 + " " + row3);
			}
			} catch(Exception e) {			
			}
		}
	}

}
