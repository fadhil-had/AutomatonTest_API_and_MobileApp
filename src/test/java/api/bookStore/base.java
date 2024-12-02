package api.bookStore;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;


public class base {
    public Faker faker = new Faker();

    @BeforeMethod
    public void setup() {
        RestAssured.baseURI = "https://bookstore.toolsqa.com";
    }
}
