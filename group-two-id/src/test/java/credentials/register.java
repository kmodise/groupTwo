package credentials;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ExcelUtils;

public class register {
	WebDriver driver;
	FileInputStream fis;
	Workbook wb;
	Sheet sheet0;
	Row row0;
	Cell cell0;
	File f;
	ExcelUtils excel;
	ExcelUtils excelEmails;
	int i;
	String prPath;

	@BeforeClass
	public void setUp() throws EncryptedDocumentException, InvalidFormatException, IOException {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
	}

	@BeforeTest
	public void sheetSetUp() throws IOException, EncryptedDocumentException, InvalidFormatException {

		prPath = System.getProperty("user.dir");
		excel = new ExcelUtils(prPath + "\\documents\\test.xls", "sheet1 -1");
		excelEmails = new ExcelUtils(prPath + "\\documents\\emails.xls", "sheet1 -1");

	}

	@Test(enabled = false, dataProvider = "emailCheck")
	public void emailCheck(String emails, String status) {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

		WebElement email = driver.findElement(By.name("email_create"));
		String emailIs = emails;
		email.sendKeys(emailIs);
		driver.findElement(By.name("SubmitCreate")).click();

		String expected_title = "Login - My Store";
		String actual_title = driver.getTitle();

		if (status.equals("Valid")) {
			if (expected_title.equals(actual_title)) {
				driver.findElement(By.className("login")).click();
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		} else if (status.equals("invalid")) {
			if (expected_title.equals(actual_title)) {
				driver.findElement(By.className("login")).click();
				Assert.assertTrue(false);
			} else {
				Assert.assertTrue(true);
			}
		}

	}
	

	@Test(dataProvider = "loginData", enabled =false)
	public void login(String email, String passWord) {
		System.out.println(email + passWord);
	}

	@DataProvider(name = "loginData")
	public String[][] getData() {
		String loginData[][] = { { "tes1@one.com", "Valid" }, { "tes2@.com", "Invalid" }, { "", "Invalid" },
				{ "tes4one.com", "Invalid" } };

		return loginData;
	}

	@DataProvider(name = "emailCheck")
	public String[][] getDataEmail() {
		int totalRows = excelEmails.getRowCount();
		int totalCells = excelEmails.getCellNumber(0);

		String emails[][] = new String[totalRows][totalCells];

		for (int i = 1; i <= totalRows; i++) {
			for (int j = 0; j < totalCells; j++) {
				emails[i - 1][j] = excelEmails.getCellDataString(i, j);
			}
		}
		return emails;
	}
}
