package com.brightminds.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brightminds.assignment.model.Statement;

@Repository
public interface StatmentRepo extends JpaRepository<Statement, Integer> {
	
	List<Statement> findByAccountId(Long accountId);
	
	List<Statement> findByAccountIdAndStatmentDate(Long accountId,String statementDate);
	
	List<Statement> findByAccountIdAndAmount(Long accountId,String amount);

}
