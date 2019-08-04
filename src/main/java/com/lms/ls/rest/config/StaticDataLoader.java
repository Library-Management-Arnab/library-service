package com.lms.ls.rest.config;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lms.ls.rest.model.db.BookIssueAction;
import com.lms.ls.rest.repository.BookIssueActionRepository;
import com.lms.svc.common.config.BaseDataLoader;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class StaticDataLoader extends BaseDataLoader {
	private final Logger LOG = LoggerFactory.getLogger(StaticDataLoader.class);
	private BookIssueActionRepository bookIssueActionRepository;
	
	private List<BookIssueAction> bookIssueActions;
	private List<String> validBookIssueActions;
	
	@PostConstruct
	public void loadData() {
		LOG.info("Populating  data for book service");
		bookIssueActions = bookIssueActionRepository.findAll();
		validBookIssueActions = bookIssueActions.stream().map(BookIssueAction::getAction).collect(Collectors.toList());
		LOG.info("Loaded {} valid actions - {}", bookIssueActions.size(), validBookIssueActions);
	}
	
	public String getIssueActionForClient(BookIssueAction bookIssueAction) {
		return getClientString(bookIssueActions, bookIssueAction, BookIssueAction::getAction);
	}
	public BookIssueAction getIssueActionFromClient(String action) {
		Predicate<BookIssueAction> predicate = bookIssueAction -> bookIssueAction.getAction().equalsIgnoreCase(action);
		return returnOrThrow(bookIssueActions, predicate, action, validBookIssueActions, "Issue Action");
	}
}
