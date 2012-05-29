package com.example.myminibugtracker.services;

import java.util.Collection;

import com.example.myminibugtracker.dao.BugDao;
import com.example.myminibugtracker.dao.BugDaoDb4o;
import com.example.myminibugtracker.model.Bug;

public class BugServiceImpl implements BugService {

	// TODO: irgendwie mit Spring und Autowiring lösen
	BugDao bugDao;

	public BugServiceImpl() {
		this.bugDao = new BugDaoDb4o();
	}

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
