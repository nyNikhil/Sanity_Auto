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

		// Setup ChromeDriver 
			 WebDriverManager.chromedriver().setup();
	       
	         WebDriver driver = new ChromeDriver();    

	        DevTools devTools = ((HasDevTools) driver).getDevTools();
	        devTools.createSession();

	     // Enable Network tracking
	        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

	        // Add listener to capture requests
	        devTools.addListener(Network.requestWillBeSent(), request -> {
	            Request req = request.getRequest();
	            String url = req.getUrl();
	            if (url.contains("collect") && 
	            	    (url.contains("tid=G-K") || url.contains("tid=UA-"))) {
	                System.out.println("GA request URL: " + url);

	                // Extract tid from the URL
	                String[] params = url.split("[?&]");
	                for (String param : params) {
	                    if (param.startsWith("tid=")) {
	                        String tid = param.split("=")[1];
	                        System.out.println("Found tid: " + tid);
	                    }
	                }
	            }
	        });

	        // Navigate to target site
	        driver.get("https://zeenews.india.com/");

	        // Wait some time to let the network activity complete
	        try {
	            Thread.sleep(10000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        // Quit browser
	        driver.quit();
	    }
	}