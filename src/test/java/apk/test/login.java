package apk.test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;

public class login {

    private AndroidDriver driver;

    static String name = "Fadhil-Pintu";
    static String email = "fadhilpintu@gmail.com";
    static String password = "fadhilpintu123";

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
    public void positiveCaseLogin() {
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextEmail")).sendKeys(name);
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextPassword")).sendKeys(name);
        driver.findElement(By.id("com.loginmodule.learning:id/appCompatButtonLogin")).click();

        WebElement rxBtn = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Android NewLine Learning\"]"));
        WebElement emailFront = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.loginmodule.learning:id/textViewName\" and @text=\"fadhilpintu@gmail.com\"]"));
        Assert.assertTrue(rxBtn.isDisplayed());
        Assert.assertTrue(emailFront.isDisplayed());

        WebElement nameElement = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.loginmodule.learning:id/textViewName\" and @text=\"Fadhil-Pintu\"]"));
        String names = nameElement.getText();
        Assert.assertTrue(nameElement.isDisplayed());
        Assert.assertEquals(name, names);

        WebElement emailElement = driver.findElement(By.id("com.loginmodule.learning:id/textViewEmail"));
        String emails = emailElement.getText();
        Assert.assertTrue(emailElement.isDisplayed());
        Assert.assertEquals(email, emails);

        WebElement passElement = driver.findElement(By.id("com.loginmodule.learning:id/textViewPassword"));
        String pass = passElement.getText();
        Assert.assertTrue(passElement.isDisplayed());
        Assert.assertEquals(password, pass);
    }

    @Test
    public void negativeCaseRegister1() {
        System.out.println("Case Negative 1: Email false");
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextEmail")).sendKeys("fadhil");
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextPassword")).sendKeys(name);
        driver.findElement(By.id("com.loginmodule.learning:id/appCompatButtonLogin")).click();

        WebElement rxBtn = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Enter Valid Email\"]"));
        Assert.assertTrue(rxBtn.isDisplayed());
    }

    @Test
    public void negativeCaseRegister2() {
        System.out.println("Case Negative 2: Password false");
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextEmail")).sendKeys(email);
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextPassword")).sendKeys("apanih");
        driver.findElement(By.id("com.loginmodule.learning:id/appCompatButtonLogin")).click();

        String bodyText = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.loginmodule.learning:id/snackbar_text\"]")).getText();
        Assert.assertTrue(bodyText.contains("Wrong Email or Password"), "Text not found!");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
