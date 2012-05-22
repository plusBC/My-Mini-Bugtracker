package com.example.myminibugtracker.model;

import com.example.myminibugtracker.services.Messages;

public class Bug {

	private String id;
	private String title;
	private String description;
	private String bugType;
	private String status;
	private long creationDate;
	private long modificationDate;

	public long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(long creationDate) {
		this.creationDate = creationDate;
	}

	public long getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(long modificationDate) {
		if (modificationDate < creationDate) {
			throw new RuntimeException(
					Messages.getString("exeption.Bug.ModificationDateError"));
		}
		this.modificationDate = modificationDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBugType() {
		return bugType;
	}

	public void setBugType(String bugType) {
		this.bugType = bugType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
