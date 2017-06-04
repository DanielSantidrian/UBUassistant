package selenium;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class ReservedAndSimpleAnswer {
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
  public void testReservedAndSimpleAnswer() throws Exception {
    driver.get(baseUrl + "/UBUassistant/index.jsp");
    driver.findElement(By.id("pinguino")).click();
    Thread.sleep(2000);
    
    /***/
    driver.switchTo().frame(driver.findElement(By.id("ubuassistantFrame")));
    /***/
    
    driver.findElement(By.id("user-input")).clear();
    driver.findElement(By.id("user-input")).sendKeys("Hola");
    driver.findElement(By.id("enviar")).click();
    Thread.sleep(2000);
    assertEquals("Hola, estoy preparada para responder, adelánte", driver.findElement(By.xpath("//div[@id='chat-output']/div[3]/div")).getText());
    driver.findElement(By.id("user-input")).clear();
    driver.findElement(By.id("user-input")).sendKeys("Callate");
    driver.findElement(By.id("enviar")).click();
    Thread.sleep(2000);
    assertEquals("Lo siento, solo intentaba ayudar", driver.findElement(By.xpath("//div[@id='chat-output']/div[5]/div")).getText());
    driver.findElement(By.id("user-input")).clear();
    driver.findElement(By.id("user-input")).sendKeys("deporte");
    driver.findElement(By.id("enviar")).click();
    Thread.sleep(2000);
    assertTrue(isElementPresent(By.linkText("http://www.ubu.es/deportes")));
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
