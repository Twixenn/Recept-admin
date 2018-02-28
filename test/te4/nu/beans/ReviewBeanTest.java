/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package te4.nu.beans;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Fia
 */
public class ReviewBeanTest {
    private static WebDriver webDriver;
    private static String url = "http://94.46.140.3:8080/fia-recipe-admin/faces/reviews.xhtml";
    
    public ReviewBeanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.setProperty("webdriver.chrome.driver", "/Volumes/Godzilla/TE4/Java/chromedriver/chromedriver");
        webDriver = new ChromeDriver();
        webDriver.get(url);
    }
    
    @AfterClass
    public static void tearDownClass() {
        webDriver.close();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of delete method, of class ReviewBean.
     */
    @Test
    public void testDelete() {
        int reviews = webDriver.findElements(By.cssSelector("tbody tr")).size();
        int id = Integer.parseInt(webDriver.findElement(By.cssSelector("tbody tr:last-of-type span:first-of-type")).getText());
        webDriver.findElement(By.cssSelector("tbody tr:last-of-type input[type=submit]")).click();
        int result = webDriver.findElements(By.cssSelector("tbody tr")).size();
        assertEquals(reviews - 1, result);
    }
    
}
