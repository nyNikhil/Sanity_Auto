package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Instagram_LoginPage {
	WebDriver driver = null;
	By Username = By.name("username");
	By Password = By.name("password");
	By Login = By.xpath("//div[text()='Log In']");
	public Instagram_LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	public void Setusername() {
		driver.findElement(Username).click();
		driver.findElement(Username).clear();
		driver.findElement(Username).sendKeys("thesauravtiwari");
	}
	public void Setpassword() {
		driver.findElement(Password).click();
		driver.findElement(Password).clear();
		driver.findElement(Password).sendKeys("@utom@tion");
	}
	public void Login() {
		driver.findElement(Login).click();
	}
	

	

}
