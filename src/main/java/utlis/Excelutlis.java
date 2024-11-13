package utlis;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excelutlis {
	static String ProjectPath ;
	static XSSFWorkbook workbook ;
	static XSSFSheet sheet ;
	
	
	public Excelutlis(String exelpath, String sheetname) {
		try {
//			ProjectPath = System.getProperty("user.dir");
			workbook = new XSSFWorkbook(exelpath);
			sheet = workbook.getSheet(sheetname);
	}catch(Exception e) {
		e.printStackTrace();
	}
	}
	
	public static void main(String[] args) {
		getRowCount();
		getCellData(0,0);
		getCellDataNumber(1,1);
	}

	public static void getRowCount() {
		try {
//			ProjectPath = System.getProperty("user.dir");
//			workbook = new XSSFWorkbook(ProjectPath+"/excel/Data.xlsx");
			//		XSSFWorkbook workbook = new XSSFWorkbook("C:\\Users\\Saurav.Tiwari\\eclipse-workspace3\\SeleniumFrame\\excel\\Data.xlsx");
//			sheet = workbook.getSheet("Sheet1");
			int rowCount = sheet.getPhysicalNumberOfRows();
			System.out.println("No. of rows : " +rowCount);
		}catch(Exception exp){
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
	}
	public static void getCellData(int rowNum, int colNum) {
		try {
//			ProjectPath = System.getProperty("user.dir");
//			workbook = new XSSFWorkbook(ProjectPath+"/excel/Data.xlsx");
//			sheet = workbook.getSheet("Sheet1");
			String Celldata = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
			System.out.println(Celldata);
		}catch(Exception exp){
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}


	}
	public static void getCellDataNumber(int rowNum, int colNum) {
		try {
			ProjectPath = System.getProperty("user.dir");
			workbook = new XSSFWorkbook(ProjectPath+"/excel/Data.xlsx");
			sheet = workbook.getSheet("Sheet1");
			double Celldata = sheet.getRow(rowNum).getCell(colNum).getNumericCellValue();
			System.out.println(Celldata);
		}catch(Exception exp){
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}


	}


}
