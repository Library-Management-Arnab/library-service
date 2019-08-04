package com.lms.ls.rest.repository;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lms.ls.rest.config.DiscoveryClientConfig;
import com.lms.ls.rest.model.client.LibraryBook;
import com.lms.svc.common.exception.NotImplementedException;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class BookServiceRepository {
	private static final Logger LOG = LoggerFactory.getLogger(BookServiceRepository.class);
	private DiscoveryClientConfig discoveryClientConfig;
	
	public LibraryBook addBook(LibraryBook book) {
		throw new NotImplementedException();
	}

	public LibraryBook getBookDetails(String bookId) {
		throw new NotImplementedException();
	}

	public List<LibraryBook> getAllBooks() {
		getURI();
		throw new NotImplementedException();
	}

	public void deleteBook(String bookId) {
		throw new NotImplementedException();
	}
	
	private String getURI() {
		URI uri = discoveryClientConfig.getBookServiceUrl();
		LOG.info("Book service URI - {}", uri);
		return uri.toString();
	}
}
