package com.lms.ls.rest.model.db;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lms.svc.common.constants.ApplicationCommonConstants;

import lombok.Data;

@Data
@Entity
@Table(name = "issued_book_history")
public class IssueHistory implements Serializable {
	private static final long serialVersionUID = -5383660774004808934L;
	public IssueHistory() {
		this.historyId = String.format("IBH%s", ApplicationCommonConstants.generateId());
	}
	@Id
	@Column(name = "history_id", length = 20)
	private String historyId;

	@OneToOne
	@JoinColumn(name = "action_id", referencedColumnName = "action_id")
	private BookIssueAction action;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "issued_book_id")
	private IssuedBook issuedBook;
}
