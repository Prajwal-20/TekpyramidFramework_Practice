
//public class writedata {
	
//	 for(int i=1;i<=rowCount;i++) {
//         //Enter the values read from Excel in firstname,lastname,mobile,email,address
//         firstName.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
//         lastName.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
//         email.sendKeys(sheet.getRow(i).getCell(2).getStringCellValue());
//         mobile.sendKeys(sheet.getRow(i).getCell(4).getStringCellValue());
//         address.sendKeys(sheet.getRow(i).getCell(5).getStringCellValue());
//         
//         //Click on the gender radio button using javascript
//         JavascriptExecutor js = (JavascriptExecutor) driver;
//         js.executeScript("arguments[0].click();", genderMale);
//         
//         //Click on submit button
//         submitBtn.click();
//         
//         //Verify the confirmation message
//         WebElement confirmationMessage = driver.findElement(By.xpath("//div[text()='Thanks for submitting the form']"));
//         
//         //create a new cell in the row at index 6
//         HSSFCell cell = sheet.getRow(i).createCell(6);
//         
//         //check if confirmation message is displayed
//         if (confirmationMessage.isDisplayed()) {
//             // if the message is displayed , write PASS in the excel sheet
//             cell.setCellValue("PASS");
//             
//         } else {
//             //if the message is not displayed , write FAIL in the excel sheet
//             cell.setCellValue("FAIL");
//         }
//         
//         // Write the data back in the Excel file
//         FileOutputStream outputStream = new FileOutputStream("E:\\TestData\\TestData.xls");
//         wb.write(outputStream);
//
//         //close the confirmation popup
//         WebElement closebtn = driver.findElement(By.id("closeLargeModal"));
//         closebtn.click();
//         
//         //wait for page to come back to registration page after close button is clicked
//         driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
//     }
//     
//     //Close the workbook
//     wb.close();
//     
//     //Quit the driver
//     driver.quit();
//     }
//
//}
