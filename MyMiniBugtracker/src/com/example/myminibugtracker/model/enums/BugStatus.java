package com.example.myminibugtracker.model.enums;

/**
 * @author Fiederling Daniel
 * 
 * AUI Diese Enum listet potentielle BugStatus "Namen", oder? Die Übersetzungen sollen natürlich irgendwann woanders hin.. 
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
