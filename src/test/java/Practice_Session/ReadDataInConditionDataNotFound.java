package Practice_Session;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataInConditionDataNotFound {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		String expectedTestId = "tc_92";
		String row1 = "";
		String row2 = "";
		String row3 = "";
		boolean flag = false;
		FileInputStream fis = new FileInputStream(".//src//test//resources//TestData1.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Sheet1");
		int rowCount = sh.getLastRowNum();
		try {
		for(int i=0;i<=rowCount;i++) {
			String row = sh.getRow(i).getCell(0).toString();
			if(row.equals(expectedTestId)) {
				flag=true;
			  row1 = sh.getRow(i).getCell(1).toString();
			  row2 = sh.getRow(i).getCell(2).toString();
			 row3 = sh.getRow(i).getCell(3).toString();
			}
		}
			}catch(Exception e) {
			}if(flag==true) {
				System.out.println(row1 + " " + row2 + " " + row3);
			} else {
			System.out.println("tc_92 is not present");
		}
			wb.close();
		
		}
		
	
}
