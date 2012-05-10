package com.example.myminibugtracker.services;

import java.util.List;

import com.example.myminibugtracker.dao.BugDao;
import com.example.myminibugtracker.dao.BugDaoDb4o;
import com.example.myminibugtracker.model.Bug;

public class BugServiceImpl implements BugService {

	//TODO: irgendwie mit Spring und Autowiring lösen
	BugDao bugDao;

	public BugServiceImpl() {
		this.bugDao = new BugDaoDb4o();
	}

	public List<Bug> getAll() {
		return bugDao.findAllBugs();
	}

	public void save(Bug bug) {
		bugDao.persist(bug);
	}

	public void delete(Bug bug) {
		bugDao.remove(bug);
	}

}
