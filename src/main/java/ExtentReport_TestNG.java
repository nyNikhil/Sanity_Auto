import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReport_TestNG {
	ExtentSparkReporter spark;
	ExtentReports extent;
	WebDriver driver= new FirefoxDriver();;
	@BeforeSuite
	public void setup() {
		extent = new ExtentReports();
		spark = new ExtentSparkReporter("Extent.html");
		extent.attachReporter(spark);
	}
	@BeforeTest
	public void setupTest() {
//		
        WebDriverManager.firefoxdriver().setup();

		//		WebDriver driver = new ChromeDriver();
	}
	@Test
	public void test() {
		ExtentTest test = extent.createTest("Google Search Test", "this is a test to validate google search functionality");
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
		test.pass("Navigated to google.com");
//		// log(Status, details)
//		test.log(Status.INFO,"This step shows Staus and Detials log");
//		
//		// info(details)
//		test.info("This steps shows info(details)");
//		
//		//log with snapshot
//        test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
//        
//     // test with snapshot
//        test.addScreenCaptureFromPath("screenshot.png");

		

	}
	 @AfterTest
		public void TeardownTest() throws InterruptedException {
//			driver.close();
	    	Thread.sleep(5000);
			driver.quit();
		}
	@AfterSuite
	public void teardown() {
		extent.flush();

	}

}
