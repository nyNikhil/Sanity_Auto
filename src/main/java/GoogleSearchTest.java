import org.openqa.selenium.By;
import Pages.GoogleSearchPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GoogleSearchTest {
	private static WebDriver driver= null;
	public static void main(String[] args) throws InterruptedException {
		 driver = new FirefoxDriver();
		//		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
		//		WebElement textbox = driver.findElement(By.id("APjFqb"));
		//		textbox.sendKeys("automation");
//		driver.findElement(By.id("APjFqb")).sendKeys("Automation");
		GoogleSearchPage.textbox_search(driver).sendKeys("Automation");
//		driver.findElement(By.name("q")).sendKeys(Keys.RETURN);
		GoogleSearchPage.Button_Search(driver).sendKeys(Keys.RETURN);
		Thread.sleep(5000);
		driver.close();


	}

}
