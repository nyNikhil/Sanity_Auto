package utlis;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Seo {
	public static WebDriver driver= null;
	public static void main(String[] args)  {
		 driver = new FirefoxDriver();
		 driver.get("https://www.wionews.com/");
		 List<WebElement> metaTags = driver.findElements(By.tagName("meta"));
		 for (WebElement metaTag : metaTags) {
             // Fetch 'name' and 'content' attributes
             String name = metaTag.getAttribute("name");
             String property = metaTag.getAttribute("property");
             String content = metaTag.getAttribute("content");
             if (name != null && (name.equals("description") || name.equals("keywords") || name.equals("robots"))) {
                 System.out.println("Name: " + name + " | Content: " + content);
             } else if (property != null && property.startsWith("og:")) { // Open Graph tags
                 System.out.println("Property: " + property + " | Content: " + content);
             }
		 }
	}
}

                 