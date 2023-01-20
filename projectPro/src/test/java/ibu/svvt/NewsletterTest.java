package ibu.svvt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class NewsletterTest {
	private static WebDriver webDriver;
	private static String baseUrl;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("Webdriver.chrome.driver", "C:\\Users\\DELL\\Downloads\\chromedriver_win32");

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

	@Test
	void testNewsletter() throws InterruptedException {
		webDriver.get(baseUrl);
		Thread.sleep(2000);
		
		// this is the control to home page:
		String tabHomee = webDriver.getWindowHandle();
		System.out.println(tabHomee);
		
		JavascriptExecutor js = (JavascriptExecutor) webDriver;

		// now scroll to newsletter and click on it

		WebElement newsletter = webDriver.findElement(By.linkText("NEWSLETTER"));
		js.executeScript("arguments[0].scrollIntoView(true);", newsletter);
		Thread.sleep(3000);
		newsletter.click();
		Thread.sleep(2000);

		// give the selenium control to newsletter

		for (String tabNewsletter : webDriver.getWindowHandles()) {
			if (!tabNewsletter.equals(tabHomee)) {
				webDriver.switchTo().window(tabNewsletter);
				break;
			}
		}

		// assert that the text in p tag is "Subscribe Now to W3Scools Monthly Newsletter to Access Exclusive Content"
		String subscribe = webDriver.findElement(By.xpath("/html/body/main/div[1]/p")).getText();
		assertEquals("Subscribe Now to W3Schools Monthly Newsletter to Access Exclusive Content", subscribe);
		
		// find email input, type your mail
		webDriver.findElement(By.id("EMAIL")).sendKeys("merjema99mujic@gmail.com");
		Thread.sleep(2000);

		// accept the data privacy statement
		webDriver.findElement(By.id("OPT_IN")).click();
		Thread.sleep(3000);

		// and click the button subscribe
		webDriver.findElement(By.xpath("/html/body/main/div[1]/div/div[1]/div/div/div[4]/form/div[3]/div/button"))
				.click();
		Thread.sleep(3000);

		// close the tabNewsletter
		webDriver.close();

		// and switch to home page

		webDriver.switchTo().window(tabHomee);
		Thread.sleep(2000);
	}

}
