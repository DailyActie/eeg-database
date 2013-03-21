package cz.zcu.kiv.eegdatabase.selenium.webdriver;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ChangePasswordNS {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = Config.BASE_URL;
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testChangePasswordNS() throws Exception {
    driver.get(baseUrl + "/home-page?0");

    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*No user logged[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("//input[@wicketpath='login_userName']")).clear();
    driver.findElement(By.xpath("//input[@wicketpath='login_userName']")).sendKeys("testaccountforeeg2@seznam.cz");
    driver.findElement(By.xpath("//input[@wicketpath='login_password']")).clear();
    driver.findElement(By.xpath("//input[@wicketpath='login_password']")).sendKeys("123456");
    driver.findElement(By.xpath("//input[@wicketpath='login_submit']")).click();

    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Logged user: testaccountforeeg2@seznam\\.cz[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("//span[@wicketpath='userHeaderLink_linkLabel']")).click();

    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Account overview[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("//a[@wicketpath='leftMenu_menu_1_link']")).click();

    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Change password[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("//input[@wicketpath='form_submit']")).click();

    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Field 'New password' is required\\.[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }

    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Field 'New password' is required\\.[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }

    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Field 'New password again' is required\\.[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("//input[@wicketpath='form_oldPassword']")).clear();
    driver.findElement(By.xpath("//input[@wicketpath='form_oldPassword']")).sendKeys("123456");
    driver.findElement(By.xpath("//input[@wicketpath='form_newPassword']")).clear();
    driver.findElement(By.xpath("//input[@wicketpath='form_newPassword']")).sendKeys("123");
    driver.findElement(By.xpath("//input[@wicketpath='form_verPassword']")).clear();
    driver.findElement(By.xpath("//input[@wicketpath='form_verPassword']")).sendKeys("123");
    driver.findElement(By.xpath("//input[@wicketpath='form_submit']")).click();

    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*'New password' is shorter than the minimum of 6 characters\\.[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }

    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*'New password again' is shorter than the minimum of 6 characters\\.[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("//input[@wicketpath='form_oldPassword']")).clear();
    driver.findElement(By.xpath("//input[@wicketpath='form_oldPassword']")).sendKeys("123456");
    driver.findElement(By.xpath("//input[@wicketpath='form_newPassword']")).clear();
    driver.findElement(By.xpath("//input[@wicketpath='form_newPassword']")).sendKeys("");
    driver.findElement(By.xpath("//input[@wicketpath='form_verPassword']")).clear();
    driver.findElement(By.xpath("//input[@wicketpath='form_verPassword']")).sendKeys("");
    driver.findElement(By.xpath("//input[@wicketpath='form_submit']")).click();

    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Field 'New password' is required\\.[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }

    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Field 'New password again' is required\\.[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("//input[@wicketpath='form_newPassword']")).clear();
    driver.findElement(By.xpath("//input[@wicketpath='form_newPassword']")).sendKeys("");
    driver.findElement(By.xpath("//input[@wicketpath='form_verPassword']")).clear();
    driver.findElement(By.xpath("//input[@wicketpath='form_verPassword']")).sendKeys("");
    driver.findElement(By.xpath("//input[@wicketpath='form_submit']")).click();

    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Field 'New password' is required\\.[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }

    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Field 'New password again' is required\\.[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("//input[@wicketpath='form_oldPassword']")).clear();
    driver.findElement(By.xpath("//input[@wicketpath='form_oldPassword']")).sendKeys("654321");
    driver.findElement(By.xpath("//input[@wicketpath='form_newPassword']")).clear();
    driver.findElement(By.xpath("//input[@wicketpath='form_newPassword']")).sendKeys("654321");
    driver.findElement(By.xpath("//input[@wicketpath='form_verPassword']")).clear();
    driver.findElement(By.xpath("//input[@wicketpath='form_verPassword']")).sendKeys("654321");
    driver.findElement(By.xpath("//input[@wicketpath='form_submit']")).click();

    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Inserted password doesn't match current password\\.[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("//input[@wicketpath='form_oldPassword']")).clear();
    driver.findElement(By.xpath("//input[@wicketpath='form_oldPassword']")).sendKeys("");
    driver.findElement(By.xpath("//input[@wicketpath='form_newPassword']")).clear();
    driver.findElement(By.xpath("//input[@wicketpath='form_newPassword']")).sendKeys("654321");
    driver.findElement(By.xpath("//input[@wicketpath='form_verPassword']")).clear();
    driver.findElement(By.xpath("//input[@wicketpath='form_verPassword']")).sendKeys("654321");
    driver.findElement(By.xpath("//input[@wicketpath='form_submit']")).click();

    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Field 'Current password' is required\\.[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("//input[@wicketpath='form_oldPassword']")).clear();
    driver.findElement(By.xpath("//input[@wicketpath='form_oldPassword']")).sendKeys("123456");
    driver.findElement(By.xpath("//input[@wicketpath='form_newPassword']")).clear();
    driver.findElement(By.xpath("//input[@wicketpath='form_newPassword']")).sendKeys("");
    driver.findElement(By.xpath("//input[@wicketpath='form_verPassword']")).clear();
    driver.findElement(By.xpath("//input[@wicketpath='form_verPassword']")).sendKeys("654321");
    driver.findElement(By.xpath("//input[@wicketpath='form_submit']")).click();

    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Field 'New password' is required\\.[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("//input[@wicketpath='form_oldPassword']")).clear();
    driver.findElement(By.xpath("//input[@wicketpath='form_oldPassword']")).sendKeys("123456");
    driver.findElement(By.xpath("//input[@wicketpath='form_newPassword']")).clear();
    driver.findElement(By.xpath("//input[@wicketpath='form_newPassword']")).sendKeys("654321");
    driver.findElement(By.xpath("//input[@wicketpath='form_verPassword']")).clear();
    driver.findElement(By.xpath("//input[@wicketpath='form_verPassword']")).sendKeys("");
    driver.findElement(By.xpath("//input[@wicketpath='form_submit']")).click();

    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Field 'New password again' is required\\.[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("//input[@wicketpath='form_oldPassword']")).clear();
    driver.findElement(By.xpath("//input[@wicketpath='form_oldPassword']")).sendKeys("123456");
    driver.findElement(By.xpath("//input[@wicketpath='form_newPassword']")).clear();
    driver.findElement(By.xpath("//input[@wicketpath='form_newPassword']")).sendKeys("123456");
    driver.findElement(By.xpath("//input[@wicketpath='form_verPassword']")).clear();
    driver.findElement(By.xpath("//input[@wicketpath='form_verPassword']")).sendKeys("123456");
    driver.findElement(By.xpath("//input[@wicketpath='form_submit']")).click();

    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*New password is the same as the old one\\.[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("//input[@wicketpath='form_oldPassword']")).clear();
    driver.findElement(By.xpath("//input[@wicketpath='form_oldPassword']")).sendKeys("123456");
    driver.findElement(By.xpath("//input[@wicketpath='form_newPassword']")).clear();
    driver.findElement(By.xpath("//input[@wicketpath='form_newPassword']")).sendKeys("123456");
    driver.findElement(By.xpath("//input[@wicketpath='form_verPassword']")).clear();
    driver.findElement(By.xpath("//input[@wicketpath='form_verPassword']")).sendKeys("654321");
    driver.findElement(By.xpath("//input[@wicketpath='form_submit']")).click();

    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Inserted passwords don't match\\.[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("//a[@wicketpath='logout']")).click();

    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*No user logged[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
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

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alert.getText();
    } finally {
      acceptNextAlert = true;
    }
  }
}