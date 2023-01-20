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

class GameTest {
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
	void testGame() throws InterruptedException {
		// we open baseUrl
		webDriver.get(baseUrl);

		// switch between tabs: webDriver.switchTo().window(<some window>) command
		// The <some window> part is called a window handle, and it is obtained via the
		// webDriver.getWindowHandle()

		// this is the control to first tab:
		String tabHome = webDriver.getWindowHandle();
		System.out.println(tabHome);
		
		// find the Theme on the top of the page:
//		webDriver.findElement(By.cssSelector("body > div.w3-bar.w3-card-2.notranslate > div.w3-right.w3-padding-16.ext_icon_container > a.w3-bar-item.w3-button.bar-icon-hover.w3-right.w3-hide-small > i")).click();
//		Thread.sleep(1000);

		// now assert that both options under the button are selected
//		assertTrue(webDriver.findElement(By.id("radio_darkpage")).isSelected());
//		assertTrue(webDriver.findElement(By.id("radio_darkcode")).isSelected());
//		Thread.sleep(1000);

		// on Home page, scroll to GameColor Picker and assert that we found the element:
		JavascriptExecutor js = (JavascriptExecutor) webDriver;

		js.executeScript("window.scrollBy(0, 8800);");
		Thread.sleep(2000);

		// we will find the game and go to the new tab
		String codeGame = webDriver.findElement(By.xpath("/html/body/div[5]/div[14]/div/h1")).getText();
		assertEquals("Code Game", codeGame);

		// open the game
		webDriver.findElement(By.linkText("Play Game")).click();
		Thread.sleep(8000);

		// now switch selenium to this new link:
		// second tab:
		for (String tabGame : webDriver.getWindowHandles()) {
			if (!tabGame.equals(tabHome)) {
				webDriver.switchTo().window(tabGame);
				break;
			}
		}
		//Thread.sleep(2000);

		// we play the game :)
		// from each level, we will play once, and then the last level:
		
		// now on this second tab:
		// we will play the music first
		webDriver.findElement(By.id("codeGame-button-musicToggle")).click();
		//Thread.sleep(1000);
		
		
		// FIRST LEVEL
		// play the game, click on the arrow forward, twice:
		webDriver.findElement(By.id("codeGame-button-forward")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.id("codeGame-button-forward")).click();
		//Thread.sleep(3000);
		
		// play code we have entered:
		webDriver.findElement(By.id("codeGame-button-runsequence")).click();
		Thread.sleep(4000);
		
		// we now click button "play next level", but we will Select the level from the list:
		webDriver.findElement(By.id("codeGame-retryLevel")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.id("codeGame-levelselector-open")).click();
		Thread.sleep(1000);
		
		// we scroll to the "Turning part":
//		WebElement scrollToTurning = webDriver.findElement(By.xpath("//*[@id=\"codeGame-levelselector-scrollcontainer\"]"));
//		js.executeScript("arguments[0].scrollIntoView(true);", scrollToTurning);
//		Thread.sleep(2000);
		
		// we choose the first one from the "Turning"
		webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div[2]/div[2]/div[1]")).click();
		Thread.sleep(2000);
		
		// SECOND LEVEL
		// and we play that one
		webDriver.findElement(By.id("codeGame-button-forward")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.id("codeGame-button-forward")).click();
		Thread.sleep(1000);
		
		webDriver.findElement(By.id("codeGame-button-left")).click();
		Thread.sleep(1000);
		
		webDriver.findElement(By.id("codeGame-button-forward")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.id("codeGame-button-forward")).click();
		//Thread.sleep(2000);
		
		webDriver.findElement(By.id("codeGame-button-runsequence")).click();
		Thread.sleep(4000);
		
		//and again, we will find more levels:
		webDriver.findElement(By.id("codeGame-retryLevel")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.id("codeGame-levelselector-open")).click();
		Thread.sleep(1000);
		
		
		// we scroll to the "Action part":
		WebElement scrollToAction = webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div[2]/h3[3]"));
		js.executeScript("arguments[0].scrollIntoView(true);", scrollToAction);
		Thread.sleep(2000);
		
		// we choose the first one from the "Action"
		webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div[2]/div[3]/div[1]")).click();
		Thread.sleep(2000);
		
		// THIRD LEVEL
		// and we play that one
		webDriver.findElement(By.id("codeGame-button-forward")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.id("codeGame-button-forward")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.id("codeGame-button-forward")).click();
		Thread.sleep(1000);
		
		webDriver.findElement(By.id("codeGame-button-use")).click();
		//Thread.sleep(1000);

		webDriver.findElement(By.id("codeGame-button-runsequence")).click();
		Thread.sleep(4000);
		
		//and again, we will find more levels:
		webDriver.findElement(By.id("codeGame-retryLevel")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.id("codeGame-levelselector-open")).click();
		Thread.sleep(1000);
		
		// we scroll to the "Condition part":
		WebElement scrollToCondition = webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div[2]/h3[4]"));
		js.executeScript("arguments[0].scrollIntoView(true);", scrollToCondition);
		Thread.sleep(2000);
		
		// we choose the first one from the "Condition"
		webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div[2]/div[4]/div[1]")).click();
		Thread.sleep(2000);

		// FOURTH LEVEL
		// and we play that one
		webDriver.findElement(By.id("codeGame-button-forward")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.id("codeGame-button-forward")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.id("codeGame-button-forward")).click();
		Thread.sleep(1000);
		
		webDriver.findElement(By.id("codeGame-button-use")).click();
		//Thread.sleep(1000);

		webDriver.findElement(By.id("codeGame-button-runsequence")).click();
		Thread.sleep(4000);
		
		//and again, we will find more levels:
		webDriver.findElement(By.id("codeGame-retryLevel")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.id("codeGame-levelselector-open")).click();
		Thread.sleep(1000);
		
		// we scroll to the "Looping part":
		WebElement scrollToLooping = webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div[2]/h3[5]"));
		js.executeScript("arguments[0].scrollIntoView(true);", scrollToLooping);
		Thread.sleep(2000);
		
		// we choose the first one from the "Looping"
		webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div[2]/div[5]/div[1]")).click();
		Thread.sleep(2000);
		
		// FIFTH LEVEL
		// and we play that one
		webDriver.findElement(By.id("codeGame-button-loop")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.id("codeGame-button-forward")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div[3]/div[2]/div[1]/div[2]/div/div[2]/div/button[1]")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div[3]/div[2]/div[1]/div[2]/div/div[2]/div/button[1]")).click();
		webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div[3]/div[2]/div[1]/div[2]/div/div[2]/div/button[1]")).click();
		//Thread.sleep(2000);

