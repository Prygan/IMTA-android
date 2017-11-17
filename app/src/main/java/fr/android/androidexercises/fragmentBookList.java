package fr.android.androidexercises;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class fragmentBookList extends Fragment {
    private OnNextListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnNextListener)context;
    }

    @Nullable
    @Override
    public View onCreateView(final  LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://henri-potier.xebia.fr/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookService service = retrofit.create(BookService.class);

        Call<List<Book>> listCall = service.listBooks();

        listCall.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                List<Book> books = response.body();

                for(Book b: books) {
                    Timber.d(b.getTitle());
                }

                RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.bookListView);
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
                recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
                recyclerView.setAdapter(new BookAdapter(LayoutInflater.from(getContext()), books));
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Timber.e(t);
            }
        });
    }

    public interface OnNextListener {
        void onNext();
    }
}
