package com.stadiumgood.Utils;

import java.io.*;
import java.util.*;

import com.stadiumgood.Base.Driver;

public class ReadPropertyFile extends Driver{

	/*
	 * 
	 */
	public static String get(String propertyname) {
		
		String returnproperty=null;
		Properties property = new Properties();
		try {
			FileInputStream file = new FileInputStream("./resources/TestRunDetails.properties");
			property.load(file);
			returnproperty = property.getProperty(propertyname);
			if(returnproperty==null) {
				throw new Exception("Property named "+propertyname+ "not found");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return returnproperty;
		
	}
	
}
