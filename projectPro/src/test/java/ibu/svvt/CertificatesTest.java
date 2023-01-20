package ibu.svvt;

import static org.junit.Assert.assertTrue;
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

class CertificatesTest {
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
	void testCertificate() throws InterruptedException {
		webDriver.get(baseUrl);
		Thread.sleep(1000);
		
		// selenium control
		String tabHomeC = webDriver.getWindowHandle();
		System.out.println(tabHomeC);
		
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		
		// find Get Certified in the navbar and click on it
		webDriver.findElement(By.linkText("Get Certified")).click();
		Thread.sleep(2000);
		
		// switch selenium control
		for (String tabCertificate : webDriver.getWindowHandles()) {
			if (!tabCertificate.equals(tabHomeC)) {
				webDriver.switchTo().window(tabCertificate);
				break;
			}
		}
		
		// assert that we are on the page with certificates
		String certificates = webDriver.getCurrentUrl();
		assertEquals("https://campus.w3schools.com/collections/course-catalog", certificates);
		Thread.sleep(1000);
		
		// view as list:
		webDriver.findElement(By.cssSelector(".icon-list")).click();
		Thread.sleep(2000);
		
		// filter the courses by date, old to new
		Select filterFeatured = new Select(webDriver.findElement(By.id("product_grid_sort")));
		filterFeatured.selectByValue("created-ascending");
		Thread.sleep(2000);
		
		// minimize the price filter
		webDriver.findElement(By.xpath("/html/body/main/div[1]/div[1]/div[2]/div/div[1]/button")).click();
		Thread.sleep(2000);
		
		// assert that HTML is the first one
		String learnHTML = webDriver.findElement(By.linkText("Learn HTML")).getText();
		assertEquals("Learn HTML", learnHTML);
		Thread.sleep(1000);
		
		// buy the certificate (go to quick shop button)
		webDriver.findElement(By.xpath("/html/body/main/div[1]/div[1]/div[3]/ul/li[1]/div/div/div[3]/div[3]/button")).click();
		Thread.sleep(3000);
		
		// it will open the pop up window
		// in it we will click to see the the image of the certificate
		webDriver.findElement(By.xpath("/html/body/div[7]/div/div/article/div[1]/div[2]/div/button[2]/span/img")).click();
		Thread.sleep(2000);
		
		// and buy it
		webDriver.findElement(By.xpath("/html/body/div[7]/div/div/article/div[2]/div/div[4]/div/div/form/div[1]/div[2]/div/div/div/button[1]")).click();
		Thread.sleep(3000);
		
		// type in email
		webDriver.findElement(By.id("email")).sendKeys("merjema99mujic@gmail.com");
		Thread.sleep(1000);
		
		// type in first name
		webDriver.findElement(By.id("TextField1")).sendKeys("Merjema");
		Thread.sleep(1000);
		
		// type in last name
		webDriver.findElement(By.id("TextField2")).sendKeys("Mujić");
		Thread.sleep(1000);
		
		// scroll a little bit, to see better
		WebElement scrollAddress = webDriver.findElement(By.id("TextField5"));
		js.executeScript("arguments[0].scrollIntoView(true);", scrollAddress);
		
		// type in address
		webDriver.findElement(By.id("TextField7")).sendKeys("Butmirska cesta bb");
		Thread.sleep(1000);
		
		// type in postal code
		webDriver.findElement(By.id("TextField5")).sendKeys("71210");
		Thread.sleep(1000);
		
		// type in city
		webDriver.findElement(By.id("TextField6")).sendKeys("Sarajevo");
		Thread.sleep(3000);
		
		// click continue to payment button
		webDriver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/div/div[2]/div/div/div/div[2]/div/div/div/main/form/div[1]/div/div/div[2]/div[1]/button")).click();
		Thread.sleep(3000);
	
		// close the tab
		webDriver.close();
		
		//give the selenium control back to home page
		webDriver.switchTo().window(tabHomeC);
		Thread.sleep(1000);
		js.executeScript("alert('We are done! Thank you for your attention!')");	
		Thread.sleep(3000);
		

	}

}
