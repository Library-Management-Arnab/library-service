package com.lms.ls.rest.model.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "book_issue_action")
public class BookIssueAction {
	@Id
	@Column(name = "action_id", length = 2)
	private String actionId;
	@Column(name = "action_name", length = 12, nullable = false)
	private String action;
}
