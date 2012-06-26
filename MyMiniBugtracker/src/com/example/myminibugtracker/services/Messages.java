package com.example.myminibugtracker.services;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

// (AUI) Du kannst gerne die Codebank und den darin enthaltenen RM nehmen, falls dir das hilft.
@Deprecated
public class Messages {
	private static final String BUNDLE_NAME = "messages"; 

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private Messages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
	
	public static String getString(String key, Object[] arg) {
		try {
			String bundleString = RESOURCE_BUNDLE.getString(key);
			for (int i = 0; i < arg.length; i++) {
				String regex = "[{][0-9][0-9]*[}]";
				bundleString = bundleString.replaceAll(regex, arg[i].toString());
			}
			return bundleString;
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
