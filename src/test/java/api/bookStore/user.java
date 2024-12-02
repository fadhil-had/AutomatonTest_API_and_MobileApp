package api.bookStore;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class user {
    public Faker faker = new Faker();

    public String username = faker.name().username();
    public String password = faker.internet().password(8,16,true,true,true);

    public String userId;

    @Test
    public void addNewUser(){
        Map<String, Object> request =  new HashMap<>();
        request.put("userName", username);
        request.put("password", password);
        Response response = bookStore.postApi(request,"/Account/v1/User");

        userId = response.path("userID");
    }
}
