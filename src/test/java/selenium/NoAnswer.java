package selenium;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class NoAnswer {
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
  public void testNoAnswer() throws Exception {
    driver.get(baseUrl + "/UBUassistant/index.jsp");
    driver.findElement(By.id("pinguino")).click();
    TimeUnit.MILLISECONDS.sleep(2000);
    
    /***/
    driver.switchTo().frame(driver.findElement(By.id("ubuassistantFrame")));
    /***/
    
    driver.findElement(By.id("user-input")).clear();
    driver.findElement(By.id("user-input")).sendKeys("verve");
    driver.findElement(By.id("enviar")).click();
    TimeUnit.MILLISECONDS.sleep(2000);
    assertEquals("Lo siento no tengo respuestas a tu pregunta :(\nEcha un vistazo a las sugerencias de búsqueda", driver.findElement(By.xpath("//div[@id='chat-output']/div[3]/div")).getText());
    assertEquals("verano", driver.findElement(By.cssSelector("input.sugBut")).getAttribute("value"));
    assertTrue(isElementPresent(By.xpath("(//input[@value='nivel 0'])[2]")));
    assertTrue(isElementPresent(By.xpath("(//input[@value='matricula cursos verano'])[2]")));
    driver.findElement(By.cssSelector("input.sugBut")).click();
    TimeUnit.MILLISECONDS.sleep(2000);
    assertTrue(isElementPresent(By.linkText("http://www.ubu.es/cursos-de-verano-de-la-universidad-de-burgos")));
    assertEquals("Valora esta respuesta", driver.findElement(By.id("buttonPanelContent")).getText());
    driver.findElement(By.cssSelector("label[title=\"text\"]")).click();
    TimeUnit.MILLISECONDS.sleep(2000);
    assertEquals("Voto guardado con éxito. Su voto ha sido 5", driver.findElement(By.id("buttonPanel")).getText());
    driver.findElement(By.id("user-input")).clear();
    driver.findElement(By.id("user-input")).sendKeys("ccc");
    driver.findElement(By.id("enviar")).click();
    TimeUnit.MILLISECONDS.sleep(2000);
    driver.findElement(By.cssSelector("input.sugBut")).click();
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
