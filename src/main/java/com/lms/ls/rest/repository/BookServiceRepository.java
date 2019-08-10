package com.lms.ls.rest.repository;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.lms.ls.rest.config.ServiceExplorer;
import com.lms.ls.rest.model.client.LibraryBook;
import com.lms.svc.common.constants.ApplicationCommonConstants;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class BookServiceRepository extends BaseServiceRepository {
	private static final Logger LOG = LoggerFactory.getLogger(BookServiceRepository.class);

	private ServiceExplorer serviceExplorer;
	private RestTemplate restTemplate;

	public LibraryBook addBook(LibraryBook book) {
		String addBookUrl = formURL(ApplicationCommonConstants.BOOK_SERVICE_BASE_URI);
		
		HttpEntity<LibraryBook> bookHttpEntity = new HttpEntity<>(book);
		ResponseEntity<String> addedBook = restTemplate.exchange(addBookUrl, HttpMethod.POST, bookHttpEntity, String.class);

		return checkForErrorAndReturn(addedBook, LibraryBook.class);
	}
	
	public List<LibraryBook> getAllBooks() {
		String getAllBooksUrl = formURL(ApplicationCommonConstants.BOOK_SERVICE_BASE_URI);

		ResponseEntity<String> allBooksResponse = restTemplate.exchange(getAllBooksUrl, HttpMethod.GET, null, String.class);
		LibraryBook[] allBooks = checkForErrorAndReturn(allBooksResponse, LibraryBook[].class);
		
		return Arrays.asList(allBooks);
	}
	
	public LibraryBook getBookDetails(String bookId) {
		String getBookByIdUrl = formURL(ApplicationCommonConstants.BOOK_SERVICE_BASE_URI, bookId);

		ResponseEntity<String> getBookByIdResponse = restTemplate.exchange(getBookByIdUrl, HttpMethod.GET, null, String.class);

		return checkForErrorAndReturn(getBookByIdResponse, LibraryBook.class);
	}
	
	public LibraryBook updateBook(String bookId, LibraryBook book) {
		String updateBookUrl = formURL(ApplicationCommonConstants.BOOK_SERVICE_BASE_URI, bookId);
		
		HttpEntity<LibraryBook> bookHttpEntity = new HttpEntity<>(book);
		ResponseEntity<String> updateBookResponse = restTemplate.exchange(updateBookUrl, HttpMethod.PUT, bookHttpEntity, String.class);

		return checkForErrorAndReturn(updateBookResponse, LibraryBook.class);
	}
	
	public void deleteBook(String bookId) {
		String deleteBookByIdUrl = formURL(ApplicationCommonConstants.BOOK_SERVICE_BASE_URI, bookId);
		LOG.info("Deleting book using [{}]", deleteBookByIdUrl);
	
		restTemplate.exchange(deleteBookByIdUrl, HttpMethod.DELETE, null, String.class);
	}

	private String formURL(String... components) {
		return serviceExplorer.getUrl(ApplicationCommonConstants.BOOK_SERVICE_APP_ID, components);
	}
}
