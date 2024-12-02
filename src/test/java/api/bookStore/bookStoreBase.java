package api.bookStore;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class bookStoreBase extends base {
    public String isbn;

    @Test
    public void getAllBook(){
        Response response = bookStore.getApi("/BookStore/v1/Books");
        System.out.println("Check response!");
        Assert.assertEquals(response.getStatusCode(), 200,"Response status not 200");

        isbn = response.path("books.isbn[0]");
    }

    @Test
    public void getOneBook(){
        bookStore.getApi("/BookStore/v1/Book?ISBN=",isbn);
    }

    @Test
    public void postOneBook(){
        Map<String, Object> request =  new HashMap<>();
        request.put("userId", "e01393d8-abab-42d0-83ee-b9087d323cac");
        request.put("isbn", "9781449325862");
        bookStore.postApi(request,"/BookStore/v1/Books");
    }
}
