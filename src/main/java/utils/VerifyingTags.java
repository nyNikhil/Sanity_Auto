package utils;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.log.LogEntry;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.WriteClass;

public class VerifyingTags {
	WebDriver driver;
	List<String[]> results = new ArrayList<>(); // To store results for Excel

	@BeforeTest
	public void setupTest() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	}

//	@Test
//	public void testWIONews() {
//		String url = "https://www.wionews.com/";
//		System.out.println("Testing URL: " + url);
//
//		Map<String, String> expectedValues = new HashMap<>();
//		expectedValues.put("viewport", "width=device-width, initial-scale=1.0");
//		expectedValues.put("description",
//				"WION (World Is One News) brings latest & breaking news from South Asia, India, Pakistan, Bangladesh, Nepal, Sri Lanka and rest of the World in politics, business, economy, sports, lifestyle, science & technology with opinions & analysis.");
//		expectedValues.put("keywords",
//				"latest news, breaking news, world news, current headlines, world, international, south asia, business and economy news, sports update, health articles, science and tech, opinions, photos and videos, WION, WIONews");
//		expectedValues.put("og:type", "website");
//		expectedValues.put("og:site_name", "WION");
//		expectedValues.put("og:title",
//				"WION: Breaking News, Latest News, World, South Asia, India, Pakistan, Bangladesh News & Analysis");
//		expectedValues.put("og:description",
//				"WION (World Is One News) brings latest & breaking news from South Asia, India, Pakistan, Bangladesh, Nepal, Sri Lanka and rest of the World in politics, business, economy, sports, lifestyle, science & technology with opinions & analysis.");
//		expectedValues.put("og:url", "https://www.wionews.com");
//		expectedValues.put("og:image", "https://cdn.wionews.com/images/WION-logo-1200.png");
//		expectedValues.put("canonical", "https://www.wionews.com/");
//
//		testSEOAttributes(url, expectedValues);
//	}
	
    @Test
    public void testZeeNews() {
        String url = "https://zeenews.india.com/";
        System.out.println("Testing URL: " + url);

        Map<String, String> expectedValues = new HashMap<>();
        expectedValues.put("viewport", "width=device-width, initial-scale=1");
        expectedValues.put("robots", "max-image-preview:large");
        expectedValues.put("theme-color", "#345678");
        expectedValues.put("description", "Zee News brings latest news from India and World on breaking news, today news headlines, politics, business, technology, bollywood, entertainment, sports and others. Find exclusive news stories on Indian politics, current affairs, cricket matches, festivals and events.");
        expectedValues.put("keywords", "news, latest news, today news, breaking news, news headlines, bollywood news, India news, top news, political news, business news, technology news, sports news");
        expectedValues.put("og:type", "website");
        expectedValues.put("og:site_name", "Zee News");
        expectedValues.put("og:title", "Zee News: Latest News, Live Breaking News, Today News, India Political News Updates");
        expectedValues.put("og:description", "Zee News brings latest news from India and World on breaking news, today news headlines, politics, business, technology, bollywood, entertainment, sports and others. Find exclusive news stories on Indian politics, current affairs, cricket matches, festivals and events.");
        expectedValues.put("og:url", "https://zeenews.india.com");
        expectedValues.put("og:image", "https://english.cdn.zeenews.com/images/logo/placeholder_image.jpg");
        expectedValues.put("canonical", "https://zeenews.india.com/");

        testSEOAttributes(url, expectedValues);
        validateGATrackingID(url, "G-K4Q9W8LH6V");
    }

    private void testSEOAttributes(String url, Map<String, String> expectedValues) {
        try {
            driver.get(url);
            List<WebElement> metaTags = driver.findElements(By.tagName("meta"));
            for (WebElement metaTag : metaTags) {
                String name = metaTag.getAttribute("name");
                String property = metaTag.getAttribute("property");
                String content = metaTag.getAttribute("content");

                String key = (name != null && !name.isEmpty()) ? name : property;
                if (key != null && expectedValues.containsKey(key)) {
                    String expected = expectedValues.get(key);
                    String status = expected.equals(content) ? "PASS" : "FAIL";
                    //hgfhdcfxf
                    results.add(new String[]{url, key, expected, content, status});
                } else if (key != null) {
                    results.add(new String[]{url, key, "No Expected Content", content, "NOT TESTED"});
                }
            }

            // Check for canonical link
            List<WebElement> linkTags = driver.findElements(By.tagName("link"));
            for (WebElement linkTag : linkTags) {
                String rel = linkTag.getAttribute("rel");
                String href = linkTag.getAttribute("href");

                if ("canonical".equalsIgnoreCase(rel)) {
                    String expected = expectedValues.get("canonical");
                    if (expected != null) { // Ensure expected value exists
                        String status = expected.equals(href) ? "PASS" : "FAIL";
                        results.add(new String[]{url, "Canonical Link", expected, href, status});
                    }
                }
            }
        } catch (Exception e) {
            results.add(new String[]{url, "SEO Test", "N/A", "N/A", "Error: " + e.getMessage()});
        }
    }
       
    private void validateGATrackingID(String url, String expectedGATID) {
        try {
            driver.get(url);

            // Wait for the page to fully load
            new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

            // Check in page source
            String pageSource = driver.getPageSource();
            if (pageSource.contains(expectedGATID)) {
                results.add(new String[]{url, "GA Tracking ID", expectedGATID, "Found in page source", "PASS"});
            } else {
                results.add(new String[]{url, "GA Tracking ID", expectedGATID, "Not found in page source", "FAIL"});
            }

            // Check network requests only if driver is ChromeDriver
            boolean gaRequestFound = false;
            if (driver instanceof ChromeDriver) {
                LogEntries logs = driver.manage().logs().get(LogType.PERFORMANCE);
                
                for (org.openqa.selenium.logging.LogEntry entry : logs) {
                    String message = entry.getMessage();
                    System.out.println("Log Entry: " + message);  // Debugging: Print logs

                    if (message.contains("google-analytics.com/g/collect") && message.contains(expectedGATID)) {
                        gaRequestFound = true;
                        break;
                    }
                }
            }

            if (gaRequestFound) {
                results.add(new String[]{url, "GA Tracking Request", expectedGATID, "Found in network requests", "PASS"});
            } else {
                results.add(new String[]{url, "GA Tracking Request", expectedGATID, "Not found in network requests or unsupported browser", "FAIL"});
            }
        } catch (Exception e) {
            results.add(new String[]{url, "GA Tracking ID", expectedGATID, "Error: " + e.getMessage(), "ERROR"});
        }
    }



    @AfterTest
    public void teardownTest() {
        if (driver != null) {
            driver.quit();
        }
        try {
            String[] headers = {"URL", "Tag Name", "Expected Content", "Actual Content", "Status"};
            WriteClass.writeResultsToExcel("SEO Validation Results", headers, results, true);
        } catch (IOException e) {
            System.err.println("Failed to write results to Excel: " + e.getMessage());
        }
    }
}


