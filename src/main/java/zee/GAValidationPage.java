package zee;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.*;

public class GAValidationPage {
    private WebDriver driver;
    private List<String[]> results = new ArrayList<>();

    public GAValidationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void validateGATrackingIDs() {
        Map<String, String> urlToGATrackingID = new HashMap<>();
        urlToGATrackingID.put("https://zeenews.india.com/", "G-K4Q9W8LH6V");
        urlToGATrackingID.put("https://zeenews.india.com/photos", "G-K4Q9W8LH6V");
        urlToGATrackingID.put("https://www.dnaindia.com/", "G-RBQCS7B03R");
   

        for (Map.Entry<String, String> entry : urlToGATrackingID.entrySet()) {
            validateGATrackingID(entry.getKey(), entry.getValue());
        }
    }

    private void validateGATrackingID(String url, String expectedGATID) {
        try {
            driver.get(url);

            new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

            String pageSource = driver.getPageSource();
            if (pageSource.contains(expectedGATID)) {
                results.add(new String[]{url, "GA Tracking ID", expectedGATID, "Found in page source" +expectedGATID, "PASS"});
            } else {
                results.add(new String[]{url, "GA Tracking ID", expectedGATID, "Not found in page source" +expectedGATID, "FAIL"});
            }
        } catch (Exception e) {
            results.add(new String[]{url, "GA Tracking ID", expectedGATID, "Error: " + e.getMessage(), "ERROR"});
        }
    }

    public List<String[]> getResults() {
        return results;
    }
}



