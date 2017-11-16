package fr.android.androidexercises;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class LibraryActivity extends AppCompatActivity implements fragmentBookList.OnNextListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerFrameLayout, new fragmentBookList(), fragmentBookList.class.getSimpleName())
                .commit();
    }

    @Override
    public void onNext() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerFrameLayout, new fragmentBookDetail(), fragmentBookDetail.class.getSimpleName())
                .addToBackStack(fragmentBookDetail.class.getSimpleName())
                .commit();
    }

}
