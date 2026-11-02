package com.example.librarybookmanagement.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class Book {

private Integer bookId;

@NotBlank(message = "Book name is required")
@Size(max = 100, message = "Book name cannot exceed 100 characters")
private String bookName;

@NotBlank(message = "Author name is required")
@Size(max = 80, message = "Author name cannot exceed 80 characters")
private String author;

@Positive(message = "Price must be positive")
private double price;

private boolean available;

public Book() {
}

public Book(Integer bookId, String bookName,
            String author, double price, boolean available) {

    this.bookId = bookId;
    this.bookName = bookName;
    this.author = author;
    this.price = price;
    this.available = available;
}

public Integer getBookId() {
    return bookId;
}

public void setBookId(Integer bookId) {
    this.bookId = bookId;
}

public String getBookName() {
    return bookName;
}

public void setBookName(String bookName) {
    this.bookName = bookName;
}

public String getAuthor() {
    return author;
}

public void setAuthor(String author) {
    this.author = author;
}

public double getPrice() {
    return price;
}

public void setPrice(double price) {
    this.price = price;
}

public boolean isAvailable() {
    return available;
}

public void setAvailable(boolean available) {
    this.available = available;
}

}
