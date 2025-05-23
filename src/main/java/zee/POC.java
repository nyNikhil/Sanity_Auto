package zee;

import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v126.network.Network;
import org.openqa.selenium.devtools.v126.network.model.Request;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class POC {

	   public static void main(String[] args) {

		// Setup ChromeDriver (Ensure chromedriver is in PATH or specify path)
			 WebDriverManager.chromedriver().setup();
	        //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
	         WebDriver driver = new ChromeDriver();    

	        DevTools devTools = ((HasDevTools) driver).getDevTools();
	        devTools.createSession();

	        // Enable Network monitoring
	        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

	        // Listen to network requests
	        devTools.addListener(Network.requestWillBeSent(), request -> {
	            Request req = request.getRequest();
	            System.out.println("URL: " + req.getUrl());
	            System.out.println("Method: " + req.getMethod());
	            System.out.println("Post Data: " + req.getPostData().orElse("N/A"));
	        });

	        driver.get("https://yourwebsite.com");

	        // Perform actions here to trigger network calls

	        driver.quit();
	    }
}
