package com.lms.ls.rest.model.db;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.lms.svc.common.constants.ApplicationCommonConstants;

import lombok.Data;

@Data
@Entity
@Table(name = "issued_book")
public class IssuedBook implements Serializable {
	private static final long serialVersionUID = 1L;

	public IssuedBook() {
		this.issueRecordId = String.format("IBR%s", ApplicationCommonConstants.generateId());
	}

	@Id
	@Column(name = "issue_record_id", length = 20)
	private String issueRecordId;

	@Column(name = "user_id", length = 20)
	private String userId;

	@Column(name = "book_id", length = 20)
	private String bookId;

	private Date bookIssueDate;
	private Date returnDueDate;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "issuedBook")
	private List<IssueHistory> history;
}
