package api.bookStore;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class bookStore {
    private static String urlDefault = "https://bookstore.toolsqa.com";
    private String payLoad;

    public static Response getApi(String url){
        Response response = given().
                accept(ContentType.JSON).
                when().get(urlDefault+url).
                prettyPeek();
        return response;
    }

    public static Response getApi(String url, String param){
        Response response = given().accept(ContentType.JSON).when().get(urlDefault+url+String.format("?ISBN=%s",param)).prettyPeek();
        return response;
    }
}
