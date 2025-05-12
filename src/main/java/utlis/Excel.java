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
		getCellData();
	}
	public static List<String> getCellData() {
		List<String> urls = new ArrayList<>(); {
			try {
				projectPath = System.getProperty("user.dir");
				workbook = new XSSFWorkbook(projectPath + "/excel/Website.xlsx");
				sheet = workbook.getSheet("Sheet1");

				// Loop through rows from 0 to 3 and get data from cell 0 in each row
				for (int rowIndex = 1; rowIndex <= 2; rowIndex++) {
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




