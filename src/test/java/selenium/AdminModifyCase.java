package selenium;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class AdminModifyCase {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
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
  public void testAdminModifyCase() throws Exception {
    driver.get(baseUrl + "/UBUassistant/index.jsp");
    driver.findElement(By.cssSelector("input.adminLink")).click();
    TimeUnit.MILLISECONDS.sleep(2000);
    driver.findElement(By.id("user")).clear();
    driver.findElement(By.id("user")).sendKeys("admin1@ubu.es");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("admin1");
    driver.findElement(By.cssSelector("button")).click();
    TimeUnit.MILLISECONDS.sleep(2000);
    driver.findElement(By.linkText("Editor de Casos")).click();
    TimeUnit.MILLISECONDS.sleep(2000);
    driver.findElement(By.id("add")).click();
    driver.findElement(By.id("keyWord1")).clear();
    driver.findElement(By.id("keyWord1")).sendKeys("CasoPrueba");
    driver.findElement(By.id("categoria")).clear();
    driver.findElement(By.id("categoria")).sendKeys("CategoriaPrueba");
    driver.findElement(By.id("respuesta")).clear();
    driver.findElement(By.id("respuesta")).sendKeys("RespuestaPrueba");
    driver.findElement(By.cssSelector("input.aceptButton")).click();
    TimeUnit.MILLISECONDS.sleep(2000);
    driver.findElement(By.linkText("Editor de Casos")).click();
    driver.findElement(By.id("edit")).click();
    
    int rowCount=driver.findElements(By.xpath("//table[@id='tabla']/tbody/tr")).size();
    
    TimeUnit.MILLISECONDS.sleep(2000);
    JavascriptExecutor jse = (JavascriptExecutor)driver;
    jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    TimeUnit.MILLISECONDS.sleep(2000);
    
    driver.findElement(By.xpath("(//input[@id='button'])["+((rowCount*2)-1)+"]")).click();
    driver.findElement(By.id("keyWord1")).clear();
    driver.findElement(By.id("keyWord1")).sendKeys("CasoPruebaEdit");
    driver.findElement(By.id("keyWord2")).clear();
    driver.findElement(By.id("keyWord2")).sendKeys("Caso2Edit");
    driver.findElement(By.id("keyWord5")).clear();
    driver.findElement(By.id("keyWord5")).sendKeys("Caso5Edit");
    driver.findElement(By.id("categoria")).clear();
    driver.findElement(By.id("categoria")).sendKeys("CategoriaEditada");
    driver.findElement(By.id("respuesta")).clear();
    driver.findElement(By.id("respuesta")).sendKeys("RespuestaEditada");
    driver.findElement(By.cssSelector("input.aceptButton")).click();
    TimeUnit.MILLISECONDS.sleep(2000);
    assertEquals("CasoPruebaEdit", driver.findElement(By.xpath("//table[@id='tabla']/tbody/tr["+rowCount+"]/td[2]")).getText());
    assertEquals("Caso2Edit", driver.findElement(By.xpath("//table[@id='tabla']/tbody/tr["+rowCount+"]/td[3]")).getText());
    assertEquals("Caso5Edit", driver.findElement(By.xpath("//table[@id='tabla']/tbody/tr["+rowCount+"]/td[6]")).getText());
    assertEquals("CategoriaEditada", driver.findElement(By.xpath("//table[@id='tabla']/tbody/tr["+rowCount+"]/td[7]")).getText());
    assertEquals("RespuestaEditada", driver.findElement(By.xpath("//table[@id='tabla']/tbody/tr["+rowCount+"]/td[8]")).getText());
    
    TimeUnit.MILLISECONDS.sleep(2000);
    jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    TimeUnit.MILLISECONDS.sleep(2000);
    
    driver.findElement(By.xpath("(//input[@id='button'])["+((rowCount*2)-1)+"]")).click();
    TimeUnit.MILLISECONDS.sleep(2000);
    driver.findElement(By.id("keyWord1")).clear();
    driver.findElement(By.id("keyWord1")).sendKeys("");
    driver.findElement(By.cssSelector("input.aceptButton")).click();
    TimeUnit.MILLISECONDS.sleep(2000);
    assertTrue(driver.findElement(By.id("error")).getText().equals("*Debe rellenar al menos la palabra clave 1, la categoría y la respuesta."));
    driver.findElement(By.id("keyWord1")).clear();
    driver.findElement(By.id("keyWord1")).sendKeys("CasoPruebaEdit");
    driver.findElement(By.id("categoria")).clear();
    driver.findElement(By.id("categoria")).sendKeys("");
    driver.findElement(By.cssSelector("input.aceptButton")).click();
    TimeUnit.MILLISECONDS.sleep(2000);
    assertTrue(driver.findElement(By.id("error")).getText().equals("*Debe rellenar al menos la palabra clave 1, la categoría y la respuesta."));
    driver.findElement(By.id("categoria")).clear();
    driver.findElement(By.id("categoria")).sendKeys("CategoriaEditada");
    driver.findElement(By.id("respuesta")).clear();
    driver.findElement(By.id("respuesta")).sendKeys("");
    driver.findElement(By.cssSelector("input.aceptButton")).click();
    TimeUnit.MILLISECONDS.sleep(2000);
    assertTrue(driver.findElement(By.id("error")).getText().equals("*Debe rellenar al menos la palabra clave 1, la categoría y la respuesta."));
    driver.findElement(By.linkText("Editor de Casos")).click();
    TimeUnit.MILLISECONDS.sleep(2000);
    driver.findElement(By.id("edit")).click();
    TimeUnit.MILLISECONDS.sleep(2000);
    
    TimeUnit.MILLISECONDS.sleep(2000);
    jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    TimeUnit.MILLISECONDS.sleep(2000);
    
    driver.findElement(By.xpath("(//input[@id='button'])["+(rowCount*2)+"]")).click();
    TimeUnit.MILLISECONDS.sleep(2000);
    assertTrue(closeAlertAndGetItsText().matches("^¿Está seguro de que desea borrar el caso[\\s\\S]$"));
    TimeUnit.MILLISECONDS.sleep(5000);
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
