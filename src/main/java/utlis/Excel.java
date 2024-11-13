package utlis;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.reporters.jq.Main;

public class Excel {
	static String projectPath;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	public static void main(String[] args) {
		System.out.println("Hello World");
		//		rowCount();
		getCellData();
	}
	//	public static void rowCount(){
	//		try {
	//			//		File excelFile = new File(System.getProperty("user.dir")+"\\excel\\Website.xlsx");
	//			//		FileInputStream Fis = new FileInputStream(excelFile);
	//			projectPath = System.getProperty("user.dir");
	//			workbook = new XSSFWorkbook(projectPath+"/excel/Website.xlsx");
	//			sheet = workbook.getSheet("Sheet1");
	//			int rowCount = sheet.getPhysicalNumberOfRows();
	//			System.out.println("No of rows : "+rowCount);
	//		} 
	//		catch(Exception exp) {
	//			System.out.println(exp.getMessage());
	//			System.out.println(exp.getCause());
	//			exp.printStackTrace();
	//		}
	//		//		int rowCount = sheet.getLastRowNum();
	//		//		int columnCount = sheet.getRow(0).getLastCellNum();
	//		//		Object[][] data = new Object[rowCount][columnCount];
	//		//		for(int r=0;r<rowCount;r++) { }
	//
	//	}
	//
	////
	//	public static void getCellData() {
	//		try {
	//			projectPath = System.getProperty("user.dir");
	//			workbook = new XSSFWorkbook(projectPath+"/excel/Website.xlsx");
	//			sheet = workbook.getSheet("Sheet1");
	//			String cellData = sheet.getRow(0).getCell(0).getStringCellValue();
	//			System.out.println(cellData);
	//		}
	//		catch(Exception exp) {
	//			System.out.println(exp.getMessage());
	//			System.out.println(exp.getCause());
	//			exp.printStackTrace();
	//		}
	//	}
	public static List<String> getCellData() {
		List<String> urls = new ArrayList<>(); {
			try {
				projectPath = System.getProperty("user.dir");
				workbook = new XSSFWorkbook(projectPath + "/excel/Website.xlsx");
				sheet = workbook.getSheet("Sheet1");

				// Loop through rows from 0 to 3 and get data from cell 0 in each row
				for (int rowIndex = 1; rowIndex <= 3; rowIndex++) {
					if (sheet.getRow(rowIndex) != null && sheet.getRow(rowIndex).getCell(0) != null) {
						String cellData = sheet.getRow(rowIndex).getCell(0).getStringCellValue();
						System.out.println("Row " + rowIndex + ", Cell 0: " + cellData);
						urls.add(cellData);  
					} else {
						System.out.println("Row " + rowIndex + ", Cell 0 is empty or does not exist.");
					}
				}

			} catch (Exception exp) {
				System.out.println(exp.getMessage());
				System.out.println(exp.getCause());
				exp.printStackTrace();
			} finally {
				try {
					if (workbook != null) {
						workbook.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}return urls;


	}
}




