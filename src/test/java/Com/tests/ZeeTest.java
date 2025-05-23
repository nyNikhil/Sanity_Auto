package Com.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ZeeTest {
	
	    private WebDriver driver;
	    private final String baseUrl = "https://zeenews.india.com/";

	    @BeforeClass
	    public void setUp() {
	        WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	    }

	    @Test(priority = 1)
	    public void verifyHomePageTitle() {
	        driver.get(baseUrl);
	        String title = driver.getTitle();
	        System.out.println("Page Title: " + title);
	        Assert.assertTrue(title.contains("Zee News"), "Title does not contain 'Zee News'");
	    }

	    @Test(priority = 2)
	    public void verifyTopNavMenuLinks() {
	        driver.get(baseUrl);
	        List<WebElement> navLinks = driver.findElements(By.xpath("//div[@class='category-slider']"));
	        Assert.assertTrue(navLinks.size() > 0, "Top navigation menu links not found");

	        for (WebElement link : navLinks) {
	            System.out.println("Menu Link: " + link.getText());
	            Assert.assertFalse(link.getText().isEmpty(), "Found empty menu link text");
	        }
	    }

	    @Test(priority = 3)
	    public void verifyGAtrackingPresent() {
	        driver.get(baseUrl);
	        List<WebElement> scripts = driver.findElements(By.tagName("body"));

	        boolean found = false;
	        for (WebElement script : scripts) {
	            String content = script.getAttribute("innerHTML");
	            if (content != null && content.contains("G-K4Q9W8LH6V")) {
	                found = true;
	                break;
	            }
	        }
	        Assert.assertTrue(found, "GA tracking code G-K4Q9W8LH6V not found");
	    }

	    @AfterClass
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
}



