package com.example.myminibugtracker.model;

import com.example.myminibugtracker.model.enums.BugStatus;
import com.example.myminibugtracker.model.enums.BugType;

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
					"cannot set modification date before creation date");
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

	// AUI getter/setter muessen gleichen datentyp haben
	public void setBugType(BugType bugType) {
		this.bugType = bugType.getTitle();
	}

	public String getStatus() {
		return status;
	}

	// AUI getter/setter muessen gleichen datentyp haben
	public void setStatus(BugStatus status) {
		this.status = status.getTitle();
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
