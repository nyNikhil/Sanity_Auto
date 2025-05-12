package zee;


	import org.apache.poi.ss.usermodel.*;


	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
	import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

	public class WriteClass {
		public static void writeResultsToExcel(String SheetName, String[] headers, List<String[]> data, boolean highlight) throws IOException {
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("SEO Validation Results");
			Sheet sheet1 = workbook.createSheet("Broken Link");

			// Create header row
			Row headerRow = sheet.createRow(0);
			for (int i = 0; i < headers.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(headers[i]);

				// Add bold style for headers
				CellStyle headerStyle = workbook.createCellStyle();
				Font font = workbook.createFont();
				font.setBold(true);
				headerStyle.setFont(font);
				headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
				headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				cell.setCellStyle(headerStyle);
			}
			
			// Define styles for Pass/Fail
			CellStyle passStyle = workbook.createCellStyle();
			passStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
			passStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			CellStyle failStyle = workbook.createCellStyle();
			failStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
			failStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			// Write data rows
			int rowNum = 1;
			for (String[] rowData : data) {
				Row row = sheet.createRow(rowNum++);
				for (int i = 0; i < rowData.length; i++) {
					Cell cell = row.createCell(i);
					cell.setCellValue(rowData[i]);

					// Apply highlighting for Pass/Fail in the last column
					if (highlight && i == rowData.length - 1) { // Assuming last column contains Pass/Fail
						String status = rowData[i].trim().toLowerCase();
						if ("pass".equals(status)) {
							cell.setCellStyle(passStyle);
						} else if ("fail".equals(status)) {
							cell.setCellStyle(failStyle);
						}
					}
				}
			}

			// Autosize columns (limit width for large datasets)
			for (int i = 0; i < headers.length; i++) {
				sheet.autoSizeColumn(i);
			}
			String basePath = System.getProperty("user.dir");
	        String folderPath = basePath + File.separator + "excel";
	        File directory = new File(folderPath);
	        if (!directory.exists()) {
	            directory.mkdirs();
	        }
	        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
	        String filePath = folderPath + File.separator + "Output_" + timestamp + ".xlsx";

			// File path and name
	     // Write file
	        try (FileOutputStream fos = new FileOutputStream(filePath)) {
	            workbook.write(fos);
	        }
	        workbook.close();

	        System.out.println("Results written successfully to: " + filePath);
	    }
	}
//			String filePath = "C:\\Users\\Saurav.Tiwari\\eclipse-workspace3\\SeleniumFrame\\excel\\Output.xlsx";
//
//			// Write to file
//			try (FileOutputStream fos = new FileOutputStream(filePath)) {
//				workbook.write(fos);
//			}
//			workbook.close();
//
//			System.out.println("Results written successfully to: " + filePath);
//		}
//	}





