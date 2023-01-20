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
import org.openqa.selenium.support.ui.Select;

class ForumTest {
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
	void testForum() throws InterruptedException {
		webDriver.get(baseUrl);
		Thread.sleep(1000);
		
		//		HOME PAGE
		
		// this is the control to home page:
		String tabHomePage = webDriver.getWindowHandle();
		System.out.println(tabHomePage);
		
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		
		WebElement forumLink = webDriver.findElement(By.linkText("FORUM"));
		js.executeScript("arguments[0].scrollIntoView(true);", forumLink);
		Thread.sleep(1000);

		// we open the Forum page, and give it selenium sontrol
		forumLink.click();
		
		//		FORUM PAGE
		for (String tabForum : webDriver.getWindowHandles()) {
			if (!tabForum.equals(tabHomePage)) {
				webDriver.switchTo().window(tabForum);
				break;
			}
		}
		
		// we will toggle all the categories (switch them "off") on the Forum page
		
		webDriver.findElement(By.xpath("/html/body/main/div/div/div/section/ol/li[1]/h2/a[1]")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.xpath("/html/body/main/div/div/div/section/ol/li[2]/h2/a[1]")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.xpath("/html/body/main/div/div/div/section/ol/li[3]/h2/a[1]")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.xpath("/html/body/main/div/div/div/section/ol/li[4]/h2/a[1]")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.xpath("/html/body/main/div/div/div/section/ol/li[5]/h2/a[1]")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.xpath("/html/body/main/div/div/div/section/ol/li[6]/h2/a[1]")).click();
		Thread.sleep(1000);

		// we will go to the events now, to look for the event in specific time
		webDriver.findElement(By.linkText("Events")).click();
		Thread.sleep(2000);
		
		// look for the online events in "Type"
		Select onlineEvent = new Select(webDriver.findElement(By.name("show")));
		onlineEvent.selectByValue("online");
		Thread.sleep(2000);
		
		// click on Search button
		webDriver.findElement(By.xpath("//*[@id=\"ipsLayout_mainArea\"]/div/div[2]/div/div/div/form/ul/li[4]/button")).click();
		Thread.sleep(1000);
		
		//assert that there are no online events
		String noEvent = webDriver.findElement(By.xpath("/html/body/main/div/div/div/div/div[4]/div[3]")).getText();
		assertEquals("There were no events matching your search", noEvent);
		Thread.sleep(4000);
		
		//back to overview
		webDriver.findElement(By.linkText("Back to overview")).click();
		Thread.sleep(1000);
		
		// now look for the event in april
		webDriver.findElement(By.xpath("/html/body/main/div/div/div/div/div[3]/section/div/div[1]/a[4]/span[1]")).click();
		Thread.sleep(2000);
		
		//assert that there are no events in april
		String noEventApril = webDriver.findElement(By.xpath("/html/body/main/div/div/div/div/div[3]/section/div/div[2]/ul/div")).getText();
		assertEquals("No events scheduled this month", noEventApril);
		Thread.sleep(3000);
		
		// navigate back to forum
		webDriver.navigate().back();

		// close the tab and give the selenium control back to Home page
		webDriver.close();

		webDriver.switchTo().window(tabHomePage);
		Thread.sleep(2000);	
		
	}

}
