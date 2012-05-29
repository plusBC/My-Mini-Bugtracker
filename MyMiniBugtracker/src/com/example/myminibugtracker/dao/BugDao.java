package com.example.myminibugtracker.dao;

import java.util.Collection;

import com.example.myminibugtracker.model.Bug;

public interface BugDao {

	Collection<Bug> findAllBugs();

	void persist(Bug bug);

	void update(Bug bug);

	void remove(Bug bug);

}
