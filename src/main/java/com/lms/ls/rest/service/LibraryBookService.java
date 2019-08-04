package com.lms.ls.rest.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.lms.ls.rest.model.client.LibraryBook;
import com.lms.ls.rest.repository.BookServiceRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class LibraryBookService {
	private BookServiceRepository bookServiceRepository;

	public void login() {

	}

	public List<LibraryBook> getAllBooks() {
		return bookServiceRepository.getAllBooks();
	}

	public LibraryBook getBookDetails(String bookId) {
		return bookServiceRepository.getBookDetails(bookId);
	}

	public LibraryBook addBook(LibraryBook libraryBook) {
		return bookServiceRepository.addBook(libraryBook);
	}

	public void deleteBook(String bookId) {
		bookServiceRepository.deleteBook(bookId);
	}
}
