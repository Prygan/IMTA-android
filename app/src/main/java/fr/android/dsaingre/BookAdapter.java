package fr.android.dsaingre;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Dimitri Saingre on 16/11/17.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private final List<Book> books;
    private final LayoutInflater inflater;
    private final BookItemListener listener;

    public BookAdapter(LayoutInflater inflater, List<Book> books, BookItemListener listener) {
        this.books = books;
        this.inflater = inflater;
        this.listener = listener;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookViewHolder(this.inflater.inflate(R.layout.book_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        ((BookItemView)holder.itemView).bindView(books.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder {
        public BookViewHolder(View itemView) {
            super(itemView);
        }
    }
}
