package com.polarbookshop.catalogservice.domain;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String isbn) {
        super(String.format("Book with isbn: %s, not found in search", isbn));
    }
}
