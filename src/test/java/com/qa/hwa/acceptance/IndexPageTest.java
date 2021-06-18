package com.qa.hwa.acceptance;



import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
    private static WebElement targ;

    @BeforeAll
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        //set to true to skip the visuals when testing
//        ChromeOptions config = new ChromeOptions();
//        config.setHeadless(false);
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1000, 768)); 
    }

    @Test
    public void testCreateClub() throws InterruptedException {
    	driver.get("http://localhost:8081/index.html"); 
        WebElement addClub = driver.findElement(By.id("add-club-name"));
        String clubName = "Team Birch Swimming Club";
        addClub.sendKeys(clubName);
        Thread.sleep(5000); 
        addClub.submit();
        Thread.sleep(5000); 
        //Assertions
        targ = driver.findElement(By.xpath("/html/body/main/section[3]/div/div[1]/div/div[1]/a/h3"));
        assertEquals(clubName, targ.getText());
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}