//package utils;
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.logging.LogEntries;
//import org.openqa.selenium.logging.LogType;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//
//import java.time.Duration;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Links {
//    
//    private static WebDriver driver;
//    private static List<String[]> results = new ArrayList<>();
//
//    public static void main(String[] args) {
//        // Set the path to the ChromeDriver executable
////        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
//    	WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
//
//        // Initialize ChromeDriver with options
////        WebDriverManager.firefoxdriver().setup();
////		driver = new FirefoxDriver();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless"); // Optional: Run in headless mode
//        driver = new ChromeDriver(options);
//
//        // Sample URL and GA Tracking ID to validate
//        String url = "https://zeenews.india.com/";
//        String expectedGATID = "G-K4Q9W8LH6V"; // Replace with your actual GA Tracking ID
//
//        // Call the method to validate GA Tracking ID
//        validateGATrackingID(url, expectedGATID);
//
//        // Print the results in the console
//        for (String[] result : results) {
//            System.out.println("URL: " + result[0]);
//            System.out.println("Type: " + result[1]);
//            System.out.println("Expected GA Tracking ID: " + result[2]);
//            System.out.println("Result: " + result[3]);
//            System.out.println("Status: " + result[4]);
//            System.out.println("----------------------------");
//        }
//
//        // Close the driver
//        driver.quit();
//    }
//
//    private static void validateGATrackingID(String url, String expectedGATID) {
//        try {
//            driver.get(url);
//
//            // Wait for the page to fully load
//            new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
//
//            // Check in page source
//            String pageSource = driver.getPageSource();
//            if (pageSource.contains(expectedGATID)) {
//                results.add(new String[]{url, "GA Tracking ID", expectedGATID, "Found in page source", "PASS"});
//            } else {
//                results.add(new String[]{url, "GA Tracking ID", expectedGATID, "Not found in page source", "FAIL"});
//            }
//
//            // Check network requests only if driver is ChromeDriver
//            boolean gaRequestFound = false;
//            if (driver instanceof ChromeDriver) {
//                LogEntries logs = driver.manage().logs().get(LogType.PERFORMANCE);
//                
//                for (org.openqa.selenium.logging.LogEntry entry : logs) {
//                    String message = entry.getMessage();
//                    System.out.println("Log Entry: " + message);  // Print logs
//
//                    if (message.contains("google-analytics.com/g/collect") && message.contains(expectedGATID)) {
//                        gaRequestFound = true;
//                        break;
//                    }
//                }
//            }
//
//            if (gaRequestFound) {
//                results.add(new String[]{url, "GA Tracking Request", expectedGATID, "Found in network requests", "PASS"});
//            } else {
//                results.add(new String[]{url, "GA Tracking Request", expectedGATID, "Not found in network requests or unsupported browser", "FAIL"});
//            }
//        } catch (Exception e) {
//            results.add(new String[]{url, "GA Tracking ID", expectedGATID, "Error: " + e.getMessage(), "ERROR"});
//        }
//    }
//}
package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Links {

    private static WebDriver driver;
    private static List<String[]> results = new ArrayList<>();

    public static void main(String[] args) {
        // Setup WebDriverManager for ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Set up Chrome options to enable performance logs
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("goog:loggingPrefs", new HashMap<String, String>() {{
            put("performance", "ALL");
        }});
        options.setExperimentalOption("prefs", prefs);

        // Initialize ChromeDriver with options
        driver = new ChromeDriver(options);

        // Sample URL and GA Tracking ID to validate
        String url = "https://zeenews.india.com/";
        String expectedGATID = "G-K4Q9W8LH6V"; // Replace with your actual GA Tracking ID

        // Call the method to validate GA Tracking ID
        validateGATrackingID(url, expectedGATID);

        // Print the results in the console
        for (String[] result : results) {
            System.out.println("URL: " + result[0]);
            System.out.println("Type: " + result[1]);
            System.out.println("Expected GA Tracking ID: " + result[2]);
            System.out.println("Result: " + result[3]);
            System.out.println("Status: " + result[4]);
            System.out.println("----------------------------");
        }

        // Close the driver
        driver.quit();
    }

    private static void validateGATrackingID(String url, String expectedGATID) {
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

            // Check network requests in performance logs (if possible)
            boolean gaRequestFound = false;

            // Check logs only if performance logging is available
            if (driver instanceof ChromeDriver) {
                LogEntries logs = driver.manage().logs().get(LogType.PERFORMANCE); // Get performance logs
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
}





