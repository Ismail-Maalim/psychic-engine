package com.example.demo;

import com.example.demo.controller.BookController;
import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.Arrays;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllBooks() throws Exception {
        when(bookService.getAllBooks()).thenReturn(Arrays.asList(
                new Book(1L, "Kitap-1", "Yazar-1"),
                new Book(2L, "Kitap-2", "Yazar-2")
        ));

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testAddBook() throws Exception {
        Book book = new Book(11L, "Kitap-1", "Yazar-1");
        when(bookService.addBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(11))
                .andExpect(jsonPath("$.title").value("Kitap-1"))
                .andExpect(jsonPath("$.author").value("Yazar-1"));
    }
    @Test
    public void testGetBookById () throws Exception {
        Long id = 5L;
        when(bookService.getBookById(id)).thenReturn(new Book(id, "Kitap-1", "Yazar-1"));
        mockMvc.perform(get("/api/books/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Kitap-1"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        Long id = 11L;
        when(bookService.deleteBookById(id)).thenReturn(true);
        mockMvc.perform(delete("/api/books/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string("Book Deleted Successfully"));
    }

    @Test
    public void testDeleteBook_NotFound() throws Exception {
        Long id = 999L;
        when(bookService.deleteBookById(id)).thenReturn(false);
        mockMvc.perform(delete("/api/books/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string("Book Not Found"));
    }
}
