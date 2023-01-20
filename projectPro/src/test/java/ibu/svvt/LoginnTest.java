package ibu.svvt;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class LoginnTest {
	private static WebDriver webDriver;
	private static String baseUrl;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("Webdriver.chrome.driver", "C:\\Users\\DELL\\Downloads\\chromedriver_win32 (1)");

		ChromeOptions options = new ChromeOptions();
		// to maximize the window
		options.addArguments("--start-maximized");

		webDriver = new ChromeDriver(options);
		baseUrl = "https://www.w3schools.com/";
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		webDriver.quit();
	}
	
//													  				2
	
	@Test
	void testLogin() throws InterruptedException {
		// ok, so this is the login, which is ok but it doesn't actually log in
		// tests are working and everything is green

		webDriver.get(baseUrl);
		Thread.sleep(2000);

		webDriver.findElement(By.id("w3loginbtn")).click();
		Thread.sleep(2000);

		// sign up button
		webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[4]/div[1]/div/div[2]/form/div[1]/div[1]/span/span")).click();
		Thread.sleep(1000);
		
		//assert that it says Sign up on the page
		String signUp = webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[4]/div[1]/div/div[1]/h1")).getText();
		assertEquals("Sign up", signUp);

		// we sign up
		webDriver.findElement(By.name("email")).sendKeys("merjema99mujic@gmail.com");
		Thread.sleep(3000);
		webDriver.findElement(By.name("new-password")).sendKeys("Somepassword123.");
		Thread.sleep(2000);

		// this is for the check box, unselect it
		webDriver.findElement(By.xpath("//*[@id=\"emailConsentCheckBox-wrapper\"]/div/label/span")).click();
		Thread.sleep(2000);

		// sign in button
		webDriver.findElement(By.tagName("button")).click();
		Thread.sleep(1000);

		// we are back at the home page
		String urlHome = webDriver.getCurrentUrl();
		assertEquals("https://www.w3schools.com/", urlHome);
		Thread.sleep(2000);

		// now we log in
		webDriver.findElement(By.id("w3loginbtn")).click();
		Thread.sleep(3000);

		// assert that it says "Sign up" on the window
		String logIn = webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[4]/div[1]/div/div[1]/h1")).getText();
		assertEquals("Log in", logIn);

		webDriver.findElement(By.name("email")).sendKeys("merjema99mujic@gmail.com");
		Thread.sleep(2000);

		webDriver.findElement(By.name("current-password")).sendKeys("Somepassword123.");
		Thread.sleep(2000);

		// log in button
		webDriver.findElement(By.tagName("button")).click();
		//Thread.sleep(3000);
		
		// and we are on the home page again, ready to do more stuff
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript("alert('w3Schools, Let us see see what secrets do you have')");	
		Thread.sleep(3000);
		
	}

}
