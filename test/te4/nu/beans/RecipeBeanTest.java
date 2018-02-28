/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package te4.nu.beans;

import nu.te4.beans.RecipeBean;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Fia
 */
public class RecipeBeanTest {
    private static WebDriver webDriver;
    private static String url = "http://94.46.140.3:8080/fia-recipe-admin/faces/recipies.xhtml";
    
    public RecipeBeanTest() {
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
        webDriver.findElement(By.cssSelector("form#search table input")).sendKeys("");
        webDriver.findElement(By.cssSelector("form#search table input")).sendKeys(Keys.ENTER);
    }

    /**
     * Test of search method, of class RecipeBean.
     */
    @Test
    public void testSearch() {
        boolean exp = true;
        RecipeBean instance = new RecipeBean();
        String search = "CHOKLADBOLLAR";
        WebDriverWait wait1 = new WebDriverWait(webDriver, 5);
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form#search table input")));
        webDriver.findElement(By.cssSelector("form#search table input")).sendKeys(search);
        webDriver.findElement(By.cssSelector("form#search table input")).sendKeys(Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));
        List<WebElement> result = webDriver.findElements(By.cssSelector("tbody tr td:nth-of-type(2) span"));
        for(int i = 0; i < result.size(); i++) {
            assertEquals(exp, result.get(i).getText().toLowerCase().contains(search.toLowerCase()));
        }
    }

    /**
     * Test of delete method, of class RecipeBean.
     */
    @Test
    public void testDelete() {
        /*int recipies = webDriver.findElements(By.cssSelector("tbody tr")).size();
        int id = Integer.parseInt(webDriver.findElement(By.cssSelector("tbody tr:last-of-type span:first-of-type")).getText());
        webDriver.findElement(By.cssSelector("tbody tr:last-of-type input[type=submit]")).click();
        int result = webDriver.findElements(By.cssSelector("tbody tr")).size();
        assertEquals(recipies - 1, result);*/
    }
    
}
