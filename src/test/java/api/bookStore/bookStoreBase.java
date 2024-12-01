package api.bookStore;

import io.restassured.response.Response;
import org.example.baseRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class bookStoreBase {
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
        bookStore.getApi("/BookStore/v1/Book?ISBN=%s",isbn);
    }
}
