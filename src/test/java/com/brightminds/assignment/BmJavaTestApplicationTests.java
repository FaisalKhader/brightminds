package com.brightminds.assignment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.brightminds.assignment.controller.StatmentController;
import com.brightminds.assignment.model.Statement;
import com.brightminds.assignment.model.StatementDto;
import com.brightminds.assignment.service.StatementService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(StatmentController.class)
class BmJavaTestApplicationTests {

	
	@Autowired
	MockMvc mvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	StatementService statementSrv;


	@Test
	void t1_testFindByAccountId() {
		List<Statement> statements = statementSrv.findByAccountId(1L);
		 assertThat(statements).isNotNull();
	}
	
	@Test
	 void t1_testFindByAccountIdAndDate() {
		
		StatementDto requestData = new StatementDto();
		requestData.setFromDate("01.01.2020");
		requestData.setToDate("01.06.2020");
		List<Statement> statements = statementSrv.findByAccountIdAndStatmentDate(1L, requestData);
		 assertThat(statements).isNotNull();
	}

	@Test
	void t1_testFindByAccountIdAndAmount() {
		
		StatementDto requestData = new StatementDto();
		requestData.setFromAmount("300");
		requestData.setToAmount("1000");
		List<Statement> statements = statementSrv.findByAccountIdAndAmount(1L, requestData);
		 assertThat(statements).isNotNull();
	}

	@Test
	 void t1_testFindByAccountIdAndDateAndAmount() {
		
		StatementDto requestData = new StatementDto();
		requestData.setFromDate("01.01.2020");
		requestData.setToDate("01.06.2020");
		requestData.setFromAmount("300");
		requestData.setToAmount("1000");
		List<Statement> statements = statementSrv.findByAccountIdAndStatmentDateAndAmount(1L, requestData);
		 assertThat(statements).isNotNull();
	}

	@Test
	void adminFindByAccountId() throws  Exception {
		
		StatementDto requestData = new StatementDto();
		requestData.setFromDate("");
		requestData.setToDate("");
		requestData.setFromAmount("");
		requestData.setToAmount("");
											   
		  mvc.perform( MockMvcRequestBuilders
			      .get("/statement/account/{accountId}",1L)
			      .with(httpBasic("admin","admin"))
			      .content(objectMapper.writeValueAsString(requestData))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
		  		  .andDo(print())
			      .andExpect(status().isOk());
	}

	@Test
	void adminFindByAccountIdWthDates() throws  Exception {
		
		StatementDto requestData = new StatementDto();
		requestData.setFromDate("01.01.2020");
		requestData.setToDate("01.06.2020");
		requestData.setFromAmount("");
		requestData.setToAmount("");
											   
		  mvc.perform( MockMvcRequestBuilders
			      .get("/statement/account/{accountId}",1L)
			      .with(httpBasic("admin","admin"))
			      .content(objectMapper.writeValueAsString(requestData))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
		  		  .andDo(print())
			      .andExpect(status().isOk());
	}

	@Test
	void adminFindByAccountIdWthDatesAndAmount() throws  Exception {
		
		StatementDto requestData = new StatementDto();
		requestData.setFromDate("01.01.2020");
		requestData.setToDate("01.06.2020");
		requestData.setFromAmount("500");
		requestData.setToAmount("1000");
											   
		  mvc.perform( MockMvcRequestBuilders
			      .get("/statement/account/{accountId}",1L)
			      .with(httpBasic("admin","admin"))
			      .content(objectMapper.writeValueAsString(requestData))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
		  		  .andDo(print())
			      .andExpect(status().isOk());
	}

	@Test
	void adminFindByAccountIdWthAmount() throws  Exception {
		
		StatementDto requestData = new StatementDto();
		requestData.setFromDate("");
		requestData.setToDate("");
		requestData.setFromAmount("500");
		requestData.setToAmount("1000");
											   
		  mvc.perform( MockMvcRequestBuilders
			      .get("/statement/account/{accountId}",1L)
			      .with(httpBasic("admin","admin"))
			      .content(objectMapper.writeValueAsString(requestData))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
		  		  .andDo(print())
			      .andExpect(status().isOk());
	}

	@Test
	void adminFindByAccountIdWthMissingDate() throws  Exception {
		
		StatementDto requestData = new StatementDto();
		requestData.setFromDate("01.01.2020");
		requestData.setToDate("");
		requestData.setFromAmount("");
		requestData.setToAmount("");
											   
		  mvc.perform( MockMvcRequestBuilders
			      .get("/statement/account/{accountId}",1L)
			      .with(httpBasic("admin","admin"))
			      .content(objectMapper.writeValueAsString(requestData))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
		  		  .andDo(print())
			      .andExpect(status().isBadRequest());
	}

	@Test
	void adminFindByAccountIdWthMissingAmount() throws  Exception {
		
		StatementDto requestData = new StatementDto();
		requestData.setFromDate("");
		requestData.setToDate("");
		requestData.setFromAmount("1000");
		requestData.setToAmount("");
											   
		  mvc.perform( MockMvcRequestBuilders
			      .get("/statement/account/{accountId}",1L)
			      .with(httpBasic("admin","admin"))
			      .content(objectMapper.writeValueAsString(requestData))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
		  		  .andDo(print())
			      .andExpect(status().isBadRequest());
	}

	@Test
	void adminFindByAccountIdWthWrongDateParam() throws  Exception {
		
		StatementDto requestData = new StatementDto();
		requestData.setFromDate("01.s01.2020s");
		requestData.setToDate("01.05.2020");
		requestData.setFromAmount("");
		requestData.setToAmount("");
											   
		  mvc.perform( MockMvcRequestBuilders
			      .get("/statement/account/{accountId}",1L)
			      .with(httpBasic("admin","admin"))
			      .content(objectMapper.writeValueAsString(requestData))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
		  		  .andDo(print())
			      .andExpect(status().isBadRequest());
	}

	@Test
	void adminFindByAccountIdWthWrongAmountParam() throws  Exception {
		
		StatementDto requestData = new StatementDto();
		requestData.setFromDate("");
		requestData.setToDate("");
		requestData.setFromAmount("s100s");
		requestData.setToAmount("500");
											   
		  mvc.perform( MockMvcRequestBuilders
			      .get("/statement/account/{accountId}",1L)
			      .with(httpBasic("admin","admin"))
			      .content(objectMapper.writeValueAsString(requestData))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
		  		  .andDo(print())
			      .andExpect(status().isBadRequest());
	}

	
	@Test
	void userFindByAccountId() throws  Exception {
		
		StatementDto requestData = new StatementDto();
		requestData.setFromDate("");
		requestData.setToDate("");
		requestData.setFromAmount("");
		requestData.setToAmount("");
											   
		  mvc.perform( MockMvcRequestBuilders
			      .get("/statement/account/{accountId}",1L)
			      .with(httpBasic("user","user"))
			      .content(objectMapper.writeValueAsString(requestData))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
		  		  .andDo(print())
			      .andExpect(status().isOk());
	}

	@Test
	void userFindByAccountIdAndFilters() throws  Exception {
		
		StatementDto requestData = new StatementDto();
		requestData.setFromDate("01.01.2020");
		requestData.setToDate("01.06.2020");
		requestData.setFromAmount("");
		requestData.setToAmount("");
											   
		  mvc.perform( MockMvcRequestBuilders
			      .get("/statement/account/{accountId}",1L)
			      .with(httpBasic("user","user"))
			      .content(objectMapper.writeValueAsString(requestData))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
		  		  .andDo(print())
			      .andExpect(status().isUnauthorized());
	}
	
}
