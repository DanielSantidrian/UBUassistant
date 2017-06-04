package selenium;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class AdminAddCase {
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
  public void testAdminAddCase() throws Exception {
    driver.get(baseUrl + "/UBUassistant/index.jsp");
    driver.findElement(By.cssSelector("input.adminLink")).click();
    Thread.sleep(2000);
    driver.findElement(By.id("user")).clear();
    driver.findElement(By.id("user")).sendKeys("admin1@ubu.es");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("admin1");
    driver.findElement(By.cssSelector("button")).click();
    Thread.sleep(2000);
    driver.findElement(By.linkText("Editor de Casos")).click();
    Thread.sleep(2000);
    driver.findElement(By.id("add")).click();
    Thread.sleep(2000);
    assertEquals("Formulario para añadir un caso a la base de datos.", driver.findElement(By.id("subtitle")).getText());
    driver.findElement(By.cssSelector("input.aceptButton")).click();
    Thread.sleep(2000);
    assertTrue(driver.findElement(By.id("error")).getText().equals("*Debe rellenar al menos la palabra clave 1, la categoría y la respuesta."));
    driver.findElement(By.id("keyWord2")).clear();
    driver.findElement(By.id("keyWord2")).sendKeys("CasoPrueba");
    driver.findElement(By.id("categoria")).clear();
    driver.findElement(By.id("categoria")).sendKeys("CategoriaPrueba");
    driver.findElement(By.cssSelector("input.aceptButton")).click();
    Thread.sleep(2000);
    assertTrue(driver.findElement(By.id("error")).getText().equals("*Debe rellenar al menos la palabra clave 1, la categoría y la respuesta."));
    driver.findElement(By.id("respuesta")).clear();
    driver.findElement(By.id("respuesta")).sendKeys("RespuestaPrueba");
    driver.findElement(By.cssSelector("input.aceptButton")).click();
    driver.findElement(By.id("keyWord1")).clear();
    driver.findElement(By.id("keyWord1")).sendKeys("CasoPrueba");
    driver.findElement(By.id("keyWord2")).clear();
    driver.findElement(By.id("keyWord2")).sendKeys("");
    driver.findElement(By.id("respuesta")).clear();
    driver.findElement(By.id("respuesta")).sendKeys("");
    driver.findElement(By.cssSelector("input.aceptButton")).click();
    Thread.sleep(2000);
    driver.findElement(By.id("respuesta")).clear();
    driver.findElement(By.id("respuesta")).sendKeys("RespuestaPrueba");
    driver.findElement(By.id("categoria")).clear();
    driver.findElement(By.id("categoria")).sendKeys("");
    driver.findElement(By.cssSelector("input.aceptButton")).click();
    Thread.sleep(2000);
    assertTrue(driver.findElement(By.id("error")).getText().equals("*Debe rellenar al menos la palabra clave 1, la categoría y la respuesta."));
    driver.findElement(By.id("categoria")).clear();
    driver.findElement(By.id("categoria")).sendKeys("CategoriaPrueba");
    driver.findElement(By.cssSelector("input.aceptButton")).click();
    Thread.sleep(2000);
    driver.findElement(By.linkText("Editor de Casos")).click();
    Thread.sleep(2000);
    driver.findElement(By.id("edit")).click();
    Thread.sleep(2000);
    int rowCount=driver.findElements(By.xpath("//table[@id='tabla']/tbody/tr")).size();
    assertEquals("CasoPrueba", driver.findElement(By.xpath("//table[@id='tabla']/tbody/tr["+rowCount+"]/td[2]")).getText());
    driver.findElement(By.xpath("(//input[@id='button'])["+(rowCount*2)+"]")).click();
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
}
