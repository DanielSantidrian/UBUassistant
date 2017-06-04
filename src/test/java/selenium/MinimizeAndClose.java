package selenium;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class MinimizeAndClose {
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
  }

  @Test
  public void testMinimizeAndClose() throws Exception {
	  
    driver.get(baseUrl + "/UBUassistant/index.jsp");
    driver.findElement(By.id("pinguino")).click();
    Thread.sleep(2000);
    assertTrue(isElementPresent(By.id("divchat-window")));
    driver.findElement(By.cssSelector("button.btn-minimize")).click();
    Thread.sleep(2000);
    assertEquals("+", driver.findElement(By.cssSelector("button.btn-minimize")).getText());
    driver.findElement(By.cssSelector("button.btn-minimize")).click();
    Thread.sleep(2000);

    /***/
    driver.switchTo().frame(driver.findElement(By.id("ubuassistantFrame")));
    /***/
    
    driver.findElement(By.id("user-input")).sendKeys("becas internacionales");
    driver.findElement(By.id("enviar")).click();
    Thread.sleep(3000);
    
    /***/
    driver.switchTo().defaultContent();
    /***/
    
    driver.findElement(By.cssSelector("button.btn-minimize")).click();
    Thread.sleep(2000);
    driver.findElement(By.cssSelector("button.btn-minimize")).click();
    Thread.sleep(2000);
    
    /***/
    driver.switchTo().frame(driver.findElement(By.id("ubuassistantFrame")));
    /***/
    
    assertTrue(isElementPresent(By.linkText("http://www.ubu.es/becas-de-cooperacion")));
    assertTrue(isElementPresent(By.xpath("//div[@id='chat-output']/div[3]/div")));
    
    /***/
    driver.switchTo().defaultContent();
    /***/
    
    driver.findElement(By.cssSelector("button.btn-close")).click();
    Thread.sleep(2000);
    assertTrue(isElementPresent(By.id("pinguino")));
    driver.findElement(By.id("pinguino")).click();
    Thread.sleep(2000);
    
    /***/
    driver.switchTo().frame(driver.findElement(By.id("ubuassistantFrame")));
    /***/
    
    assertFalse(isElementPresent(By.xpath("//div[@id='chat-output']/div[3]/div")));
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
