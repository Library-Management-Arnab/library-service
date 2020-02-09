package com.lms.ls.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.ls.rest.model.db.BookIssueAction;

@Repository
public interface BookIssueActionRepository extends JpaRepository<BookIssueAction, String>{

}
