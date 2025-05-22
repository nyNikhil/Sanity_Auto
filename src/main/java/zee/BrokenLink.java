package zee;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.netty.handler.timeout.TimeoutException;

public class BrokenLink {
	WebDriver driver;
	List<String[]> data = new ArrayList<>();
	Set<String> checkedUrls = new HashSet<>(); // To track processed URLs

	@BeforeTest
	public void setupTest() {
		WebDriverManager.firefoxdriver().setup();
		FirefoxOptions options = new FirefoxOptions();

		driver = new FirefoxDriver(options);
		driver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(60)); // Set page load timeout
		data.add(new String[]{"Website URL", "Link", "Status Code", "Result"});
	}

	@Test
	public void checkBrokenLinksWionNews() {
		checkBrokenLinksForWebsite("https://www.wionews.com/science");
	}

	private void checkBrokenLinksForWebsite(String websiteUrl) {
		driver.get(websiteUrl);
		WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(30));

		try {
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("a"))); // Wait for links to load
		} catch (TimeoutException e) {
			System.out.println("Failed to load links: " + e.getMessage());
			return;
		}

		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Checking links for: " + websiteUrl);
		System.out.println("Total links found: " + links.size());

		for (WebElement link : links) {
			String url = link.getAttribute("href");
			if (url == null || url.isEmpty() || checkedUrls.contains(url)) {
				System.out.println("URL is either not configured for anchor tag, it is empty, or already checked.");
				continue;
			}
			checkedUrls.add(url); // Mark this URL as checked

			checkLinkStatus(websiteUrl, url); // Check status of each link
		}
	}

	private void checkLinkStatus(String websiteUrl, String url) {
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("HEAD");
			connection.connect();
			int responseCode = connection.getResponseCode();

			boolean isBroken = responseCode >= 400;
			String result = isBroken ? "Fail" : "Pass";

			if (responseCode >= 500) {
				System.out.println(url + " is **broken** with response code: " + responseCode + " (Internal Server Error or similar)");
			} else if (responseCode >= 400) {
				System.out.println(url + " is **broken** with response code: " + responseCode + " (Client error)");
			} else {
				System.out.println(url + " is **valid** with response code: " + responseCode);
			}

			data.add(new String[]{websiteUrl, url, String.valueOf(responseCode), result});
		} catch (Exception e) {
			System.out.println("Error checking URL: " + url + " - " + e.getMessage());
		}
	}

	@AfterClass
	public void tearDown() throws IOException {
		if (driver != null) {
			driver.quit();
		}

		WriteClass.writeResultsToExcel(
				"Broken Link",
				new String[]{"Website URL", "Link", "Status Code", "Result"},
				data,
				true
				);

		System.out.println("\nCollected URLs:");
		for (String[] entry : data) {
			System.out.println("Parent: " + entry[0] + ", Link: " + entry[1]);
		}
	}
}

