package com.example.archit.booklisting;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by archit on 12/8/17.
 */

public class BooksAdaptor extends ArrayAdapter<Book> {

    public BooksAdaptor(Context context, List<Book> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.book_list_item, parent, false);
        }

        Book currentBook = getItem(position);

        TextView bookTitleView = convertView.findViewById(R.id.bookTitle_tv);
        bookTitleView.setText(currentBook.getBookTitle());

        TextView authorNameView = convertView.findViewById(R.id.authorName_tv);
        authorNameView.setText(currentBook.getAuthorName());

        return convertView;
    }
}
