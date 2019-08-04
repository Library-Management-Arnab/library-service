package com.lms.ls.rest.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.ls.rest.model.client.LibraryBook;
import com.lms.ls.rest.model.client.LibraryRestResponse;
import com.lms.ls.rest.model.client.RequestMetaData;
import com.lms.ls.rest.service.LibraryBookService;

@RestController
@RequestMapping("/lib/books")
public class LibraryBookRestController {
	private LibraryBookService libraryBookService;

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getAllBooks() {
		return returnResponse(libraryBookService.getAllBooks());
	}

	@GetMapping(value = "/{bookId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getBookDetails(@PathVariable("bookId") String bookId) {
		return returnResponse(libraryBookService.getBookDetails(bookId));
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> addBook(LibraryBook libraryBook) {
		return returnResponse(libraryBookService.addBook(libraryBook));
	}

	@DeleteMapping(value = "/{bookId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> deleteBook(String bookId) {
		libraryBookService.deleteBook(bookId);
		return returnResponse(null);
	}

	private ResponseEntity<Object> returnResponse(Object body) {
		LibraryRestResponse<Object> response = new LibraryRestResponse<>();
		response.setBody(body);
		RequestMetaData metaData = new RequestMetaData();
		metaData.setRequestedBy("APPLICATION");
		metaData.setRequestTimestamp(new Date());
		response.setMetaData(metaData);
		response.setFinalStatus("Success");

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
