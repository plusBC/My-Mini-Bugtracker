package com.example.myminibugtracker.services;

import java.util.Collection;

import com.example.myminibugtracker.model.Bug;

public interface BugService {

	/**
	 * load all bugs from DB
	 * @return
	 */
	Collection<Bug> getAll();

	/**
	 * if the given bug has a creation date set, the bug will be updated
	 * otherwise the given bug will will be stored as a new entry
	 * 
	 * @param bug
	 */
	void save(Bug bug);

	/**
	 * remove the given bug from the DB
	 * @param bug
	 */
	void delete(Bug bug);

}
