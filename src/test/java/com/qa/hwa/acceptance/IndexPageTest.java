package com.qa.hwa.acceptance;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import com.qa.hwa.HwaSwimrApplication;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = HwaSwimrApplication.class)
@ActiveProfiles("test")
public class IndexPageTest {
	
    private static WebDriver driver;
    private static WebElement targ;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1366, 768)); 
    }

    @Test
    public void test() throws InterruptedException {
        driver.get("http://localhost:8080/index.html");
        Thread.sleep(5000);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}