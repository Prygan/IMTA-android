package fr.android.dsaingre;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class FragmentBookDetail extends Fragment {
    private Book book;
    private TextView bookTitleTextDetailView;
    private TextView bookPriceTextDetailView;
    private TextView bookDescriptionTextDetailView;
    private TextView bookIsbnTextDetailView;

    private ImageView bookCoverImageDetailView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_detail, container, false);

        book = (Book) this.getArguments().getParcelable("BOOK");

        this.bookTitleTextDetailView = view.findViewById(R.id.bookTitleTextDetailView);
        this.bookCoverImageDetailView = view.findViewById(R.id.bookCoverImageDetailView);
        this.bookPriceTextDetailView = view.findViewById(R.id.bookPriceTextDetailView);
        this.bookDescriptionTextDetailView = view.findViewById(R.id.bookDescriptionTextDetailView);
        this.bookIsbnTextDetailView = view.findViewById(R.id.bookIsbnTextDetailView);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(book != null) {
            StringBuilder sb = new StringBuilder();

            for(String s: book.getSynopsis()) {
                sb.append(s + "\n");
            }

            bookTitleTextDetailView.setText(book.getTitle());
            bookIsbnTextDetailView.setText("ISBN : " + book.getIsbn());
            bookPriceTextDetailView.setText("Prix : " + book.getPrice() + "€");
            bookDescriptionTextDetailView.setText(sb.toString());

            Glide.with(view.getContext())
                    .load(book.getCover())
                    .into(bookCoverImageDetailView);
        }
        else {
            bookTitleTextDetailView.setText("No book selected :)");
            bookTitleTextDetailView.setText("");
            bookIsbnTextDetailView.setText("");
            bookPriceTextDetailView.setText("");
            bookDescriptionTextDetailView.setText("");
            bookCoverImageDetailView.setImageResource(0);
        }
    }

}
