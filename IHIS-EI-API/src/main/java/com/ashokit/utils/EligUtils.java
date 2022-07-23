package com.ashokit.utils;

import java.util.List;

public class EligUtils {
public boolean kidsageelig(List<Integer> kidsagelist) {
	for(Integer age:kidsagelist) {
		if(age>16) 
			return true;
		
	}
	return false;
	
}
}
