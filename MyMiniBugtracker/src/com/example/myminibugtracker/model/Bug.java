package com.example.myminibugtracker.model;

import org.apache.commons.lang.StringUtils;

public class Bug {

	private String id;
	private String title;
	private String description;
	private String bugType;
	private String status;
	private long creationTimeStamp;
	private long modificationTimeStamp;

	public long getCreationTimeStamp() {
		return creationTimeStamp;
	}

	public void setCreationTimeStamp(long creationTimeStamp) {
		this.creationTimeStamp = creationTimeStamp;
	}

	public long getModificationTimeStamp() {
		return modificationTimeStamp;
	}

	public void setModificationTimeStamp(long modificationTimeStamp) {
		if (modificationTimeStamp < creationTimeStamp) {
			throw new RuntimeException(
					"cannot set modification date before creation date");
		}
		this.modificationTimeStamp = modificationTimeStamp;
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

	public boolean hasId() {
		if (StringUtils.isNotBlank(id)) {
			return true;
		} else {
			return false;
		}
	}

}
