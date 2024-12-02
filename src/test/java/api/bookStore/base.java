package api.bookStore;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;


public class base {
    @BeforeTest
    public void setup() {
        RestAssured.baseURI = "https://bookstore.toolsqa.com";
    }
}
