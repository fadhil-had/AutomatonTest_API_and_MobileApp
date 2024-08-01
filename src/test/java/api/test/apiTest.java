package api.test;

import io.restassured.response.Response;
import org.example.baseRequest;

public class apiTest extends baseRequest{
    private static final String url = "https://jsonplaceholder.typicode.com/posts";

    public static Response getData() {
        return getAPI(url);
    }
}
