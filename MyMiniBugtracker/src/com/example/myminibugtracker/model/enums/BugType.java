package com.example.myminibugtracker.model.enums;

/**
 * @author Fiederling Daniel
 * 
 * AUI Diese Enum listet potentielle BugType "Namen", oder? Die Übersetzungen sollen natürlich irgendwann woanders hin.. 
 */
public enum BugType {

	BUG("Bug"), FEATURE("Feature"), COSMETICS("Cosmetics");

	private final String title;

	private BugType(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

}
