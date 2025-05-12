package zee;

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TaboolaAdCheck {
    public static void main(String[] args) {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();

        try {
            // Open the website
            driver.get("https://www.dnaindia.com/");
//            driver.get("https://zeenews.india.com/");
//              driver.get("https://www.wionews.com/");


            // Wait for page to load completely
//            Thread.sleep(5000);

            // Scroll down slowly to the bottom of the page
            JavascriptExecutor js = (JavascriptExecutor) driver;
            long lastHeight = (long) js.executeScript("return document.body.scrollHeight");
            
            while (true) {
                for (int i = 0; i < lastHeight; i += 200) { // Scroll in increments of 200 pixels
                    js.executeScript("window.scrollTo(0, arguments[0]);", i);
                    Thread.sleep(500); // Wait to allow content to load
                    
                    // Check for Taboola advertisement
                    Object result = js.executeScript("return typeof TRC !== 'undefined' && typeof TRC._taboolaClone !== 'undefined';");
                    if (Boolean.TRUE.equals(result)) {
                        System.out.println("Taboola advertisement is present on the website. Closing browser...");
                        driver.quit();
                        return;
                    }
                }
                
                long newHeight = (long) js.executeScript("return document.body.scrollHeight");
                if (newHeight == lastHeight) {
                    break;
                }
                lastHeight = newHeight;
            }

            System.out.println("Taboola advertisement was NOT found on the website.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser if it's still open
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
