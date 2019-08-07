package com.lms.ls.rest.controller;

import com.lms.ls.rest.model.client.LibraryBook;
import com.lms.ls.rest.service.LibraryBookService;
import com.lms.ls.rest.util.LibraryServiceUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class LibraryBookRestController {
    private LibraryBookService libraryBookService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> addBook(@RequestBody LibraryBook libraryBook) {
        return LibraryServiceUtil.returnResponse(libraryBookService.addBook(libraryBook), HttpStatus.CREATED);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> getAllBooks() {
        return LibraryServiceUtil.returnResponse(libraryBookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping(value = "/{bookId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> getBookDetails(@PathVariable("bookId") String bookId) {
        return LibraryServiceUtil.returnResponse(libraryBookService.getBookDetails(bookId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{bookId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> deleteBook(@PathVariable("bookId") String bookId) {
        libraryBookService.deleteBook(bookId);
        return LibraryServiceUtil.returnResponse(null, HttpStatus.NO_CONTENT);
    }

}
