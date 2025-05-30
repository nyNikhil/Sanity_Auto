package Config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;
import java.io.InputStream;
import java.io.OutputStream;

public class Propertiesfile {
	static Properties prop = new Properties();
	static String projectPath = System.getProperty("user.dir");
	public static void main(String[]args) {
		getProperties();
		setProperties();
		getProperties();

	}
	public static void getProperties() {
		try {
			InputStream input = new FileInputStream(projectPath+"\\src\\main\\java\\Config\\config.properties");
			prop.load(input);
			String browser = prop.getProperty("browser");
			System.out.println(browser);
		}catch(Exception exp) {
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
	}
	public static void setProperties() {
		try {

			OutputStream output = new FileOutputStream(projectPath+"\\\\src\\\\main\\\\java\\\\Config\\\\config.properties");
			prop.setProperty("browser", "chrome");
			prop.store(output, null);

		} catch(Exception exp) {
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
	}

}
