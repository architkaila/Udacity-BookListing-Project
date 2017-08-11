package com.example.archit.booklisting;

/**
 * Created by archit on 12/8/17.
 */

public class Book {

    private String mBookTitle;
    private String mAuthorName;

    public Book(String bookTitle, String authorName) {
        mBookTitle = bookTitle;
        mAuthorName = authorName;
    }

    public String getBookTitle() {
        return mBookTitle;
    }

    public String getAuthorName() {
        return mAuthorName;
    }
}
