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

class HTMLTutorialTest {
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
	void testHTMLTutorial() throws InterruptedException {
		webDriver.get(baseUrl);
		Thread.sleep(1000);
		
		// find the Theme picker, on the top of the page:
		webDriver.findElement(By.cssSelector("body > div.w3-bar.w3-card-2.notranslate > div.w3-right.w3-padding-16.ext_icon_container > a.w3-bar-item.w3-button.bar-icon-hover.w3-right.w3-hide-small > i")).click();
		
		// now assert that both options under the button are selected
		assertTrue(webDriver.findElement(By.id("radio_darkpage")).isSelected());
		assertTrue(webDriver.findElement(By.id("radio_darkcode")).isSelected());
		Thread.sleep(1000);
		
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		
		// click to link "where to start"
		webDriver.findElement(By.linkText("Not Sure Where To Begin?")).click();
		Thread.sleep(3000);
		
		// and go to HTML Tutorials but in a new tab
		webDriver.findElement(By.xpath("/html/body/div[6]/div/div[1]/div[2]/a/div/h2")).click();
		Thread.sleep(2000);
		
//		// now switch selenium to this new link:
//		// second tab:
//		for (String tabGame : webDriver.getWindowHandles()) {
//			if (!tabGame.equals(tabHome)) {
//				webDriver.switchTo().window(tabGame);
//				break;
//			}
//		}
		
		// and go to HTML Basic
		webDriver.findElement(By.linkText("HTML Basic")).click();
		Thread.sleep(3000);
		
		// we go to <p> tag to see it
		WebElement p = webDriver.findElement(By.xpath("/html/body/div[7]/div[1]/div[1]/h2[4]"));
		js.executeScript("arguments[0].scrollIntoView(true);", p);
		Thread.sleep(3000);
//		webDriver.findElement(By.xpath("/html/body/div[7]/div[1]/div[1]/h2[4]")).getText();
//		Thread.sleep(3000);
		
		// we go to <a> tag to see it
		WebElement a = webDriver.findElement(By.xpath("/html/body/div[7]/div[1]/div[1]/h2[5]"));
		js.executeScript("arguments[0].scrollIntoView(true);", a);
		Thread.sleep(3000);
//		webDriver.findElement(By.xpath("/html/body/div[7]/div[1]/div[1]/h2[5]")).getText();
//		Thread.sleep(3000);
		
		// we go to <img> tag to see it
		WebElement img = webDriver.findElement(By.xpath("/html/body/div[7]/div[1]/div[1]/h2[6]"));
		js.executeScript("arguments[0].scrollIntoView(true);", img);
		Thread.sleep(3000);
//		webDriver.findElement(By.xpath("/html/body/div[7]/div[1]/div[1]/h2[6]")).getText();
//		Thread.sleep(3000);
		
		// now we go to HTML tables to learn them, and hopefully to try them by ourselves
		webDriver.findElement(By.linkText("HTML Tables")).click();
		Thread.sleep(3000);
		
		// give it selenium control, we will need it to close the editor window
		
		// the selenium control is on the page HTML Table Tutorial:
		String tabTableTutorial = webDriver.getWindowHandle();
		System.out.println(tabTableTutorial);
		
		// scroll to see the whole table and click on the button Try it Yourself
		js.executeScript("window.scrollBy(0, 200);");
		Thread.sleep(3000);
//		webDriver.findElement(By.xpath("/html/body/div[7]/div[1]/div[1]/div[3]/a")).click();
//		Thread.sleep(3000);
		
		// scroll to table exercise
		WebElement tableExercise = webDriver.findElement(By.xpath("/html/body/div[7]/div[1]/div[1]/form/div/h2"));
		js.executeScript("arguments[0].scrollIntoView(true);", tableExercise);
		Thread.sleep(3000);
		
		// insert the values into table:
		webDriver.findElement(By.name("ex1")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.name("ex1")).sendKeys("<tr>");
		Thread.sleep(2000);
		webDriver.findElement(By.name("ex4")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.name("ex4")).sendKeys("</tr>");
		Thread.sleep(2000);
		
		webDriver.findElement(By.name("ex2")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.name("ex2")).sendKeys("Name");
		Thread.sleep(2000);
		webDriver.findElement(By.name("ex3")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.name("ex3")).sendKeys("Age");
		Thread.sleep(2000);
		
		// and submit the answer:
		webDriver.findElement(By.xpath("/html/body/div[7]/div[1]/div[1]/form/div/button")).click();
		Thread.sleep(3000);
		
		// now switch selenium to this table exercise link:
		// second tab:
		for (String tabTableExercise : webDriver.getWindowHandles()) {
			if (!tabTableExercise.equals(tabTableTutorial)) {
				webDriver.switchTo().window(tabTableExercise);
				break;
			}
		}
		
		// scroll to submit answer
		WebElement submit = webDriver.findElement(By.id("answerbutton"));
		js.executeScript("arguments[0].scrollIntoView(true);", submit);
		Thread.sleep(2000);
		submit.click();
		Thread.sleep(2000);
		
		// assert that the answer is not correct
		String notCorrect = webDriver.findElement(By.tagName("h2")).getText();
		assertEquals("Not Correct", notCorrect);
		Thread.sleep(1000);
		
		// click on the screen to try again
		webDriver.findElement(By.xpath("/html/body/table/tbody/tr/td/div[4]/div[5]")).click();
		Thread.sleep(1000);	
		
		// now insert the correct values:
		webDriver.findElement(By.cssSelector("#assignmentcontainer > input:nth-child(6)")).click();
		webDriver.findElement(By.cssSelector("#assignmentcontainer > input:nth-child(6)")).clear();
		Thread.sleep(1000);
		webDriver.findElement(By.cssSelector("#assignmentcontainer > input:nth-child(6)")).sendKeys("<th>Name</th>");
		Thread.sleep(2000);
		
		webDriver.findElement(By.cssSelector("input.editablesection:nth-child(9)")).click();
		webDriver.findElement(By.cssSelector("input.editablesection:nth-child(9)")).clear();
		Thread.sleep(1000);
		webDriver.findElement(By.cssSelector("input.editablesection:nth-child(9)")).sendKeys("<th>Age</th>");
		Thread.sleep(2000);
		
		// scroll to submit answer
		WebElement submit2 = webDriver.findElement(By.id("answerbutton"));
		js.executeScript("arguments[0].scrollIntoView(true);", submit2);
		Thread.sleep(2000);
		submit.click();
		Thread.sleep(2000);
		
		// assert that now it is the correct answer
		String correct = webDriver.findElement(By.xpath("//*[@id=\"assignmentCorrect\"]/h2")).getText();
		assertEquals("Correct!", correct);
		Thread.sleep(1000);
		
		// and now we will close the tabTableExercise:
		webDriver.close();

		// selenium control back
		webDriver.switchTo().window(tabTableTutorial);
		Thread.sleep(2000);
	
		webDriver.navigate().to(baseUrl);
		Thread.sleep(2000);
		
	}

}
