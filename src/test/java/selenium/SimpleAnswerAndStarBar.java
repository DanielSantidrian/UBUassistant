package selenium;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class SimpleAnswerAndStarBar {
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
  public void testSimpleAnswerAndStarBar() throws Exception {
	  
	driver.get(baseUrl + "/UBUassistant/");
    driver.findElement(By.id("pinguino")).click();
    TimeUnit.MILLISECONDS.sleep(2000);
    
    /***/
    driver.switchTo().frame(driver.findElement(By.id("ubuassistantFrame")));
    /***/
    
    driver.findElement(By.id("user-input")).clear();
    driver.findElement(By.id("user-input")).sendKeys("esqui");
    driver.findElement(By.id("enviar")).click();
    TimeUnit.MILLISECONDS.sleep(2000);
    assertTrue(isElementPresent(By.linkText("http://www.ubu.es/deportes")));
    driver.findElement(By.xpath("//form[@id='starForm']/div/label[5]")).click();
    TimeUnit.MILLISECONDS.sleep(2000);
    assertEquals("Voto guardado con éxito. Su voto ha sido 1", driver.findElement(By.id("buttonPanel")).getText());
    driver.findElement(By.id("user-input")).clear();
    driver.findElement(By.id("user-input")).sendKeys("esqui");
    driver.findElement(By.id("enviar")).click();
    TimeUnit.MILLISECONDS.sleep(2000);
    driver.findElement(By.xpath("//form[@id='starForm']/div/label[4]")).click();
    TimeUnit.MILLISECONDS.sleep(2000);
    assertEquals("Voto guardado con éxito. Su voto ha sido 2", driver.findElement(By.id("buttonPanel")).getText());
    driver.findElement(By.id("user-input")).clear();
    driver.findElement(By.id("user-input")).sendKeys("esqui");
    driver.findElement(By.id("enviar")).click();
    TimeUnit.MILLISECONDS.sleep(2000);
    driver.findElement(By.xpath("//form[@id='starForm']/div/label[3]")).click();
    TimeUnit.MILLISECONDS.sleep(2000);
    assertEquals("Voto guardado con éxito. Su voto ha sido 3", driver.findElement(By.id("buttonPanel")).getText());
    driver.findElement(By.id("user-input")).clear();
    driver.findElement(By.id("user-input")).sendKeys("esqui");
    driver.findElement(By.id("enviar")).click();
    TimeUnit.MILLISECONDS.sleep(2000);
    driver.findElement(By.xpath("//form[@id='starForm']/div/label[2]")).click();
    TimeUnit.MILLISECONDS.sleep(2000);
    assertEquals("Voto guardado con éxito. Su voto ha sido 4", driver.findElement(By.id("buttonPanel")).getText());
    driver.findElement(By.id("user-input")).clear();
    driver.findElement(By.id("user-input")).sendKeys("esqui");
    driver.findElement(By.id("enviar")).click();
    TimeUnit.MILLISECONDS.sleep(2000);
    driver.findElement(By.cssSelector("label[title=\"text\"]")).click();
    TimeUnit.MILLISECONDS.sleep(2000);
    assertEquals("Voto guardado con éxito. Su voto ha sido 5", driver.findElement(By.id("buttonPanel")).getText());
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
