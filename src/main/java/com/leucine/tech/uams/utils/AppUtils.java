package com.leucine.tech.uams.utils;

import java.util.Arrays;
import java.util.List;

import com.leucine.tech.uams.constants.AppConstants;

public class AppUtils {
	
	public static List<String> getRoles() {
		return Arrays.asList(AppConstants.EMPLOYEE_ROLE, AppConstants.MANAGER_ROLE, AppConstants.ADMIN_ROLE);
	}

}