		webDriver.findElement(By.id("codeGame-button-runsequence")).click();
		Thread.sleep(4000);
		
		// and now, to the last level:
		webDriver.findElement(By.id("codeGame-retryLevel")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.id("codeGame-levelselector-open")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div[2]/div[5]/div[4]")).click();
		Thread.sleep(2000);
		
		
		String finalLevelSubTitle = webDriver.findElement(By.id("codeGame-subtitle")).getText();
		assertEquals("I must put everything in the loop.", finalLevelSubTitle);
		
		
		//FINAL LEVEL
		webDriver.findElement(By.id("codeGame-button-loop")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.id("codeGame-button-left")).click();
		webDriver.findElement(By.id("codeGame-button-forward")).click();
		webDriver.findElement(By.id("codeGame-button-forward")).click();
		webDriver.findElement(By.id("codeGame-button-forward")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.id("codeGame-button-runsequence")).click();
		Thread.sleep(4000);
		webDriver.findElement(By.id("codeGame-retryLevel")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.id("codeGame-levelselector-open")).click();
		Thread.sleep(2000);
		
		// after we finished the game, we will stop the music:
		webDriver.findElement(By.id("codeGame-button-musicToggle")).click();
		Thread.sleep(2000);

		// and we will close the tabGame:
		webDriver.close();

		// and then we go to the Home page and switch the Selenium control to the
		// tabHome:
		webDriver.switchTo().window(tabHome);
		Thread.sleep(2000);
		js.executeScript("alert('Congratulations! You have finished the game!')");	
		Thread.sleep(2000);
	}

}
