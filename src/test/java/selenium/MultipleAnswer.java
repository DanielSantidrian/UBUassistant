package selenium;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class MultipleAnswer {
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
  public void testMultipleAnswer() throws Exception {
    driver.get(baseUrl + "/UBUassistant/index.jsp");
    driver.findElement(By.id("pinguino")).click();
    Thread.sleep(2000);
    
    /***/
    driver.switchTo().frame(driver.findElement(By.id("ubuassistantFrame")));
    /***/
    
    driver.findElement(By.id("user-input")).clear();
    driver.findElement(By.id("user-input")).sendKeys("becas");
    driver.findElement(By.id("enviar")).click();
    Thread.sleep(2000);
    assertEquals("Vaya, parece que tengo demasiadas respuestas.\nIntenta ser más concreto o selecciona alguna sugerencia.", driver.findElement(By.xpath("//div[@id='chat-output']/div[3]/div")).getText());
    assertEquals("Becas", driver.findElement(By.id("but")).getAttribute("value"));
    assertEquals("Becas internacionales", driver.findElement(By.xpath("(//input[@id='but'])[2]")).getAttribute("value"));
    driver.findElement(By.xpath("(//input[@id='but'])[2]")).click();
    Thread.sleep(2000);
    assertTrue(isElementPresent(By.linkText("http://www.ubu.es/becas-de-cooperacion")));
    driver.findElement(By.id("but")).click();
    Thread.sleep(2000);
    driver.findElement(By.cssSelector("label[title=\"text\"]")).click();
    Thread.sleep(2000);
    assertTrue(isElementPresent(By.id("but")));
    driver.findElement(By.id("but")).click();
    Thread.sleep(2000);
    assertTrue(isElementPresent(By.linkText("http://www.ubu.es/ayudas-y-becas")));
    driver.findElement(By.xpath("(//input[@id='but'])[2]")).click();
    Thread.sleep(2000);
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
