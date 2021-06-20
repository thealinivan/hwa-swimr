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
public class IndexPageTest {
	
    private static WebDriver driver;
    private String url = "http://localhost:8081/index.html";
    private Actions action = new Actions(driver);
    private FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
    private String numberOfPlaces = "Swimming places: ";
    
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
        config.setHeadless(false);
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(500, 900)); 
    }

    //create
    @Test
    public void testCreateClub() throws InterruptedException {
    	driver.get(url);
    	WebElement addForm = waitToBeVisible(getElementById("club-form"));
    	action.moveToElement(addForm).build().perform();
    	WebElement nameInput = waitToBeVisible(getElementById("add-club-name"));
    	String savedName = "Team Birch";
        nameInput.sendKeys(savedName); 
        nameInput.submit();
        Thread.sleep(1000);
        
//        WebDriverWait wait30s = new WebDriverWait(driver,30);
//        wait30s.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[@id=\"render-club\"]"), 2));
        
        WebElement expectedName = waitToBeVisible(getElementByXPath("//*[@id=\"render-club\"]/div[2]/div/div[1]/a/h3"));
        WebElement expectedPlaces = waitToBeVisible(getElementByXPath("//*[@id=\"render-club\"]/div[2]/div/div[1]/a/p"));
        //Assertions
        assertEquals(savedName, expectedName.getText());
        assertEquals(numberOfPlaces+1, expectedPlaces.getText());
    }
    
    //read
    @Test
    public void testReadClub() throws InterruptedException {
    	driver.get(url);
    	Thread.sleep(1000);
    	String testName = "Team Birch";
    	WebElement expectedName = waitToBeVisible(getElementByXPath("//*[@id=\"render-club\"]/div[1]/div/div[1]/a/h3"));
        //Assertions
        assertEquals(testName, expectedName.getText());
    }
    
    
    //update
    @Test
    public void testUpdateClub() throws InterruptedException {
    	driver.get(url);
    	Thread.sleep(1000);
    	WebElement updateBtn = waitToBeVisible(getElementByXPath("//*[@id=\"render-club\"]/div[1]/div/div[2]/div[2]/button"));
    	updateBtn.click();
    	WebElement nameInput = waitToBeVisible(getElementById("update-club-name"));
    	String updateName = "Team Elm";
    	nameInput.clear(); 
    	nameInput.sendKeys(updateName);
        nameInput.submit();
        Thread.sleep(1000);
        WebElement expectedName = waitToBeVisible(getElementByXPath("//*[@id=\"render-club\"]/div[1]/div/div[1]/a/h3"));
        //Assertions
        assertEquals(updateName, expectedName.getText());  
    }
    
    //delete
    @Test
    public void testDeleteClub() throws InterruptedException {
    	driver.get(url);
    	Thread.sleep(1000);
    	WebElement deleteBtn = waitToBeVisible(getElementByXPath("//*[@id=\"render-club\"]/div[1]/div/div[2]/div[1]/button"));
    	deleteBtn.click();
    	Thread.sleep(1000);
    	Boolean isPresent = driver.findElements(By.xpath("//*[@id=\"render-club\"]/div[1]/div/div[1]/a/h3")).size() > 0;
        //Assertions
        assertEquals(false, isPresent);
    }
    
    //search
    @Test
    public void testSearchClub() throws InterruptedException {
    	driver.get(url);
    	WebElement searchInput = waitToBeVisible(getElementById("field-search"));
    	String searchWord = "bir";
    	String actual = "Team Birch";
        searchInput.sendKeys(searchWord); 
        Thread.sleep(300);
        searchInput.submit();
    	Thread.sleep(1000);
    	WebElement expectedName = waitToBeVisible(getElementByXPath("//*[@id=\"render-club\"]/div[1]/div/div[1]/a/h3"));
        //Assertions
        assertEquals(actual, expectedName.getText());
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}