package com.example.myminibugtracker.model.enums;

import com.example.myminibugtracker.services.Messages;

/**
 * @author Fiederling Daniel
 * 
 */
public enum BugType {

	BUG(Messages.getString("enum.BugType.bug")), FEATURE(Messages
			.getString("enum.BugType.feature")), COSMETICS(Messages
			.getString("enum.BugType.cosmetics"));

	private final String title;

	private BugType(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

}
