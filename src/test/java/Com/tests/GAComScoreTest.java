package Com.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v126.network.Network;
import org.openqa.selenium.devtools.v126.network.model.Request;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import zee.BaseTest;
import zee.WriteClass;
@Test
public class GAComScoreTest {
	public void GATest() {
		BaseTest baseTest = new BaseTest();
		baseTest.setupchrome();

		List<String[]> results = new ArrayList<>();
		List<String> urls = Arrays.asList(
				"https://zeenews.india.com/"    
				);
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

						results.add(new String[]{
								url,                     
								"GA Tag",               
								"tid should be present",
								tid,                     
								(tid.isEmpty() ? "Fail" : "Pass")  
						});

					}
				}
			}
		});

		// Navigate to target site
		for (String site : urls) {
			driver.get(site);

			// Wait some time to let the network activity complete
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			try {
				String[] headers = {"URL", "Tag Name", "Expected Content", "Actual Content", "Status"};
				//			
				WriteClass.writeResultsToExcel("SEO Validation Results", headers, results, true);
			} catch (IOException e) {
				System.err.println("Failed to write results to Excel: " + e.getMessage());
			}


			// Quit browser
			driver.quit();
		}
	}
}


