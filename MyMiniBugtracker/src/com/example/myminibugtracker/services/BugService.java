package com.example.myminibugtracker.services;

import java.util.List;

import com.example.myminibugtracker.model.Bug;

public interface BugService {

	List<Bug> getAll();

	void save(Bug bug);

	void delete(Bug bug);

}
