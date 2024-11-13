import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Pages.Instagram_LoginPage;

public class Insta_Login {
	private static	WebDriver driver = null;
	public static void main(String []args) {
		Ins_Login();
	}
	    public static void Ins_Login() {
		driver = new FirefoxDriver();
		Instagram_LoginPage Login = new Instagram_LoginPage(driver);
		driver.get("https://www.instagram.com/");
		driver.manage().window().maximize();
		Login.Setusername();
		Login.Setpassword();
		Login.Login();
	    }
}
		
     


