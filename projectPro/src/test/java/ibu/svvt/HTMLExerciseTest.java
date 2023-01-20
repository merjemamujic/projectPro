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
import org.openqa.selenium.support.locators.RelativeLocator;

class HTMLExerciseTest {
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
	void testHTMLExercise() throws InterruptedException {
		webDriver.get(baseUrl);
		Thread.sleep(1000);
		
		// find the Theme picker, on the top of the page:
		webDriver.findElement(By.cssSelector("body > div.w3-bar.w3-card-2.notranslate > div.w3-right.w3-padding-16.ext_icon_container > a.w3-bar-item.w3-button.bar-icon-hover.w3-right.w3-hide-small > i")).click();
		
		// now assert that both options under the button are selected
		assertTrue(webDriver.findElement(By.id("radio_darkpage")).isSelected());
		assertTrue(webDriver.findElement(By.id("radio_darkcode")).isSelected());
		Thread.sleep(1000);
		
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		
		// find HTML Exercise in the navbar and click on the HTML Exercises
		webDriver.findElement(By.linkText("Exercises")).click();
		Thread.sleep(2000);
		webDriver.findElement(By.linkText("HTML Exercises")).click();
		Thread.sleep(1000);
		
		// selenium control
		String tabHTMLExercise = webDriver.getWindowHandle();
		System.out.println(tabHTMLExercise);
		
		//find button to start the exercise and click on it
		WebElement startExercise = webDriver.findElement(By.xpath("/html/body/div[7]/div[1]/div[1]/div[3]/h2"));
		js.executeScript("arguments[0].scrollIntoView(true);", startExercise);
		Thread.sleep(3000);
		webDriver.findElement(By.xpath("/html/body/div[7]/div[1]/div[1]/div[3]/p[2]/a")).click();
		Thread.sleep(2000);
		
		// switch selenium control
		for (String tabExercises : webDriver.getWindowHandles()) {
			if (!tabExercises.equals(tabHTMLExercise)) {
				webDriver.switchTo().window(tabExercises);
				break;
			}
		}
		
		// 			type title into p tag
		webDriver.findElement(By.xpath("//*[@id=\"assignmentcontainer\"]/input")).sendKeys("title");
		Thread.sleep(2000);
		
		// click on the button Submit answer
		webDriver.findElement(By.xpath("//*[@id=\"answerbutton\"]")).click();
		Thread.sleep(2000);
		// click on the button Next Exercise
		webDriver.findElement(By.xpath("//*[@id=\"answerbutton\"]")).click();
		Thread.sleep(2000);
		
		// 			for img: in first field type 250 in the second 400
		webDriver.findElement(By.xpath("//*[@id=\"assignmentcontainer\"]/input[1]")).sendKeys("250");
		Thread.sleep(2000);
		webDriver.findElement(By.xpath("//*[@id=\"assignmentcontainer\"]/input[2]")).sendKeys("400");
		Thread.sleep(2000);
		
		// click on the button Submit answer
		webDriver.findElement(By.xpath("//*[@id=\"answerbutton\"]")).click();
		Thread.sleep(2000);
		// click on the button Next Exercise
		webDriver.findElement(By.xpath("//*[@id=\"answerbutton\"]")).click();
		Thread.sleep(2000);
		
		// 			for a: type href=
		webDriver.findElement(By.xpath("//*[@id=\"assignmentcontainer\"]/input")).sendKeys("href=");
		Thread.sleep(2000);
		
		// click on the button Submit answer
		webDriver.findElement(By.xpath("//*[@id=\"answerbutton\"]")).click();
		Thread.sleep(2000);
		// click on the button Next Exercise
		webDriver.findElement(By.xpath("//*[@id=\"answerbutton\"]")).click();
		Thread.sleep(2000);
		
		// 			for img src:  type alt
		webDriver.findElement(By.xpath("//*[@id=\"assignmentcontainer\"]/input")).sendKeys("alt");
		Thread.sleep(2000);		
		
		// just click on the button Submit answer
		webDriver.findElement(By.xpath("//*[@id=\"answerbutton\"]")).click();
		Thread.sleep(2000);
		
		// close the tab with exercises
		webDriver.close();
		
		// and then we go to the HTML exercises and switch the Selenium control back
		webDriver.switchTo().window(tabHTMLExercise);
		Thread.sleep(2000);
		
		// go back to home page:
		webDriver.findElement(By.xpath("//*[@id=\"topnav\"]/div/div[1]/a[2]")).click();
		Thread.sleep(2000);

		Thread.sleep(1000);
		js.executeScript("alert('You were GREAT at this!')");	
		Thread.sleep(2000);
		
		
	}

}
