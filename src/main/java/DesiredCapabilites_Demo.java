import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DesiredCapabilites_Demo {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver;
        driver = new FirefoxDriver();
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("what is selenium");
        driver.findElement(By.name("q")).sendKeys(Keys.RETURN);
        Thread.sleep(5000);
        driver.close();		
      
	}

}
