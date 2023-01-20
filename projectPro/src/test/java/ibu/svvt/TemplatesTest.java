package ibu.svvt;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class TemplatesTest {
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
	
// 																6	

	@Test
	void testTemplates() throws InterruptedException {
		webDriver.get(baseUrl);
		Thread.sleep(1000);
		
		//		HOME PAGE

		
		// find the Theme picker, on the top of the page:
		webDriver.findElement(By.cssSelector("body > div.w3-bar.w3-card-2.notranslate > div.w3-right.w3-padding-16.ext_icon_container > a.w3-bar-item.w3-button.bar-icon-hover.w3-right.w3-hide-small > i")).click();
		Thread.sleep(1000);
		
		// now assert that both options under the button are selected
		assertTrue(webDriver.findElement(By.id("radio_darkpage")).isSelected());
		assertTrue(webDriver.findElement(By.id("radio_darkcode")).isSelected());
		Thread.sleep(1000);
		
		// on Home page, scroll to Web Templates and assert that we found the element:
		JavascriptExecutor js = (JavascriptExecutor) webDriver;

		js.executeScript("window.scrollBy(0, 9870);");
		Thread.sleep(2000);
		
		String templateBrowser = webDriver.findElement(By.xpath("/html/body/div[5]/div[16]/h1")).getText();
		assertEquals("Web Templates", templateBrowser);
		
		
		// click on the button browse templates
		webDriver.findElement(By.linkText("Browse Templates")).click();
		Thread.sleep(1000);
		
		
		//		FIRST PAGE: (first tab: tabTemplateBrowser -> tabTemplate1)
		
		
		// we are now on the new page
		// this is the control to this tab:
		String tabTemplateBrowser = webDriver.getWindowHandle();
		System.out.println(tabTemplateBrowser);
		
		// now on this second tab:
		js.executeScript("window.scrollBy(0, 500);");
		Thread.sleep(2000);
		
		// find some Templete to see (the first example):
		webDriver.findElement(By.linkText("Demo")).click();
		Thread.sleep(1000);
		
		// now switch selenium to this new link:
		
		
		//	      SECOND PAGE: (second tab: tabTemplate1 -> tabTemplateBrowser)
		
		
		for (String tabTemplate1 : webDriver.getWindowHandles()) {
				if (!tabTemplate1.equals(tabTemplateBrowser)) {
					webDriver.switchTo().window(tabTemplate1);
					break;
				}
		}
		
		// on this new page, go to TOUR section from the navbar:
		webDriver.findElement(By.linkText("TOUR")).click();
		Thread.sleep(1000);
		
		// assert that the text says "TOUR DATES"
		String tourDates = webDriver.findElement(By.xpath("/html/body/div[3]/div[5]/div/h2")).getText();
		assertEquals("TOUR DATES", tourDates);
		
		// now scroll to see better
		js.executeScript("window.scrollBy(0, 50);");
		Thread.sleep(2000);
		
		// book tickets for 2 persons:
		webDriver.findElement(By.xpath("/html/body/div[3]/div[5]/div/div/div[2]/div/button")).click();
		Thread.sleep(2000);
		webDriver.findElement(By.xpath("/html/body/div[3]/div[6]/div/div/input[1]")).sendKeys("3");
		webDriver.findElement(By.xpath("/html/body/div[3]/div[6]/div/div/input[2]")).sendKeys("merjema99mujic@gmail.com");
		Thread.sleep(2000);
		webDriver.findElement(By.xpath("/html/body/div[3]/div[6]/div/div/button[1]")).click();
		Thread.sleep(2000);
		
		// and close the booking tab
		webDriver.findElement(By.xpath("/html/body/div[3]/div[6]/div/div/button[2]")).click();
		Thread.sleep(1000);
		
		// and we will close the tabTemplate1:
		webDriver.close();
		Thread.sleep(1000);
		
		
		//			FIRST PAGE again (first tab again: tabTemplateBrowser -> tabTemplate2)
 
		
		// and we switch Selenium control to the templateBrowser again
		webDriver.switchTo().window(tabTemplateBrowser);
		Thread.sleep(1000);
		
		// second example will be the Fashion Blog Template:
		// we need to scroll a little bit to see it
		js.executeScript("window.scrollBy(0, 3300);");
		Thread.sleep(2000);
		
		// open the fashion blog in a new tab
		webDriver.findElement(By.xpath("/html/body/div[7]/div[1]/div[1]/div[12]/div/div/div[1]/a")).click();
		Thread.sleep(2000);
		
		// give selenium control to this tab:
		
		
		//	 	     THIRD PAGE: (third tab: tabTemplate2 -> tabTemplateBrowser)
		
		
		for (String tabTemplate2 : webDriver.getWindowHandles()) {
			if (!tabTemplate2.equals(tabTemplateBrowser)) {
				webDriver.switchTo().window(tabTemplate2);
				break;
			}
		}
		
		// scroll to first post on the page
		js.executeScript("window.scrollBy(0, 2000);");
		Thread.sleep(2000);
		
		// like the post
		webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div[1]/div[2]/p[3]/button")).click();
		Thread.sleep(2000);
		
		// assert that reply says "Great blog post! Following"
		String blogReply = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div[1]/div[2]/div/div[2]/p")).getText();
		assertEquals("Great blog post! Following", blogReply);
		Thread.sleep(1000);
		
		// hide the blog comment
		webDriver.findElement(By.id("myBtn")).click();
		Thread.sleep(2000);
		
		// and now close the second Template
		webDriver.close();
		Thread.sleep(1000);
		
		// give the control back to the tabTemplateBrowser
		webDriver.switchTo().window(tabTemplateBrowser);
		Thread.sleep(1000);
		
		// navigate back to the HOME PAGE
		webDriver.navigate().back();
		Thread.sleep(3000);
		
	}

}
