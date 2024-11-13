import org.openqa.selenium.By;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Test_NGDemo {
//	WebDriver driver;
	WebDriver driver = new FirefoxDriver();
	@BeforeTest
	public void setupTest() {
//		
        WebDriverManager.firefoxdriver().setup();

		//		WebDriver driver = new ChromeDriver();
	}
	@Test
	public  void GoogleSearch() {
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
		//		WebElement textbox = driver.findElement(By.id("APjFqb"));
		//		textbox.sendKeys("automation");
		driver.findElement(By.id("APjFqb")).sendKeys("Automation");
		driver.findElement(By.name("q")).sendKeys(Keys.RETURN);
		

		
} 
    @AfterTest
	public void TeardownTest() throws InterruptedException {
//		driver.close();
    	Thread.sleep(5000);
		driver.quit();
	}
}


