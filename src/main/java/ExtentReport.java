import org.openqa.selenium.By;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
	public static void main(String[]args) throws InterruptedException  {
		WebDriver driver;
		 ExtentReports extent = new ExtentReports();
	        ExtentSparkReporter spark = new ExtentSparkReporter("Extentreport.html");
	        extent.attachReporter(spark);
	        ExtentTest test = extent.createTest("Google Search Test", "this is a test to validate google search functionality");
	        
	        driver = new FirefoxDriver();
			//		WebDriver driver = new ChromeDriver();
	        test.log(Status.INFO, "Starting Test case ");
			driver.get("https://www.google.com/");
			driver.manage().window().maximize();
			test.pass("Navigated to google.com");
			driver.findElement(By.name("q")).sendKeys("Automation");
			test.pass("Entered text in searchbox");
			driver.findElement(By.name("q")).sendKeys(Keys.RETURN);
			test.pass("PressedKeyboard enter key");		
			Thread.sleep(3000);
			driver.close();
			test.pass("Close the browser");
			test.info("Test Completed");
			extent.flush();
		
	}

}
