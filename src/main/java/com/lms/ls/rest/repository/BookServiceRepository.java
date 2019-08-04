package com.lms.ls.rest.repository;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.lms.ls.rest.config.DiscoveryClientConfig;
import com.lms.ls.rest.exception.ServiceException;
import com.lms.ls.rest.model.client.LibraryBook;
import com.lms.ls.rest.service.BaseServiceRepository;
import com.lms.svc.common.constants.ApplicationCommonConstants;
import com.lms.svc.common.exception.NotImplementedException;
import com.lms.svc.common.model.ErrorObject;

import lombok.AllArgsConstructor;
import reactor.core.Disposable;

@Repository
@AllArgsConstructor
public class BookServiceRepository extends BaseServiceRepository {
	private static final Logger LOG = LoggerFactory.getLogger(BookServiceRepository.class);
	
	private DiscoveryClientConfig discoveryClientConfig;
	private RestTemplate restTemplate;
	
	private WebClient webClient;

	public LibraryBook addBook(LibraryBook book) {
		String addBookUrl = prepareURL(ApplicationCommonConstants.BOOK_SERVICE_BASE_URI);
		
//		LibraryBook addedBook = webClient.post()
//									.uri(addBookUrl)
//									.accept(MediaType.APPLICATION_JSON_UTF8)
//									.body(BodyInserters.fromObject(book))
//									.exchange()
//									.flatMap(response -> {
//										if(response.statusCode().isError()) {
//											throw new ServiceException(response.bodyToMono(ErrorObject.class).);
//										}
//										return response.bodyToMono(LibraryBook.class);
//									})
//									.block();
		ResponseEntity<Object> response = restTemplate.exchange(addBookUrl, HttpMethod.POST, new HttpEntity<>(book), Object.class);
		
		return checkForError(LibraryBook.class, response); 
	}

	public LibraryBook getBookDetails(String bookId) {
		throw new NotImplementedException();
	}

	public List<LibraryBook> getAllBooks() {
		String allBooksUri = prepareURL(ApplicationCommonConstants.BOOK_SERVICE_BASE_URI);
		ResponseEntity<LibraryBook[]> booksResponse = restTemplate.exchange(allBooksUri, HttpMethod.GET, null, LibraryBook[].class);
		return Arrays.asList(booksResponse.getBody());
	}

	public void deleteBook(String bookId) {
		throw new NotImplementedException();
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
