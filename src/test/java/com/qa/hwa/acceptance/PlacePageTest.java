package com.qa.hwa.acceptance;

import static org.junit.Assert.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.qa.hwa.HwaSwimrApplication;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = HwaSwimrApplication.class)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:swimr-schema.sql", "classpath:swimr-data.sql"}, 
executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class PlacePageTest {
 
	 private static WebDriver driver;
	    private static WebElement targ;
	    private String url = "http://localhost:8081/index.html";
	    private Actions action = new Actions(driver);
	    private FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
	    
	    public void waitToBeVisible(WebElement expectedElement) {
	    	wait
	    	.withTimeout(Duration.ofSeconds(3))
	        .pollingEvery(Duration.ofMillis(30000))
	        .ignoring(Exception.class)
	        .until(ExpectedConditions.visibilityOf(expectedElement));
	    }
	    
	    public WebElement getElementById(String webElementId) {
	    	return driver.findElement(By.id(webElementId));
	    }
	    
	    public WebElement getElementByXPath(String webElementXPath) {
	    	return driver.findElement(By.xpath(webElementXPath));
	    }
	    
	    @BeforeAll
	    public static void setup() {
	        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
	        ChromeOptions config = new ChromeOptions();
	        config.setHeadless(false);
	        driver = new ChromeDriver();
	        driver.manage().window().setSize(new Dimension(500, 900)); 
	    }
	    
	   
	    
	    @AfterAll
	    public static void tearDown() {
	        driver.quit();
	    }
}
