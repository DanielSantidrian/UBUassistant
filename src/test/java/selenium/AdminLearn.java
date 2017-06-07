package selenium;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runner.JUnitCore;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class AdminLearn {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {

    System.setProperty("webdriver.chrome.driver", ".\\rsc\\chromedriver.exe");
	driver = new ChromeDriver();
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();
   	
    JUnitCore junit = new JUnitCore();
    junit.run(NoAnswer.class);
  }

  @Test
  public void testAdminLearn() throws Exception {
    driver.get(baseUrl + "/UBUassistant/index.jsp");
    driver.findElement(By.cssSelector("input.adminLink")).click();
    Thread.sleep(2000);
    driver.findElement(By.id("user")).clear();
    driver.findElement(By.id("user")).sendKeys("admin2@ubu.es");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("admin2");
    driver.findElement(By.cssSelector("button")).click();
    Thread.sleep(2000);
    driver.findElement(By.id("learn")).click();
    Thread.sleep(2000);
    driver.findElement(By.cssSelector("button.saveas")).click();
    Thread.sleep(2000);
    assertTrue(isElementPresent(By.linkText("XLS")));
    
    int rowCount=driver.findElements(By.xpath("//table[@id='tabla']/tbody/tr")).size();
    
    assertEquals("ccc", driver.findElement(By.xpath("//table[@id='tabla']/tbody/tr["+rowCount+"]/td[2]")).getText());
    driver.findElement(By.xpath("(//input[@id='button'])["+(rowCount*2)+"]")).click();
    Thread.sleep(2000);
    
    rowCount=driver.findElements(By.xpath("//table[@id='tabla']/tbody/tr")).size();
    
    assertEquals("verve", driver.findElement(By.xpath("//table[@id='tabla']/tbody/tr["+rowCount+"]/td[2]")).getText());
    driver.findElement(By.xpath("(//input[@id='button'])["+(rowCount*2-1)+"]")).click();
    Thread.sleep(2000);
    driver.findElement(By.linkText("Editor de Casos")).click();
    Thread.sleep(2000);
    driver.findElement(By.id("edit")).click();
    
    int rowCount2=driver.findElements(By.xpath("//table[@id='tabla']/tbody/tr")).size();

    assertEquals("verve", driver.findElement(By.xpath("//table[@id='tabla']/tbody/tr["+rowCount2+"]/td[2]")).getText());
    driver.findElement(By.xpath("(//input[@id='button'])["+(rowCount2*2)+"]")).click();
    Thread.sleep(2000);
    driver.switchTo().alert().accept();
    Thread.sleep(5000);

    
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }
}
