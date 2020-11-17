package com.example.demo.controller;

import com.example.demo.exception.BookNotFoundException;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
//import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/vineet")
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    // Get All Notes
    @GetMapping("/books")
    public List<Book> getAllNotes() {
        return bookRepository.findAll();
    }

    // Create a new Note
    @PostMapping("/books")
    public Book createNote(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    // Get a Single Note
    @GetMapping("/books/{id}")
    public Book getNoteById(@PathVariable(value = "id") Long bookId) throws BookNotFoundException {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
    }

    // Update a Note
    @PutMapping("/books/{id}")
    public Book updateNote(@PathVariable(value = "id") Long bookId,
                           @RequestBody Book bookDetails) throws BookNotFoundException {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        book.setBook_name(bookDetails.getBook_name());
        book.setAuthor_name(bookDetails.getAuthor_name());
        book.setIsbn(bookDetails.getIsbn());

        Book updatedBook = bookRepository.save(book);

        return updatedBook;
    }

    // Delete a Note
    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long bookId) throws BookNotFoundException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        bookRepository.delete(book);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/books2")
    private String getEmployees()
    {
        final String uri = "http://localhost:8080/vineet/books";

        //TODO: Autowire the RestTemplate in all the examples
        RestTemplate restTemplate = new RestTemplate();

        String result = restTemplate.getForObject(uri, String.class);
        System.out.println(result);
        return result;
    }

//    Q2.  Write a program to call the below mentioned rest APIs.
//    Display the email ids returned by the GET method
//    https://reqres.in/api/users/1
//    https://reqres.in/api/users/3
//    https://reqres.in/api/users/10

    @GetMapping("/books3")
    private List<String> getEmailid()
    {
        final String uri = "http://localhost:8080/vineet/books";


        //TODO: Autowire the RestTemplate in all the examples
        RestTemplate restTemplate = new RestTemplate();
        List<String> result = new ArrayList<String>();

        String result1 = restTemplate.getForObject(uri, String.class);
        System.out.println(result1);
        result.add(result1);
        return result;
    }

}
