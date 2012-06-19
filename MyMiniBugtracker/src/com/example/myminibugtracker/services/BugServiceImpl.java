package com.example.myminibugtracker.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.myminibugtracker.dao.BugDao;
import com.example.myminibugtracker.model.Bug;

public class BugServiceImpl implements BugService {

	@Autowired
	BugDao bugDao;

	public Collection<Bug> getAll() {
		return bugDao.findAllBugs();
	}

	public void save(Bug bug) {
		if (bug.hasId()) {
			bug.setModificationDate(System.currentTimeMillis());
			bugDao.update(bug);

		} else {
			bug.setCreationDate(System.currentTimeMillis());
			bugDao.persist(bug);
		}

	}

	public void delete(Bug bug) {
		bugDao.remove(bug);
	}

}
