package selenium.test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.gargoylesoftware.htmlunit.javascript.host.Console;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class DriverSetup {
	protected static ChromeDriver driver;
	private static long TIMEOUT=60;

	public static ChromeDriver driverSetup() throws Throwable {
		ChromeDriverManager.getInstance().setup();
		driver = new ChromeDriver();
		System.out.println(driver);
		return driver;
	}

	public static long getTimeout() {
		return DriverSetup.TIMEOUT;
	}
}
