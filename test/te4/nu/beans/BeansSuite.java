/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package te4.nu.beans;

import com.mysql.jdbc.Connection;
import nu.te4.utilities.ConnectionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Fia
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({te4.nu.beans.LoginBeanTest.class, te4.nu.beans.ReviewBeanTest.class, te4.nu.beans.RecipeBeanTest.class, te4.nu.beans.UserBeanTest.class})
public class BeansSuite {
    private static WebDriver webDriver;
    private static String url = "http://94.46.140.3:8080/fia-recipe-admin/";
    private static Connection connection;

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.setProperty("webdriver.chrome.driver", "/Volumes/Godzilla/TE4/Java/chromedriver/chromedriver");
        webDriver = new ChromeDriver();
        webDriver.get(url);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        webDriver.close();
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
}
