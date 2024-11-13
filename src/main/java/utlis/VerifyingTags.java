package utlis;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyingTags {
	WebDriver driver = new FirefoxDriver();
	@BeforeTest
	public void setupTest() {
		//		
		WebDriverManager.firefoxdriver().setup();

		//		WebDriver driver = new ChromeDriver();
	}
	@Test
	public void wion(){
		List<String> urls = Excel.getCellData();
		//		driver.get("https://www.wionews.com/");
		for (String url : urls) {
			System.out.println("Testing URL: " + url);
			driver.get(url);
			List<WebElement> metaTags = driver.findElements(By.tagName("meta"));
			List<WebElement> linkTags = driver.findElements(By.tagName("link"));
			//		 List<WebElement> metaTags = driver.findElements(By.tagName("meta"));
			//		 List<WebElement> linkTags = driver.findElements(By.tagName("link"));
			for (WebElement metaTag : metaTags) {
				// Fetch 'name' and 'content' attributes
				String name = metaTag.getAttribute("name");
				String property = metaTag.getAttribute("property");
				String content = metaTag.getAttribute("content");
				if (name != null && (name.equals("description") || name.equals("keywords") || name.equals("viewport"))) {
					System.out.println("Name: " + name + " | Content: " + content);
				} else if (property != null && property.startsWith("og:")) { // Open Graph tags
					System.out.println("Property: " + property + " | Content: " + content);
				} 
			}
			for (WebElement linkTag : linkTags) {
				String rel = linkTag.getAttribute("rel");
				String href = linkTag.getAttribute("href");

				// Print link tags with rel="canonical" or other SEO-related attributes
				if (rel != null && (rel.equals("canonical") || rel.equals("alternate") || rel.equals("stylesheet"))) {
					System.out.println("Rel: " + rel + " | Href: " + href);
				}
			}
		}
	}
	@AfterTest
	public void TeardownTest() throws InterruptedException {
		//		driver.close();
		Thread.sleep(5000);
		driver.quit();
	}
}



