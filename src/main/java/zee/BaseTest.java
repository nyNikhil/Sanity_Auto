package zee;

import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v126.network.Network;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    public WebDriver driver;

    public void setup() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }
 public void setupchrome() {
	 WebDriverManager.chromedriver().setup();
     
     WebDriver driver = new ChromeDriver();    

    DevTools devTools = ((HasDevTools) driver).getDevTools();
    devTools.createSession();

 // Enable Network tracking
    devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
 }

    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


