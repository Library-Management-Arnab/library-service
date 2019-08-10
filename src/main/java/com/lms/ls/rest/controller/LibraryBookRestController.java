package com.lms.ls.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.ls.rest.model.client.LibraryBook;
import com.lms.ls.rest.service.LibraryBookService;
import com.lms.svc.common.constants.ApplicationCommonConstants;
import com.lms.svc.common.util.LMSResponseUtil;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class LibraryBookRestController {
	private LibraryBookService libraryBookService;

	@Secured(value = { ApplicationCommonConstants.USER_RIGHT_ADMIN })
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> addBook(@RequestBody LibraryBook libraryBook) {
		return LMSResponseUtil.returnResponse(libraryBookService.addBook(libraryBook), HttpStatus.CREATED);
	}

	@Secured(value = { ApplicationCommonConstants.USER_RIGHT_ADMIN, ApplicationCommonConstants.USER_RIGHT_BASIC })
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getAllBooks() {
		return LMSResponseUtil.returnResponse(libraryBookService.getAllBooks(), HttpStatus.OK);
	}

	@Secured(value = { ApplicationCommonConstants.USER_RIGHT_ADMIN, ApplicationCommonConstants.USER_RIGHT_BASIC })
	@GetMapping(value = "/{bookId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getBookDetails(@PathVariable("bookId") String bookId) {
		return LMSResponseUtil.returnResponse(libraryBookService.getBookDetails(bookId), HttpStatus.OK);
	}

	@Secured(value = { ApplicationCommonConstants.USER_RIGHT_ADMIN })
	@PutMapping(value = "/{bookId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateBook(@PathVariable("bookId") String bookId, @RequestBody LibraryBook book) {
		return LMSResponseUtil.returnResponse(libraryBookService.updateBook(bookId, book), HttpStatus.OK);
	}

	@Secured(value = { ApplicationCommonConstants.USER_RIGHT_ADMIN })
	@DeleteMapping(value = "/{bookId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> deleteBook(@PathVariable("bookId") String bookId) {
		libraryBookService.deleteBook(bookId);
		return LMSResponseUtil.returnResponse(null, HttpStatus.NO_CONTENT);
	}

}
