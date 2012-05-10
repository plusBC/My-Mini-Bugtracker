package com.example.myminibugtracker.model.enums;

/**
 * @author Fiederling Daniel
 * 
 */
public enum BugStatus {

	OPEN("open"), FIXED("fixed"), WONT_FIX("Won't fix"), DEPRECARED("Deprecated");

	private final String title;

	private BugStatus(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

}
