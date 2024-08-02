package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.StringDescription;

import java.io.InputStream;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;

import java.util.Map;

public class baseRequest {
    static Logger logger = Logger.getLogger(baseRequest.class.getName());

    private static RequestSpecification requestRewrite() {
        return given()
                .contentType(ContentType.JSON);
    }

    private static RequestSpecification requestRewrite(Map<String, Object> payload) {
        return given()
                .contentType(ContentType.JSON)
                .body(payload);
    }

    public static Response getAPI(String endPoint) {
        RequestSpecification request = requestRewrite();
        Response response = given()
                .when()
                .get(endPoint);
        return response;
    }

    public static Response postAPI(Map<String, Object> payload, String endPoint) {
        RequestSpecification request = requestRewrite(payload);
        Response response = request.body(payload)
                .when()
                .post(endPoint);
        printReport(request);
        printResponse(response);
        return response;
    }

    private static void printReport(RequestSpecification requestSpecification) {
        QueryableRequestSpecification queryDetail = SpecificationQuerier.query(requestSpecification);
        System.out.println("Endpoint is "+ queryDetail.getBaseUri());
        System.out.println("Method is "+ queryDetail.getMethod());
    }

    public static void printResponse(Response response) {
        System.out.println("Response status is "+ response.getStatusCode());
        System.out.println("Response body is ");
        response.getBody().prettyPrint();
    }
    
    public static JsonNode getJson (String url) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;

        {
            try {
                jsonNode = objectMapper.readTree(inputStreamFromClasspath(url));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return jsonNode;
    }

    public static JsonNode convertJson (String response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(response);
    }

    public static InputStream inputStreamFromClasspath(String path) {

        // returning stream
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    }

    public static void checkSchemaTrue(Response response, String url) {
        MatcherAssert.assertThat(
                "Validate json schema",
                response.getBody().asString(),
                JsonSchemaValidator.matchesJsonSchemaInClasspath(url)
        );
        System.out.println("Check response schema success!");
    }

    public static Map generatePayload(String url) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Random rand = new Random();

        JsonNode jsonNode = objectMapper.readTree(inputStreamFromClasspath(url));
        ((ObjectNode)jsonNode).put("title", RandomStringUtils.randomAlphabetic(5));
        ((ObjectNode)jsonNode).put("body", RandomStringUtils.randomAlphabetic(5));
        ((ObjectNode)jsonNode).put("userId", rand.nextInt(99));

        return objectMapper.convertValue(jsonNode, Map.class);
    }

    public static Map generateResponseChecker(String url) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Random rand = new Random();

        JsonNode jsonNode = objectMapper.readTree(inputStreamFromClasspath(url));
        ((ObjectNode)jsonNode).put("title", RandomStringUtils.randomAlphabetic(5));
        ((ObjectNode)jsonNode).put("body", RandomStringUtils.randomAlphabetic(5));
        ((ObjectNode)jsonNode).put("userId", rand.nextInt(99));

        return objectMapper.convertValue(jsonNode, Map.class);
    }
}