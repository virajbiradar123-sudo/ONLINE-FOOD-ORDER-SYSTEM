package com.example.librarybookmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.librarybookmanagement.model.Book;

@Service
public class BookService {

    private List<Book> books = new ArrayList<>();

    private int nextId = 1;

    // Add Book
    public Book addBook(Book book) {

        book.setBookId(nextId++);

        books.add(book);

        return book;
    }

    // Get All Books
    public List<Book> getAllBooks() {

        return books;
    }

    // Search Book By ID
    public Book getBookById(int id) {

        return books.stream()
                .filter(book -> book.getBookId() == id)
                .findFirst()
                .orElse(null);
    }

    // Update Book Availability
    public Book updateAvailability(int id, boolean available) {

        Book book = getBookById(id);

        if (book != null) {
            book.setAvailable(available);
        }

        return book;
    }

    // Delete Book
    public boolean deleteBook(int id) {

        return books.removeIf(book -> book.getBookId() == id);
    }

    // Search Book By Name
    public List<Book> searchByName(String name) {

        return books.stream()
                .filter(book -> book.getBookName()
                .toLowerCase()
                .contains(name.toLowerCase()))
                .toList();
    }

    // Update Complete Book Details
    public Book updateBook(int id, Book updatedBook) {

        Book book = getBookById(id);

        if (book != null) {

            book.setBookName(updatedBook.getBookName());
            book.setAuthor(updatedBook.getAuthor());
            book.setPrice(updatedBook.getPrice());
            book.setAvailable(updatedBook.isAvailable());
        }

        return book;
    }
}