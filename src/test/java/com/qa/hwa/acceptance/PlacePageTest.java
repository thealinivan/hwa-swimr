package com.qa.hwa.acceptance;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.util.NoSuchElementException;

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
	    private String url = "http://localhost:8081/index.html";
	    private Actions action = new Actions(driver);
	    private FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
	    
	    public WebElement waitToBeVisible(WebElement expectedElement) {
	    	return wait
	    	.withTimeout(Duration.ofSeconds(3))
	        .pollingEvery(Duration.ofMillis(30000))
	        .ignoring(Exception.class)
	        .ignoring(NoSuchElementException.class)
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
	        config.setHeadless(true);
	        driver = new ChromeDriver();
	        driver.manage().window().setSize(new Dimension(500, 900)); 
	    }
	    
	    //create
	    @Test
	    public void testCreatePlace() throws InterruptedException {
	    	driver.get(url);
	    	Thread.sleep(1000);
	    	WebElement clubLink = waitToBeVisible(getElementByXPath("//*[@id=\"render-club\"]/div/div/div[1]/a/h3"));
	    	clubLink.click();
	    	WebElement addForm = waitToBeVisible(getElementById("add-form"));
	    	action.moveToElement(addForm).build().perform();
	    	WebElement nameInput = waitToBeVisible(getElementById("add-name"));
	    	WebElement postcodeInput = waitToBeVisible(getElementById("add-postcode"));
	    	String placeName = "The Swimming Pool";
	        String placePostcode = "E11 3DW";
	        nameInput.sendKeys(placeName); 
	        postcodeInput.sendKeys(placePostcode);
	        postcodeInput.submit();
	        Thread.sleep(1000);
	        WebElement expectedName = waitToBeVisible(getElementById("card-name-2"));
	        WebElement expectedPostcode = waitToBeVisible(getElementById("card-postcode-2"));
	        //Assertions   
	        assertEquals(placeName, expectedName.getText());
	        assertEquals(placePostcode, expectedPostcode.getText());
	    }
	    
	    //read
	    @Test
	    public void testReadPlace() throws InterruptedException {
	    	driver.get(url);
	    	Thread.sleep(1000);
	    	WebElement clubLink = waitToBeVisible(getElementByXPath("//*[@id=\"render-club\"]/div/div/div[1]/a/h3"));
	    	clubLink.click();
	    	Thread.sleep(1000);
	    	String actualName = "Hackney Pool";
		    String actualPostcode = "E12 6LB";
	    	WebElement expectedName = waitToBeVisible(getElementById("card-name-1"));
	    	WebElement expectedPostcode = waitToBeVisible(getElementById("card-postcode-1"));
	        //Assertions
	        assertEquals(actualName, expectedName.getText());
	        assertEquals(actualPostcode, expectedPostcode.getText());
	    }
	    
	    
	    //update
	    @Test
	    public void testUpdatePlace() throws InterruptedException {
	    	driver.get(url);
	    	Thread.sleep(1000);
	    	WebElement clubLink = waitToBeVisible(getElementByXPath("//*[@id=\"render-club\"]/div/div/div[1]/a/h3"));
	    	clubLink.click();
	    	Thread.sleep(1000);
	    	WebElement updateBtn = waitToBeVisible(getElementByXPath("//*[@id=\"render\"]/div/div/div[3]/button"));
	    	updateBtn.click();
	    	WebElement nameInput = waitToBeVisible(getElementById("update-name"));
	    	WebElement postcodeInput = waitToBeVisible(getElementById("update-postcode"));
	    	String newPlaceName = "Another Pool";
	    	String newPlacePostcode = "E16 3QN";
	    	nameInput.clear(); 
	    	nameInput.sendKeys(newPlaceName);
	    	postcodeInput.clear(); 
	    	postcodeInput.sendKeys(newPlacePostcode);
	        postcodeInput.submit();
	        Thread.sleep(1000);
	        WebElement expectedName = waitToBeVisible(getElementById("card-name-1"));
	        WebElement expectedPostcode = waitToBeVisible(getElementById("card-postcode-1"));
	        //Assertions
	        assertEquals(newPlaceName, expectedName.getText());
	        assertEquals(newPlacePostcode, expectedPostcode.getText());
	        
	    }
	    
	    //delete
	    @Test
	    public void testDeletePlace() throws InterruptedException {
	    	driver.get(url);
	    	Thread.sleep(1000);
	    	WebElement clubLink = waitToBeVisible(getElementByXPath("//*[@id=\"render-club\"]/div/div/div[1]/a/h3"));
	    	clubLink.click();
	    	Thread.sleep(1000);
	    	WebElement deleteBtn = waitToBeVisible(getElementByXPath("//*[@id=\"render\"]/div/div/div[3]/div[1]/button"));
	    	deleteBtn.click();
	    	Thread.sleep(1000);
	    	Boolean isPresent = driver.findElements(By.id("card-name-1")).size() > 0;
	        //Assertions
	        assertEquals(false, isPresent);
	    }
	    
	    //search
	    @Test
	    public void testSearchPlace() throws InterruptedException {
	    	driver.get(url);
	    	Thread.sleep(1000);
	    	WebElement clubLink = waitToBeVisible(getElementByXPath("//*[@id=\"render-club\"]/div/div/div[1]/a/h3"));
	    	clubLink.click();
	    	Thread.sleep(1000);
	    	WebElement searchInput = waitToBeVisible(getElementById("field-search"));
	    	String searchWord = "hackney";
	    	String actualPlaceName = "Hackney Pool";
	        searchInput.sendKeys(searchWord); 
	        Thread.sleep(1000);
	        WebElement expectedName = waitToBeVisible(getElementById("card-name-1"));
	        //Assertions
	        assertEquals(actualPlaceName, expectedName.getText());
	    }
	    
	    @AfterAll
	    public static void tearDown() {
	        driver.quit();
	    }
}
