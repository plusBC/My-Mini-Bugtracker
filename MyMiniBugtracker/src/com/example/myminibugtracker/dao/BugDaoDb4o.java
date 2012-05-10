package com.example.myminibugtracker.dao;

import java.util.ArrayList;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.example.myminibugtracker.model.Bug;

public class BugDaoDb4o implements BugDao {

	final String FILENAME = "D:\\Databases\\myBugTracker.yap";

	public List<Bug> findAllBugs() {
		// createDatabaseFileIfMissing();


		//TODO: ich verstehs schon wieder mal nicht...
		// aus irgendwelchen gründen koennen alle bugs aus der ausgelesen werden 
		// und werden auch in der Tabelle angezeigt wenn ich die DB nicht schließe. 
		// versuche ich das close aufzurufen wird ne exception geworfen, 
		// lass ich die DB offen kommt beim speichern der tickets ne exception...
		
		// ObjectContainer db = Db4oEmbedded.openFile(
		// Db4oEmbedded.newConfiguration(), FILENAME);
		List<Bug> bugs = new ArrayList<Bug>();
		// try {
		// bugs = db.query(Bug.class);
		// } finally {
		// // there is an exception thrown if i try to close the db...
		// // WTF WHY??
		// // db.close();
		// }
		return bugs;

	}

	public void persist(Bug bug) {
		ObjectContainer db = Db4oEmbedded.openFile(
				Db4oEmbedded.newConfiguration(), FILENAME);
		try {
			bug.setId("" + System.currentTimeMillis());
			db.store(bug);
		} finally {
			db.close();
		}

	}

	public void remove(Bug bug) {
		// TODO Auto-generated method stub

	}

	// private void createDatabaseFileIfMissing() {
	// File file = new File(FILENAME);
	// try {
	// file.createNewFile();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }

}
