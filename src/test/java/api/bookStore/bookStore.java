package api.bookStore;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class bookStore {
    private static final String urlDefault = "https://bookstore.toolsqa.com";
    private String payLoad;

    public static Response getApi(String url){
        return given().
                accept(ContentType.JSON).
                when().get(url).
                prettyPeek();
    }

    public static Response getApi(String url, String param){
        return given().accept(ContentType.JSON).when().get(url+param).prettyPeek();
    }

    public static Response postApi(Map<String, Object> payload, String endPoint, String token) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .and()
                .header("Authorization", "Bearer " + token)
                .body(payload)
                .when()
                .post(urlDefault+endPoint)
                .prettyPeek();
    }

    public static Response postApi(Map<String, Object> payload, String endPoint) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .and()
                .body(payload)
                .when()
                .post(urlDefault+endPoint)
                .prettyPeek();
    }
}
