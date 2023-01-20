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

class ColorsTest {
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

//																5
	
	@Test
	void testColors() throws InterruptedException {

		// Let's learn something about the colors!
		webDriver.get(baseUrl);
		Thread.sleep(1000);
		
		// find the Theme picker, on the top of the page:
		webDriver.findElement(By.cssSelector("body > div.w3-bar.w3-card-2.notranslate > div.w3-right.w3-padding-16.ext_icon_container > a.w3-bar-item.w3-button.bar-icon-hover.w3-right.w3-hide-small > i")).click();
		Thread.sleep(1000);
		
		// now assert that both options under the button are selected
		assertTrue(webDriver.findElement(By.id("radio_darkpage")).isSelected());
		assertTrue(webDriver.findElement(By.id("radio_darkcode")).isSelected());
		Thread.sleep(1000);
		
		// on Home page, scroll to Color Picker and assert that we found the element:
		JavascriptExecutor js = (JavascriptExecutor) webDriver;

		js.executeScript("window.scrollBy(0, 8300);");
		Thread.sleep(2000);
		
		String colorPicker = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div[13]/div/h4")).getText();
		assertEquals("W3Schools' famous color picker:", colorPicker);
		
		// open the color picker
		webDriver.findElement(By.xpath("//*[@id=\"main\"]/div[13]/div/a/img")).click();
		Thread.sleep(1000);

		
		// go to color values to learn some colors:
		webDriver.findElement(By.linkText("Color Values")).click();
		Thread.sleep(1000);

		// we will find black color to see "the lecture" and assert that we have found it
		String colorHex = webDriver.findElement(By.xpath("/html/body/div[7]/div[1]/div[1]/div[3]/div[1]/div/span/a")).getText();
		assertEquals("#000000", colorHex);
		Thread.sleep(2000);

		// we will "learn" one more color and for this one we will sort colors by Color name
		// and we will find the second color by Color name
		webDriver.findElement(By.linkText("Click here to see the colors sorted by name")).click();
		Thread.sleep(2000);
		
		// we are now on a different page, so we will assert that and scroll to see the color
		String colorNames = webDriver.getCurrentUrl();
		assertEquals("https://www.w3schools.com/colors/colors_names.asp", colorNames);
		
		js.executeScript("window.scrollBy(0, 4500);");
		Thread.sleep(2000);
		
		// this is our second color: #FFE4E1
		String colorName = webDriver.findElement(By.xpath("/html/body/div[7]/div[1]/div[1]/div[3]/div[99]/div/span/a")).getText();
		assertEquals("MistyRose", colorName);
		Thread.sleep(2000);

		// from this page, we go to color mixer to see these two colores mixed:
		webDriver.findElement(By.linkText("Color Mixer")).click();
		Thread.sleep(2000);

		// scroll a little bit, so that we can see what is happening
		js.executeScript("window.scrollBy(0, 390);");
		Thread.sleep(3000);
		
		// we send black to be the bottom color
		webDriver.findElement(By.id("colorbottom")).clear();
		webDriver.findElement(By.id("colortop")).clear();
		webDriver.findElement(By.id("colorbottom")).sendKeys("#000000");
		webDriver.findElement(By.xpath("/html/body/div[7]/div[1]/div[1]/form/div/div/div[2]/div[3]/input[2]")).click();
		Thread.sleep(3000);
		
		// and other one to be top color
		webDriver.findElement(By.id("colortop")).clear();
		webDriver.findElement(By.id("colortop")).click();
		webDriver.findElement(By.id("colortop")).sendKeys("#FFE4E1");
		webDriver.findElement(By.xpath("/html/body/div[7]/div[1]/div[1]/form/div/div/div[2]/div[1]/input[2]")).click();
		Thread.sleep(4000);
		
		// and we will also try 2 new colors, to see them mixed
		// pick this 2 colors:
		// bottom color: #660033
		webDriver.findElement(By.xpath("/html/body/div[7]/div[1]/div[1]/form/div/div/div[1]/div[3]/map/area[112]")).click();
		Thread.sleep(3000);
		
		// top color:    #FF6699
		webDriver.findElement(By.xpath("/html/body/div[7]/div[1]/div[1]/form/div/div/div[1]/div[1]/map/area[90]")).click();
		Thread.sleep(4000);
		
		// now, navigate back to home and assert that we are back at home page     ---   ici direktno ako su novi linkovi otvarani
		webDriver.get(baseUrl);
		String homeLink = webDriver.getCurrentUrl();
		assertEquals("https://www.w3schools.com/", homeLink);
		
		// at the end, congratulations, you have finished the task successfully!
		js.executeScript("alert('Congratulations! You learned something new about the colors.')");	
		Thread.sleep(3000);
		
		
		
	}

}
