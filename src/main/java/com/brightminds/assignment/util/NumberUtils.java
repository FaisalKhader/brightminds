package com.brightminds.assignment.util;

import com.brightminds.assignment.exception.BusinessException;

public class NumberUtils {
	
	
	
	public static Double getStringAsDouble(String number) {
		
		try {
			return Double.parseDouble(number);
		} catch (NumberFormatException e) {
			throw new BusinessException ("Invalid Amount Input!");

		}
		
	}
}


