package com.example.myminibugtracker.dao;

import java.util.List;

import com.example.myminibugtracker.model.Bug;

public interface BugDao {

	List<Bug> findAllBugs();

	void persist(Bug bug);

	void remove(Bug bug);

}
