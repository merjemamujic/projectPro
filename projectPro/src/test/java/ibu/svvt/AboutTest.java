package ibu.svvt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class AboutTest {
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

//												  				1
	
	@Test
	void testAbout() throws InterruptedException {
		webDriver.get(baseUrl);
		Thread.sleep(1000);
		
		// this is the control to home page:
		String tabHomee = webDriver.getWindowHandle();
		System.out.println(tabHomee);
		
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		
		// open the search tab
		webDriver.findElement(By.xpath("/html/body/div[3]/div[3]/a[1]/i")).click();
		Thread.sleep(1000);
		
		// type About and click on the search button
		webDriver.findElement(By.id("gsc-i-id1")).sendKeys("About");
		Thread.sleep(2000);
		webDriver.findElement(By.xpath("/html/body/div[1]/div/div/div/form/table/tbody/tr/td[2]")).click();
		Thread.sleep(2000);
		
		// assert that the first link is About w3Schools and click on it
		String aboutW3 = webDriver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/div[6]/div[2]/div/div/div[1]/div[1]/div[1]/div[1]/div/a")).getText();
		assertEquals("About W3Schools", aboutW3);
		webDriver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/div[6]/div[2]/div/div/div[1]/div[1]/div[1]/div[1]/div/a")).click();
		Thread.sleep(3000);
		
		// we opens a new window and switches to new window
		//webDriver.switchTo().newWindow(WindowType.TAB);
		
		// open the aboutW3Schools link in the new tab
		//webDriver.navigate().to("https://www.w3schools.com/about/default.asp");
		//Thread.sleep(3000);
		
		// we open the About page, and give it selenium sontrol

		//		About page
		for (String tabAbout : webDriver.getWindowHandles()) {
			if (!tabAbout.equals(tabHomee)) {
				webDriver.switchTo().window(tabAbout);
				break;
			}
		}

		// find about terms and scroll to "Using w2schools in Teaching" and navigate back
		webDriver.findElement(By.linkText("About Terms")).click();
		Thread.sleep(2000);
		
		WebElement teaching = webDriver.findElement(By.xpath("/html/body/div[7]/div[1]/div[1]/h2[5]"));
		js.executeScript("arguments[0].scrollIntoView(true);", teaching);
		Thread.sleep(3000);
		
		webDriver.navigate().back();
		
		// scroll to "easy learning" to "read"
		WebElement learning = webDriver.findElement(By.xpath("/html/body/div[7]/div[1]/div[1]/h2[2]"));
		js.executeScript("arguments[0].scrollIntoView(true);", learning);
		Thread.sleep(3000);
		
		// scroll to table at the end and assert that the source is Google Analytics
		WebElement w3SchoolsDemographics = webDriver.findElement(By.xpath("/html/body/div[7]/div[1]/div[1]/h2[7]"));
		js.executeScript("arguments[0].scrollIntoView(true);", w3SchoolsDemographics);
		Thread.sleep(1000);
		
		String source = webDriver.findElement(By.xpath("/html/body/div[7]/div[1]/div[1]/p[18]")).getText();
		assertEquals("Source: Google Analytics", source);
		Thread.sleep(1000);
		
		// return to home page
		webDriver.navigate().back();
		webDriver.switchTo().window(tabHomee);
		Thread.sleep(2000);
		
		// on the home page, we need to close the About pop up window
		webDriver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/div[1]")).click();
		Thread.sleep(2000);
		
//		// now scroll to newsletter and click on it
//		
//		WebElement newsletter = webDriver.findElement(By.linkText("NEWSLETTER"));
//		js.executeScript("arguments[0].scrollIntoView(true);", newsletter);
//		Thread.sleep(1000);
//		newsletter.click();
//		Thread.sleep(2000);
//		
//		// give the selenium control to newsletter
//		
//		for (String tabNewsletter : webDriver.getWindowHandles()) {
//			if (!tabNewsletter.equals(tabHomee)) {
//				webDriver.switchTo().window(tabNewsletter);
//				break;
//			}
//		}
//		
//		// find email input, type your mail
//		webDriver.findElement(By.id("EMAIL")).sendKeys("merjema99mujic@gmail.com");
//		Thread.sleep(2000);
//		
//		// accept the data privacy statement
//		webDriver.findElement(By.id("OPT_IN")).click();
//		Thread.sleep(3000);
//		
//		// and click the button subscribe
//		webDriver.findElement(By.xpath("/html/body/main/div[1]/div/div[1]/div/div/div[4]/form/div[3]/div/button")).click();
//		Thread.sleep(3000);
//		
//		// close the tabNewsletter
//		webDriver.close();
//		
//		// and switch to home page
//		
//		webDriver.switchTo().window(tabHomee);
//		Thread.sleep(2000);
		
	}
}
