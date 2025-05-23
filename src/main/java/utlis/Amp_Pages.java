package utlis;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Amp_Pages {

    public static void main(String[] args) {
    	WebDriverManager.firefoxdriver().setup();
       WebDriver driver = new FirefoxDriver();

        try {
            driver.get("https://www.wionews.com/technology");
            List<WebElement> links = driver.findElements(By.tagName("a"));

            Set<String> checkedUrls = new HashSet<>();

            for (WebElement link : links) {
                String url = link.getAttribute("href");
                if (url != null && url.contains("wionews.com") && !checkedUrls.contains(url)) {
                    checkedUrls.add(url);
                    String ampUrl = url.endsWith("/") ? url + "amp" : url + "/amp";
                    if (!isAmpPageWorking(ampUrl)) {
                        System.out.println("Failed AMP URL: " + ampUrl);
                    }
                }
            }

        } finally {
            driver.quit();
        }
    }

    public static boolean isAmpPageWorking(String url) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            ClassicHttpResponse response = (ClassicHttpResponse) client.execute(request);
            int statusCode = response.getCode();
            return statusCode == 200;
        } catch (Exception e) {
            return false;
        }
    }
}

