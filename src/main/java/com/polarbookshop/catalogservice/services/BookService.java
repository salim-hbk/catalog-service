package com.polarbookshop.catalogservice.services;

import com.polarbookshop.catalogservice.entities.Book;
import com.polarbookshop.catalogservice.exceptions.BookAlreadyExistsException;
import com.polarbookshop.catalogservice.exceptions.BookNotFoundException;
import com.polarbookshop.catalogservice.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public Iterable<Book> viewBookList() {
        return bookRepository.findAll();
    }

    public Book viewBookDetails(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public Book addBookToCatalog(Book book) {
        if (bookRepository.existsByIsbn(book.isbn())) {
            throw new BookAlreadyExistsException(book.isbn());
        }
        return bookRepository.save(book);
    }
    public void removeBookFromCatalog(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }
    public Book editBookDetails(String isbn, Book book) {
        return bookRepository.findByIsbn(isbn)
                .map(it-> bookRepository
                        .save(new Book(isbn, book.title(), book.author(), book.price())))
                .orElseGet(()->addBookToCatalog(book));
    }


}
