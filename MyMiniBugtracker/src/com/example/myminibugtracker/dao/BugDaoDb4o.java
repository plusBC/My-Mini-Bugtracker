package com.example.myminibugtracker.dao;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.example.myminibugtracker.model.Bug;

public class BugDaoDb4o implements BugDao {

	final String FILENAME = "D:\\Temp\\Databases\\MyBugTracker.yap";
	final ObjectContainer DB;

	// haesslicher workaround weil ich sonst nicht alle Objekte aus der DB lesen
	// kann (com.db4o.ext.DatabaseClosedException)

	public BugDaoDb4o() {
		createDBFileIfMissing();
		DB = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), FILENAME);
	}

	public Collection<Bug> findAllBugs() {
		// createDatabaseFileIfMissing();

		// TODO: ich verstehs schon wieder mal nicht...
		// aus irgendwelchen gründen koennen alle bugs aus der ausgelesen werden
		// und werden auch in der Tabelle angezeigt wenn ich die DB nicht
		// schließe.
		// versuche ich das close aufzurufen wird ne exception geworfen,
		// lass ich die DB offen kommt beim speichern der tickets ne
		// exception...
		// ObjectContainer db = openDB();

		Collection<Bug> bugs = null;
		try {
			bugs = DB.queryByExample(Bug.class);

		} finally {
			// there is an exception thrown if i try to close the db...
			// WTF WHY??
			// db.close();
		}
		return bugs;

	}

	public void persist(Bug bug) {
		// ObjectContainer db = openDB();
		try {
			bug.setId("" + System.currentTimeMillis());
			DB.store(bug);
			DB.commit();
			System.out.println("Bug with id " + bug.getId() + " created at: " + bug.getCreationDateAsString());
		} finally {
			// db.close();
		}
	}

	public void update(Bug bug) {
		try {
			DB.store(bug);
			DB.commit();
			System.out.println("Bug with id: " + bug.getId() + " updated at: " + bug.getModificationDateAsString());

		} finally {
			// db.close()
		}

	}

	public void remove(Bug bug) {
		try {
			DB.delete(bug);
			DB.commit();
			
			//TODO Datum formatieren zentralisieren und in Abhängigkeit der Locale des benutzers formatieren
			DateTimeFormatter formatter = DateTimeFormat
					.forPattern("dd.MM.YYYY HH:mm:ss");
			System.out.println("Bug with id: " + bug.getId() + " deleted at: " + DateTime.now().toString(formatter));
		}finally {
			// db.close();
		}

	}

	private void createDBFileIfMissing() {
		File file = new File(FILENAME);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("could not create databasefile ");
		}

	}

	// private ObjectContainer openDB() {
	// return Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), FILENAME);
	// }

}
