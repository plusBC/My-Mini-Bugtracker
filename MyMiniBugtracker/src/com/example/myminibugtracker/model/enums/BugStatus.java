package com.example.myminibugtracker.model.enums;

import com.example.myminibugtracker.services.Messages;

/**
 * @author Fiederling Daniel
 */
public enum BugStatus {

	OPEN(Messages.getString("enum.BugStatus.open")), FIXED(Messages
			.getString("enum.BugStatus.fixed")), WONT_FIX(Messages
			.getString("enum.BugStatus.wontFix")), DEPRECARED(Messages
			.getString("enum.BugStatus.deprecated"));

	private final String title;

	private BugStatus(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

}
