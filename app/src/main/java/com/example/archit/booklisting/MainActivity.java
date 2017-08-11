package com.example.archit.booklisting;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    EditText searchBox_et;
    ImageButton search_button;
    private BooksAdaptor mAdapter;

    private TextView mEmptyStateTextView;
    private static final int BOOK_LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);

        searchBox_et = (EditText) findViewById(R.id.searchBox_et);
        search_button = (ImageButton) findViewById(R.id.search_button);

        mAdapter = new BooksAdaptor(this, new ArrayList<Book>());

        final View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        ListView bookListView = (ListView) findViewById(R.id.list);
        bookListView.setAdapter(mAdapter);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        bookListView.setEmptyView(mEmptyStateTextView);

        final LoaderManager loaderManager = getLoaderManager();

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    loaderManager.restartLoader(BOOK_LOADER_ID, null, MainActivity.this);
                } else {

                    mEmptyStateTextView.setText(R.string.no_internet_connection);
                }
            }
        });
    }

    private String createStringURL() {
        String url_basic = getResources().getString(R.string.basic_url);

        if (searchBox_et.getText().toString().equals("")) {
            return null;
        } else
            return url_basic + searchBox_et.getText().toString().replace(" ", "+");
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        return new BookLoader(this, createStringURL());
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {

        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        mEmptyStateTextView.setText(R.string.no_books);

        mAdapter.clear();

        if (books != null && !books.isEmpty()) {
            mAdapter.addAll(books);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        mAdapter.clear();
    }
}
