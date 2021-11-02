package functionality;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Product {

	WebDriver driver;
	String prPath;

	@BeforeClass
	public void setUp() throws EncryptedDocumentException, InvalidFormatException, IOException {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
	}

	@Test(dataProvider = "loginData")
	public void logIn(String emailData, String password) throws InterruptedException {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		WebElement email = driver.findElement(By.name("email"));
		email.sendKeys(emailData);

		WebElement passwd = driver.findElement(By.name("passwd"));
		passwd.sendKeys(password);

		driver.findElement(By.name("SubmitLogin")).click();

		driver.navigate().to("http://automationpractice.com/index.php?id_product=4&controller=product");
		Thread.sleep(9000);
		driver.findElement(By.name("Pink")).click();
		Thread.sleep(9000);
		driver.findElement(By.name("Beige")).click();
		Thread.sleep(9000);

		driver.findElement(By.id("wishlist_button")).click();
		Thread.sleep(9000);
		driver.findElement(By.xpath("//*[@id=\"product\"]/div[2]/div/div/a")).click();
		Thread.sleep(4000);
		driver.navigate()
				.to("http://automationpractice.com/index.php?fc=module&module=blockwishlist&controller=mywishlist");

	}

	@DataProvider(name = "loginData")
	public String[][] getData() {
		String loginData[][] = { { "realcmpunk@gmail.com", "!@#$%" } };

		return loginData;
	}
}
