/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package te4.nu.beans;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Fia
 */
public class LoginBeanTest {
    private static WebDriver webDriver;
    private static String url = "http://94.46.140.3:8080/fia-recipe-admin/";
    
    public LoginBeanTest() {
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
     * Test of login method, of class LoginBean.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        webDriver.findElement(By.id("j_idt5:username")).sendKeys("Fia");
        webDriver.findElement(By.id("j_idt5:password")).sendKeys("hejsantjosan");
        webDriver.findElement(By.cssSelector("input[type=submit]")).click();
        
        String exp = "Admin page for Recept";
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        String result =  webDriver.findElement(By.tagName("h1")).getText();
        assertEquals(exp, result);
    }
    
}
