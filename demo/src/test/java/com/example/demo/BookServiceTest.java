package com.example.demo;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BookServiceTest {
    private BookService bookService;

    @BeforeEach
    public void setup() {
        bookService = new BookService();
    }
    @Test
    public void testGetAllBooks_EmptyResult() {
        List<Book> result = bookService.getAllBooks();
        Assertions.assertNull(result);
    }
    @Test
    public void testAddBookRejectsNegativeId() {
        Book b1 = new Book(-1L,"Kitap-1","Yazar-1");
        Book addedBook = bookService.addBook(b1);
        Assertions.assertNull(addedBook);
    }

    @Test
    public void testAddBook() {
        Book b1 = new Book(1L,"Kitap-1","Yazar-1");
        Book addedBook = bookService.addBook(b1);
        Assertions.assertSame(b1, addedBook);
    }

    @Test
    public void testGetAllBooks(){
        Book b1 = new Book(1L,"Kitap-1","Yazar-1");
        Book b2 = new Book(2L,"Kitap-2","Yazar-2");
        Book b3 = new Book(3L,"Kitap-3","Yazar-3");
        Book b4 = new Book(4L,"Kitap-4","Yazar-4");
        Book b5 = new Book(5L,"Kitap-5","Yazar-5");
        bookService.addBook(b1);
        bookService.addBook(b2);
        bookService.addBook(b3);
        bookService.addBook(b4);
        bookService.addBook(b5);
        List<Book> book = bookService.getAllBooks();
        Assertions.assertNotNull(book);
        Assertions.assertEquals(5, book.size());
        Assertions.assertEquals(b1.getId(), book.get(0).getId());
        Assertions.assertEquals(b2.getId(), book.get(1).getId());


        Book result2 =  bookService.getBookById(13L);
        Assertions.assertNull(result2);
        Assertions.assertSame(b2, bookService.getBookById(2L));
    }

    @Test
    public void testDeleteBookById(){
        Book b1 = new Book(1L,"Kitap-1","Yazar-1");
        Book b2 = new Book(2L,"Kitap-2","Yazar-2");
        Book b3 = new Book(3L,"Kitap-3","Yazar-3");
        Book b4 = new Book(4L,"Kitap-4","Yazar-4");
        Book b5 = new Book(5L,"Kitap-5","Yazar-5");
        bookService.addBook(b1);
        bookService.addBook(b2);
        bookService.addBook(b3);
        bookService.addBook(b4);
        bookService.addBook(b5);

        boolean  result = bookService.deleteBookById(1L);
        boolean result2 = bookService.deleteBookById(2L);
        Assertions.assertTrue(result);
        Assertions.assertTrue(result2);
        Assertions.assertFalse(bookService.deleteBookById(13L));
    }

}
