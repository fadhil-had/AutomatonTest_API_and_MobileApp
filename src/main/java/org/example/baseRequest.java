package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;

import java.util.Map;

public class baseRequest {
    static Logger logger = Logger.getLogger(baseRequest.class.getName());

    private static RequestSpecification requestRewrite() {
        return given()
                .contentType(ContentType.JSON);
    }

    private static RequestSpecification requestRewrite(Map<String, Object> headers, Map<String, Object> payload) {
        return given()
                .contentType(ContentType.JSON)
                .body(payload);
    }

    public static Response getAPI(String endPoint) {
        RequestSpecification request = requestRewrite();
        Response response = given()
                .when()
                .get(endPoint);
        printResponse(response);
        return response;
    }

    public static Response postAPI(Map<String,Object>headers ,Map<String, Object> payload, String endPoint) {
        RequestSpecification request = requestRewrite(headers,payload);
        Response response = request.body(payload)
                .when()
                .post(endPoint);
        printReport(request);
        printResponse(response);
        return response;
    }

    private static void printReport(RequestSpecification requestSpecification) {
        QueryableRequestSpecification queryDetail = SpecificationQuerier.query(requestSpecification);
        logger.info("Endpoint is "+ queryDetail.getBaseUri());
        logger.info("Method is "+ queryDetail.getMethod());
    }

    private static void printResponse(Response response) {
        logger.info("Response status is "+ response.getStatusCode());
        logger.info("Response body is ");
        logger.info(response.getBody().prettyPrint());
        System.out.println("Response status is "+ response.getStatusCode());
        System.out.println("Response body is ");
        System.out.println(response.getBody().prettyPrint());
    }
    
    public static JSONObject getJson (String url) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;

        {
            try {
                jsonNode = objectMapper.readTree(new File(url));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        String updatedJsonStr = new ObjectMapper().writeValueAsString(jsonNode);
        return new JSONObject(updatedJsonStr);
    }
}