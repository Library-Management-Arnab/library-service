package com.lms.ls.rest.repository;

import com.lms.ls.rest.config.DiscoveryClientConfig;
import com.lms.ls.rest.model.client.LibraryBook;
import com.lms.ls.rest.service.BaseServiceRepository;
import com.lms.svc.common.constants.ApplicationCommonConstants;
import com.lms.svc.common.exception.NotImplementedException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@AllArgsConstructor
public class BookServiceRepository extends BaseServiceRepository {
	private static final Logger LOG = LoggerFactory.getLogger(BookServiceRepository.class);

	private DiscoveryClientConfig discoveryClientConfig;
    private RestTemplate restTemplate;

	public LibraryBook addBook(LibraryBook book) {
		String addBookUrl = prepareURL(ApplicationCommonConstants.BOOK_SERVICE_BASE_URI);
        HttpEntity<LibraryBook> bookHttpEntity = new HttpEntity<>(book);

		ResponseEntity<String> addedBook = restTemplate.exchange(addBookUrl, HttpMethod.POST, bookHttpEntity, String.class);

		return checkForErrorAndReturn(addedBook, LibraryBook.class);
	}

	public LibraryBook getBookDetails(String bookId) {
        String getBookByIdUrl = prepareURL(ApplicationCommonConstants.BOOK_SERVICE_BASE_URI) + "/" + bookId;
        LOG.info("Getting book using [{}]", getBookByIdUrl);
        ResponseEntity<String> getBookByIdResponse = restTemplate.exchange(getBookByIdUrl, HttpMethod.GET, null, String.class);

        return checkForErrorAndReturn(getBookByIdResponse, LibraryBook.class);
	}

	public List<LibraryBook> getAllBooks() {
        String getAllBooksUrl = prepareURL(ApplicationCommonConstants.BOOK_SERVICE_BASE_URI);
		String allBooksUri = prepareURL(ApplicationCommonConstants.BOOK_SERVICE_BASE_URI);
		List<LibraryBook> libraryBooks = new ArrayList<>();

        ResponseEntity<String> allBooksResponse = restTemplate.exchange(getAllBooksUrl, HttpMethod.GET, null, String.class);
        LibraryBook[] allBooks = checkForErrorAndReturn(allBooksResponse, LibraryBook[].class);
		return Arrays.asList(allBooks);
	}

	public void deleteBook(String bookId) {
        String deleteBookByIdUrl = prepareURL(ApplicationCommonConstants.BOOK_SERVICE_BASE_URI) + "/" + bookId;
        LOG.info("Deleting book using [{}]", deleteBookByIdUrl);
        restTemplate.exchange(deleteBookByIdUrl, HttpMethod.DELETE, null, String.class);
	}

	private String getURI() {
		URI uri = discoveryClientConfig.getBookServiceUrl();
		LOG.info("Book service URI - {}", uri);
		return uri.toString();
	}
	private String prepareURL(String component) {
		String uri = getURI();
		String fullURL = String.format("%s%s", uri, component);
		LOG.info("Making a call to URL - [{}]", fullURL);
		return fullURL;
	}
}
