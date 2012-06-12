package com.example.myminibugtracker.dao;

import java.util.Collection;

import com.example.myminibugtracker.model.Bug;

public interface BugDao {

	/**
	 * retrieve all bugs from the DB
	 * 
	 * @return all bugs
	 */
	Collection<Bug> findAllBugs();

	/**
	 * perform save operations
	 * 
	 * @param bug
	 *            the Bug to be saved
	 */
	void persist(Bug bug);

	/**
	 * perform update-operations
	 * 
	 * @param bug
	 *            the Bug to be updated
	 */
	void update(Bug bug);

	/**
	 * perform delete-operations
	 * 
	 * @param bug
	 *            the Bug to be removed
	 */
	void remove(Bug bug);

}
