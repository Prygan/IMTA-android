package fr.android.androidexercises;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import timber.log.Timber;

/**
 * Created by Dimitri Saingre on 16/11/17.
 */

public class BookItemView extends LinearLayout {
    private TextView bookTitleTextView;
    private TextView bookPriceTextView;
    private ImageView bookCoverImageView;

    public BookItemView(Context context) {
        this(context, null);
    }

    public BookItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BookItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.bookTitleTextView = findViewById(R.id.bookTitleTextView);
        this.bookCoverImageView = findViewById(R.id.bookCoverImageView);
        this.bookPriceTextView = findViewById(R.id.bookPriceTextView);
    }

    public void bindView(final Book book, final BookItemListener listener) {
        bookTitleTextView.setText(book.getTitle());
        bookPriceTextView.setText(book.getPrice() + "€");
        Glide.with(this)
                .load(book.getCover())
                .into(bookCoverImageView);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onBookItemClick(book);
            }
        });
    }
}
