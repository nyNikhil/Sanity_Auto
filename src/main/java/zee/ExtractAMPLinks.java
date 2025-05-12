//package zee;
//
//import org.openqa.selenium.By;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//public class ExtractAMPLinks {
//    public static void main(String[] args) {
//        // Setup Firefox WebDriver
//        WebDriverManager.firefoxdriver().setup();
//        WebDriver driver = new FirefoxDriver();
//
//        try {
//            // Open the website
//            driver.get("https://zeenews.india.com/hindi/india/rajasthan");
//
//            // Wait for the page to load (optional)
//            Thread.sleep(3000);
//
//            // Get all article links (assuming they are inside <a> tags)
//            List<WebElement> links = driver.findElements(By.tagName("a"));
//
//            // Set to store unique article URLs
//            Set<String> articleUrls = new HashSet<>();
//
//            // Extract valid article URLs
//            for (WebElement link : links) {
//                String href = link.getAttribute("href");
//                if (href != null && href.contains("/hindi/india/rajasthan/")) { // Adjust based on real article URLs
//                    articleUrls.add(href);
//                }
//            }
//
//            System.out.println("Total Articles Found: " + articleUrls.size());
//
//            // Check AMP pages
//            System.out.println("Valid AMP Pages:");
//            for (String articleUrl : articleUrls) {
//                String ampUrl = articleUrl + "/amp"; // Append '/amp' to check AMP version
//                if (isValidAMPPage(ampUrl)) {
//                    System.out.println(ampUrl);
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            // Close the browser
//            driver.quit();
//        }
//    }
//
//    // Method to check if AMP page is valid (returns HTTP 200 OK)
//    private static boolean isValidAMPPage(String ampUrl) {
//        try {
//            URL url = new URL(ampUrl);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            connection.connect();
//
//            int responseCode = connection.getResponseCode();
//            return (responseCode == 200); // If response is 200, AMP page exists
//
//        } catch (Exception e) {
//            return false; // If error, assume AMP page does not exist
//        }
//    }
//}
package zee;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExtractAMPLinks {
    public static void main(String[] args) {
        // Setup Firefox WebDriver
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();

        try {
            // Open the website
            driver.get("https://zeenews.india.com/hindi/india/rajasthan");

            // Wait for the page to load (optional)
            Thread.sleep(3000);

            // Get all article links (assuming they are inside <a> tags)
            List<WebElement> links = driver.findElements(By.tagName("a"));

            // Set to store unique article URLs
            Set<String> articleUrls = new HashSet<>();

            // Extract valid article URLs
            for (WebElement link : links) {
                String href = link.getAttribute("href");
                if (href != null && href.contains("/hindi/india/rajasthan/")) { // Adjust based on real article URLs
                    articleUrls.add(href);
                }
            }

            System.out.println("Total Articles Found: " + articleUrls.size());

            // Lists for AMP validation
            Set<String> failedAMPUrls = new HashSet<>();
            
            // Check AMP pages
            for (String articleUrl : articleUrls) {
                String ampUrl = articleUrl + "/amp"; // Append '/amp' to check AMP version
                if (!isValidAMPPage(ampUrl)) {
                    failedAMPUrls.add(ampUrl);
                }
            }
            
            // Print failed AMP URLs
            System.out.println("\nFailed AMP Pages:");
            for (String failedUrl : failedAMPUrls) {
                System.out.println(failedUrl);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }

    // Method to check if AMP page is valid (returns HTTP 200 OK)
    private static boolean isValidAMPPage(String ampUrl) {
        try {
            URL url = new URL(ampUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            return (responseCode == 200); // If response is 200, AMP page exists

        } catch (Exception e) {
            return false; // If error, assume AMP page does not exist
            
        }
    }
}

