package selenium;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class EnterAdminPage {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.chrome.driver", ".\\rsc\\chromedriver.exe");
	driver = new ChromeDriver();
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testEnterAdminPage() throws Exception {

    driver.get(baseUrl + "/UBUassistant/index.jsp");
    driver.findElement(By.cssSelector("input.adminLink")).click();
    Thread.sleep(2000);
    driver.findElement(By.id("volver")).click();
    Thread.sleep(2000);
    assertEquals("Un asistente que te ayudará con cualquier duda sobre la UBU", driver.findElement(By.cssSelector("div.inner > strong")).getText());
    driver.findElement(By.cssSelector("input.adminLink")).click();
    Thread.sleep(2000);
    driver.findElement(By.cssSelector("button")).click();
    Thread.sleep(2000);
    assertTrue(driver.findElement(By.id("error")).getText().equals("* Debe rellenar todos los campos"));
    driver.findElement(By.id("user")).clear();
    driver.findElement(By.id("user")).sendKeys("admin1@ubu.es");
    driver.findElement(By.cssSelector("button")).click();
    Thread.sleep(2000);
    assertTrue(driver.findElement(By.id("error")).getText().equals("* Debe rellenar todos los campos"));
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("aaaaa");
    driver.findElement(By.cssSelector("button")).click();
    Thread.sleep(2000);
    assertEquals("Usuario y/o contraseña no válidos", driver.findElement(By.id("error")).getText());
    driver.findElement(By.id("user")).clear();
    driver.findElement(By.id("user")).sendKeys("aaaaa");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("admin1");
    driver.findElement(By.cssSelector("button")).click();
    Thread.sleep(2000);
    driver.findElement(By.id("user")).clear();
    driver.findElement(By.id("user")).sendKeys("admin1@ubu.es");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("admin1");
    driver.findElement(By.cssSelector("button")).click();
    Thread.sleep(2000);
    assertEquals("Log de uso", driver.findElement(By.id("title")).getText());
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}
