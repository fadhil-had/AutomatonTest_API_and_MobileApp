package apk.test;
import io.appium.java_client.remote.options.BaseOptions;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class register {

    private AndroidDriver driver;
    Random rand = new Random();

    String name = "Test" + rand.nextInt(999);
    String password = name + rand.nextInt(999);

    @BeforeEach
    public void setUp() throws MalformedURLException {
        var options = new BaseOptions()
                .amend("appium:automationName", "UiAutomator2")
                .amend("appium:platformName", "Android")
                .amend("appium:platformVersion", "5.0.2")
                .amend("appium:deviceName", "emulator-5554")
                .amend("appium:app", "D:\\Code\\Test\\Pintu\\TestPintu\\src\\test\\resources\\app\\Sample Android App - Login Tes_4.0_APKPure.apk")
                .amend("appium:newCommandTimeout", 3600)
                .amend("appium:connectHardwareKeyboard", true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
    }

    @Test
    public void positiveCaseRegister() {
        driver.findElement(By.id("com.loginmodule.learning:id/textViewLinkRegister")).click();
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextName")).sendKeys(name);
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextEmail")).sendKeys(name+"@gmail.com");
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextPassword")).sendKeys(password);
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextConfirmPassword")).sendKeys(password);
        driver.findElement(By.id("com.loginmodule.learning:id/appCompatButtonRegister")).click();


        WebElement bodyText = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.loginmodule.learning:id/snackbar_text\"]"));
        Assert.assertTrue(bodyText.isDisplayed());
        Assert.assertTrue(bodyText.getText().contains("Registration Successful"), "Text not found!");
    }

    @Test
    public void negativeCaseRegister1() {
        System.out.println("Case Negative 1: User null");
        driver.findElement(By.id("com.loginmodule.learning:id/textViewLinkRegister")).click();
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextName")).sendKeys("");
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextEmail")).sendKeys(name+"@gmail.com");
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextPassword")).sendKeys(password);
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextConfirmPassword")).sendKeys(password);
        driver.findElement(By.id("com.loginmodule.learning:id/appCompatButtonRegister")).click();

        WebElement rxBtn = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Enter Full Name\"]"));
        Assert.assertTrue(rxBtn.isDisplayed());
    }

    @Test
    public void negativeCaseRegister2() {
        System.out.println("Case Negative 2: Email null");
        driver.findElement(By.id("com.loginmodule.learning:id/textViewLinkRegister")).click();
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextName")).sendKeys(name);
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextEmail")).sendKeys("");
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextPassword")).sendKeys(password);
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextConfirmPassword")).sendKeys(password);
        driver.findElement(By.id("com.loginmodule.learning:id/appCompatButtonRegister")).click();

        WebElement rxBtn = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Enter Valid Email\"]"));
        Assert.assertTrue(rxBtn.isDisplayed());
    }

    @Test
    public void negativeCaseRegister3() {
        System.out.println("Case Negative 3: Password null");
        driver.findElement(By.id("com.loginmodule.learning:id/textViewLinkRegister")).click();
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextName")).sendKeys(name);
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextEmail")).sendKeys(name+"@gmail.com");
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextPassword")).sendKeys("");
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextConfirmPassword")).sendKeys(password);
        driver.findElement(By.id("com.loginmodule.learning:id/appCompatButtonRegister")).click();

        WebElement rxBtn = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Enter Password\"]"));
        Assert.assertTrue(rxBtn.isDisplayed());
    }

    @Test
    public void negativeCaseRegister4() {
        System.out.println("Case Negative 4: Confirm password null");
        driver.findElement(By.id("com.loginmodule.learning:id/textViewLinkRegister")).click();
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextName")).sendKeys(name);
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextEmail")).sendKeys(name+"@gmail.com");
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextPassword")).sendKeys(password);
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextConfirmPassword")).sendKeys();
        driver.findElement(By.id("com.loginmodule.learning:id/appCompatButtonRegister")).click();

        WebElement rxBtn = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Password Does Not Matches\"]"));
        Assert.assertTrue(rxBtn.isDisplayed());
    }

    @Test
    public void negativeCaseRegister5() {
        System.out.println("Case Negative 5: Email invalid");
        driver.findElement(By.id("com.loginmodule.learning:id/textViewLinkRegister")).click();
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextName")).sendKeys(name);
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextEmail")).sendKeys(name);
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextPassword")).sendKeys(password);
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextConfirmPassword")).sendKeys(password);
        driver.findElement(By.id("com.loginmodule.learning:id/appCompatButtonRegister")).click();

        WebElement rxBtn = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Enter Valid Email\"]"));
        Assert.assertTrue(rxBtn.isDisplayed());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
