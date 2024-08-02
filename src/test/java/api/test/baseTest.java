package api.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.example.baseRequest;

import java.io.IOException;
import java.util.Map;

public class baseTest {
    String url = "responseData.json";
    String urlSchema = "responseSchema.json";
    String urlRequest = "responseRequest.json";
    String urlRequestSchema = "responseRequestSchema.json";

    @Test
    public void getAllData() throws JsonProcessingException {
        Response response = apiTest.getData();
        JsonNode responseNode = baseRequest.convertJson(response.then().extract().asString());
        System.out.println("Check response!");
        baseRequest.printResponse(response);
        Assert.assertEquals(response.getStatusCode(), 200,"Response status not 200");
        Assert.assertEquals(baseRequest.getJson(url),responseNode,"Its not same");

        baseRequest.checkSchemaTrue(response, urlSchema);
    }

    @Test
    public void postData() throws IOException {
        Map payload = baseRequest.generatePayload(urlRequest);
        Response response = apiTest.postData(payload);
        Assert.assertEquals(response.getStatusCode(), 201,"Response status not 201");
        Assert.assertEquals(response.jsonPath().get("title"), payload.get("title"), "Title invalid!");
        Assert.assertEquals(response.jsonPath().get("body"), payload.get("body"),"Title invalid!");
        Assert.assertEquals(response.jsonPath().get("userId"), payload.get("userId"),"Title invalid!");
        Assert.assertNotNull(response.jsonPath().get("id"),"Id null!");
        baseRequest.checkSchemaTrue(response, urlRequestSchema);
    }
}