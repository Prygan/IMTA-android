package fr.android.dsaingre;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import timber.log.Timber;

public class LibraryActivity extends AppCompatActivity implements BookItemListener {
    private Book book;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        Timber.plant(new Timber.DebugTree());

        FragmentBookList fragmentBookList;

        if(savedInstanceState != null) {
            fragmentBookList = (FragmentBookList) getSupportFragmentManager().findFragmentByTag(FragmentBookList.class.getSimpleName());
            book = savedInstanceState.getParcelable("BOOK");
        } else {
            fragmentBookList = new FragmentBookList();
        }

        displayFragmentBookDetail();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerFrameLayout, fragmentBookList, FragmentBookList.class.getSimpleName())
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("BOOK", book);
    }

    @Override
    public void onBookItemClick(Book book) {
        this.book = book;

        Timber.i("clicked on " + book.getTitle());

        displayFragmentBookDetail();
    }

    private void displayFragmentBookDetail() {
        FragmentBookDetail fragmentBookDetail = new FragmentBookDetail();

        Bundle bundle = new Bundle();
        bundle.putParcelable("BOOK", book);
        fragmentBookDetail.setArguments(bundle);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.bookDetail, fragmentBookDetail, FragmentBookDetail.class.getSimpleName())
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerFrameLayout, fragmentBookDetail, FragmentBookDetail.class.getSimpleName())
                    .addToBackStack("FromList")
                    .commit();
        }
    }

}
