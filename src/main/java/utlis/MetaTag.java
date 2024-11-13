package utlis;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MetaTag {
	private static WebDriver driver= null;
	public static void main(String[] args) throws InterruptedException {
		 driver = new FirefoxDriver();
		 driver.get("https://www.wionews.com/");
//		 WebElement ele = driver.findElement
		 System.out.println(driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content"));
		 System.out.println(driver.findElement(By.xpath("//meta[@name='keywords']")).getAttribute("content"));
		 
		 

}
}