package com.brightminds.assignment.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.brightminds.assignment.exception.BusinessException;

public class DateUtils {

	public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("d.MM.yyyy");
	
	 
	private DateUtils() {
		throw new IllegalStateException("Utility class");
  
	}
	
	public static LocalDate getStringAsDate(String date) throws BusinessException{
		
		try {
			return LocalDate.parse(date, FORMAT);
		}catch (DateTimeParseException e) {
			throw new BusinessException("Invalid Dates Input!");
		}
		
	}
	
}
