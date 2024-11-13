package utlis;

public class ExcelUtlisDemo {
	public static void main(String[] args) {
		String ProjectPath = System.getProperty("user.dir");
		Excelutlis excel = new Excelutlis(ProjectPath+"/excel/Data.xlsx", "Sheet1");
		excel.getRowCount();
		excel.getCellDataNumber(1,1);
		excel.getCellData(0,0);
	}

}
