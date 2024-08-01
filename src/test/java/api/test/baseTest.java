package api.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.example.baseRequest;

public class baseTest {
    String url = "src/test/java/api/test/jsonCheck/responseData.json";

    @Test
    public void validateAllData() throws JsonProcessingException {
        Response response = apiTest.getData();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
