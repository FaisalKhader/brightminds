package com.brightminds.assignment.service;

import java.util.List;

import com.brightminds.assignment.model.Statement;
import com.brightminds.assignment.model.StatementDto;

public interface StatementService {

	List<Statement> findAll();
	
	List<Statement> findByAccountId(Long accountId);
	
	
	List<Statement> findByAccountIdAndStatmentDate(Long accountId,StatementDto statementData);
	
	List<Statement> findByAccountIdAndAmount(Long accountId,StatementDto statementData);
	
	List<Statement> findByAccountIdAndStatmentDateAndAmount(Long accountId,StatementDto statementData);
}
