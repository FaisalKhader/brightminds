package com.brightminds.assignment.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.hsqldb.lib.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brightminds.assignment.exception.BusinessException;
import com.brightminds.assignment.exception.UnauthorisedException;
import com.brightminds.assignment.model.Statement;
import com.brightminds.assignment.model.StatementDto;
import com.brightminds.assignment.service.StatementService;
import com.brightminds.assignment.util.DateUtils;
import com.brightminds.assignment.util.NumberUtils;

@RestController
@RequestMapping("/statement")
public class StatmentController {

	@Autowired
	StatementService statmentService;

	private boolean filterWthDates;
	private boolean filterWthAmount;

	private static final  Logger logger = LoggerFactory.getLogger(StatmentController.class);

	@GetMapping(value= {"/account/", "/account/{accountId}"})
	public List<Statement> getStatementByAccntIdAndFilters(@RequestBody StatementDto requestData,
															@PathVariable("accountId") Optional<Long> accountId, 
															Principal user) {
		if (!accountId.isPresent()) {
			logger.error("Missing Manadatory Parameter");
			throw new BusinessException("AccountId Not Found: You Must Specify Account ID");
		}
		validateRequestData(requestData);
		if (filterWthDates) {
			checkFilterAuthorization(user);
			return statmentService.findByAccountIdAndStatmentDate(accountId.get(), requestData);
		}
		if (filterWthAmount) {
			checkFilterAuthorization(user);
			return statmentService.findByAccountIdAndAmount(accountId.get(), requestData);
		}
		if (filterWthDates && filterWthAmount) {
			checkFilterAuthorization(user);
			return statmentService.findByAccountIdAndStatmentDateAndAmount(accountId.get(), requestData);
		}

		return statmentService.findByAccountId(accountId.get());

	}


	private void validateRequestData(StatementDto request) {

		if (request == null) {
			logger.error("Missing Request");
			throw new BusinessException("You Must Send a JSON Request !");
		}
		validateDateData(request.getFromDate(), request.getToDate());
		validateAmountData(request.getFromAmount(), request.getToAmount());
	}

	private void validateDateData(String fromDate, String toDate) {
		if (!StringUtil.isEmpty(fromDate)) {
			if (!StringUtil.isEmpty(toDate)) {
				filterWthDates = true;
				LocalDate statementFromDate = DateUtils.getStringAsDate(fromDate); 
				
				LocalDate statementToDate = DateUtils.getStringAsDate(toDate);
				
				if (statementFromDate.isAfter(statementToDate)) {
					filterWthDates = false;
					logger.error("Date Range Error");
					throw new BusinessException("ERROR: From Date Value Is After To Date Value !"); // exception
				}
			} else {
				filterWthDates = false;
				logger.error("Date Input Error");
				throw new BusinessException("ERROR : You Must Specify To Date Value !");
			}
		} else {
			filterWthDates = false;
		}
	}

	private void validateAmountData(String amountFrom, String amountTo) {

		if (!StringUtil.isEmpty(amountFrom)) {
			if (!StringUtil.isEmpty(amountTo)) {
				filterWthAmount = true;
				Double statementAmountFrom = NumberUtils.getStringAsDouble(amountFrom);
				Double statementAmountTo = NumberUtils.getStringAsDouble(amountTo);
				if (statementAmountFrom > (statementAmountTo)) {
					filterWthAmount = false;
					logger.error("Amount Range Error");
					throw new BusinessException("ERROR: Amount From Value Is Greater than Amount To Value !"); // exception
				}
			} else {
				filterWthAmount = false;
				logger.error("Amount To Input Missing");
				throw new BusinessException("ERROR : You Must Specify Amount To Value !");
			}
		} else {
			filterWthAmount = false;
		}
	}

	private void checkFilterAuthorization(Principal user) {
		if(user.getName().equals("user")) {
			logger.error("Authorization Issue!");
			throw new UnauthorisedException("You are not allowed to Perform Filters");			
		}

	}

	@PostMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "";
	}

}
