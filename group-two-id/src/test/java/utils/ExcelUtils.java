package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	static String projectPath;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	static FileInputStream fis;

	public ExcelUtils(String excelPath, String sheetName) {
		try {
//			projectPath = System.getProperty("user.dir");
			fis = new FileInputStream(new File(excelPath));
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
		} catch (Exception xp) {
			System.out.println(xp.getMessage());
			System.out.println(xp.getCause());
			xp.printStackTrace();
		}
	}

	public static int getRowCount() {
		try {

			int rowCount = sheet.getPhysicalNumberOfRows();
			return (rowCount);

		} catch (Exception xp) {
			System.out.println(xp.getMessage());
			System.out.println(xp.getCause());
			xp.printStackTrace();
		}
		return (Integer) null;

	}

	public static String getCellDataString(int rowNumber, int colNumber) {
		try {

			String cellData = sheet.getRow(rowNumber).getCell(colNumber).getStringCellValue();
			return cellData;
		} catch (Exception xp) {
			System.out.println(xp.getMessage());
			System.out.println(xp.getCause());
			xp.printStackTrace();
		}
		return "null :(";

	}

	public static int getCellNumber(int rowNumber) {
		int cellNumber = sheet.getRow(rowNumber).getLastCellNum();
		return cellNumber;
	}

	public static double getCellDataNumber(int rowNumber, int colNumber) {
		try {

			double cellData = sheet.getRow(rowNumber).getCell(colNumber).getNumericCellValue();
			return (cellData);
		} catch (Exception xp) {
			System.out.println(xp.getMessage());
			System.out.println(xp.getCause());
			xp.printStackTrace();
		}
		return (Double) null;
	}

}
