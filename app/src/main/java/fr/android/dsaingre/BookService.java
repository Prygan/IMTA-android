package fr.android.dsaingre;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by prygan on 16/11/17.
 */

public interface BookService {
    @GET("books")
    Call<List<Book>> listBooks();
}
