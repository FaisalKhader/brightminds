package com.brightminds.assignment.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brightminds.assignment.model.Statement;
import com.brightminds.assignment.model.StatementDto;
import com.brightminds.assignment.repository.StatmentRepo;
import com.brightminds.assignment.service.StatementService;
import com.brightminds.assignment.util.DateUtils;
	
@Service
@Transactional(readOnly=true)
public class StatmentServiceImpl implements StatementService {

	StatmentRepo statmentRepo;
	LocalDate fromDate;
	LocalDate toDate;

	@Autowired
	public StatmentServiceImpl(StatmentRepo statmentRepo) {
		this.statmentRepo = statmentRepo;
	}
		
	@Override
	public List<Statement> findAll() {
		return statmentRepo.findAll() ;
	}
	
	@Override
	public List<Statement> findByAccountId(Long accountId) {
		
		return statmentRepo.findByAccountId(accountId).stream()
													  .filter(date -> date.getStatmentDate().isAfter(LocalDate.now().plusMonths(-6)))
													  .collect(Collectors.toList());
	}

	@Override
	public List<Statement> findByAccountIdAndStatmentDate(Long accountId,StatementDto requestData) {
		List<Statement> accountStatementLst = statmentRepo.findByAccountId(accountId);
		
		fromDate = DateUtils.getStringAsDate(requestData.getFromDate());
		toDate = DateUtils.getStringAsDate(requestData.getToDate());
		
		return	accountStatementLst.stream()
				               	   .filter(dates -> dates.getStatmentDate().isAfter(fromDate) && dates.getStatmentDate().isBefore(toDate))			               	
				               	   .collect(Collectors.toList());
	}
	
	@Override
	public List<Statement> findByAccountIdAndAmount(Long accountId, StatementDto requestData) {
		List<Statement> accountStatementLst =statmentRepo.findByAccountId(accountId);
		
		return accountStatementLst.stream()
								  .filter(amount -> amount.getAmount()>= Double.parseDouble(requestData.getFromAmount()) && amount.getAmount() <= Double.parseDouble(requestData.getToAmount()))
								  .collect(Collectors.toList());
	}

	@Override
	public List<Statement> findByAccountIdAndStatmentDateAndAmount(Long accountId, StatementDto requestData) {
		
		List<Statement> accountStatementLst = statmentRepo.findByAccountId(accountId);
		
		fromDate = DateUtils.getStringAsDate(requestData.getFromDate());
		toDate = DateUtils.getStringAsDate(requestData.getToDate());
		
		return	accountStatementLst.stream()
				               	   .filter(dates -> dates.getStatmentDate().isAfter(fromDate) && dates.getStatmentDate().isBefore(toDate))	
				               	   .filter(amount -> amount.getAmount()>= Double.parseDouble(requestData.getFromAmount()) && amount.getAmount() <= Double.parseDouble(requestData.getToAmount()))
				               	   .collect(Collectors.toList());
	}

	
	
}
