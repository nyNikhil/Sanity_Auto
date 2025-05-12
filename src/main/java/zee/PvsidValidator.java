package zee;

//import io.github.bonigarcia.wdm.WebDriverManager;
//
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.logging.LogEntries;
//import org.openqa.selenium.logging.LogEntry;
//import org.openqa.selenium.logging.LogType;
//import java.util.*;
//import java.util.concurrent.TimeUnit;
//
//public class PvsidValidator {
//    public static void main(String[] args) {
//       
//        Map<String, String> pagePvsidMap = new HashMap<>();
//        pagePvsidMap.put("https://www.dnaindia.com/", "1977097117828061");
//        pagePvsidMap.put("https://www.dnaindia.com/web-stories", "1635320381983685");
//        pagePvsidMap.put("https://www.dnaindia.com/icc-champions-trophy-2025", "2004744735754038");
//        pagePvsidMap.put("https://www.dnaindia.com/videos", "3258463402434492");
//        pagePvsidMap.put("https://www.dnaindia.com/business", "3258463402434492");
//        pagePvsidMap.put("https://www.dnaindia.com/photo-gallery", "386907020343783");
//        pagePvsidMap.put("https://www.dnaindia.com/india", "2762989794341218");
//        pagePvsidMap.put("https://www.dnaindia.com/maha-kumbh-mela-2025", "4330040987448308");
//        pagePvsidMap.put("https://www.dnaindia.com/education", "858309283504015");
//        pagePvsidMap.put("https://www.dnaindia.com/automobile", "301859310778008");
//        pagePvsidMap.put("https://www.dnaindia.com/explainer", "300634606631230");
//        pagePvsidMap.put("https://www.dnaindia.com/entertainment", "498408482741386");
//        pagePvsidMap.put("https://www.dnaindia.com/viral", "1889643740194949");
//        pagePvsidMap.put("https://www.dnaindia.com/topic/dna-her", "4267467025445613");
//        pagePvsidMap.put("https://www.dnaindia.com/lifestyle", "3982440211226598");
//        pagePvsidMap.put("https://www.dnaindia.com/sports", "799435452865819");
//        pagePvsidMap.put("https://www.dnaindia.com/world", "3672883362513914");
//
//      
//        WebDriverManager.firefoxdriver().setup();
//        FirefoxOptions options = new FirefoxOptions();
//        options.setCapability("goog:loggingPrefs", Map.of("performance", "ALL")); // Enable Network Logging
//        WebDriver driver = new FirefoxDriver(options);
//
//        boolean allMatched = true;
//
//      
//        for (Map.Entry<String, String> entry : pagePvsidMap.entrySet()) {
//            String pageUrl = entry.getKey();
//            String expectedPvsid = entry.getValue();
//
//            // Open the Page
//            driver.get(pageUrl);
//            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//
//            boolean found = checkPvsidInNetworkLogs(driver, expectedPvsid);
//
//            if (found) {
//                System.out.println("PASS: Found PVSID " + expectedPvsid + " on " + pageUrl);
//            } else {
//                System.out.println("FAIL: PVSID " + expectedPvsid + " NOT found on " + pageUrl);
//                allMatched = false;
//            }
//        }
//
//       
//        if (allMatched) {
//            System.out.println("All pages have correct PVSIDs!");
//        } else {
//            System.out.println("Some pages are missing their expected PVSIDs.");
//        }
//
//        // Close Browser
//        driver.quit();
//    }
//
//    private static boolean checkPvsidInNetworkLogs(WebDriver driver, String pvsid) {
//        try {
//            LogEntries logs = driver.manage().logs().get(LogType.PERFORMANCE);
//            for (LogEntry entry : logs) {
//                if (entry.getMessage().contains("ads?pvs") && entry.getMessage().contains(pvsid)) {
//                    return true; //  PVSID Found!
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("Error reading network logs.");
//            e.printStackTrace();
//        }
//        return false;
//    }
//}


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v126.network.Network;
import org.openqa.selenium.devtools.v126.network.model.Request;


import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class PvsidValidator {
    public static void main(String[] args) {
//    	WebDriverManager.firefoxdriver().setup();
//        WebDriver driver = new FirefoxDriver();
    	WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        
        Set<String> foundPvsIds = new CopyOnWriteArraySet<>();
        
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        
        devTools.addListener(Network.requestWillBeSent(), requestEvent -> {
            Request request = requestEvent.getRequest();
            String url = request.getUrl();
            if (url.contains("ads?pvs")) {
                String[] params = url.split("\\?")[1].split("&");
                for (String param : params) {
                    if (param.startsWith("pvsid=")) {
                        foundPvsIds.add(param.split("=")[1]);
                    }
                }
            }
        });

        driver.get("https://www.dnaindia.com/");
        
        // List of expected PVS IDs
        Set<String> expectedPvsIds = Set.of(
            "1977097117828061", "1635320381983685", "2004744735754038", "3258463402434492", "386907020343783",
            "2762989794341218", "4330040987448308", "858309283504015", "301859310778008", "300634606631230",
            "498408482741386", "1889643740194949", "4267467025445613", "3982440211226598", "799435452865819",
            "3672883362513914"
        );
        
        try {
            Thread.sleep(10000); // Wait for network requests to be captured
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        for (String pvsid : expectedPvsIds) {
            if (foundPvsIds.contains(pvsid)) {
                System.out.println("PASS: PVS ID " + pvsid + " found");
            } else {
                System.out.println("FAIL: PVS ID " + pvsid + " not found");
            }
        }
        
        driver.quit();
    }
}





