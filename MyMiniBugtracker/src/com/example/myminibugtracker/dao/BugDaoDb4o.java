package com.example.myminibugtracker.dao;

import java.util.Collection;
import org.springmodules.db4o.support.Db4oDaoSupport;
import com.example.myminibugtracker.model.Bug;

public class BugDaoDb4o extends Db4oDaoSupport implements BugDao {

	public Collection<Bug> findAllBugs() {
		return getDb4oTemplate().get(Bug.class);

	}

	public void persist(Bug bug) {
		bug.setId(""+System.currentTimeMillis());
		getDb4oTemplate().set(bug);

	}

	public void update(Bug bug) {
		getDb4oTemplate().set(bug);

	}

	public void remove(Bug bug) {
		getDb4oTemplate().delete(bug);
		// try {
		// DB.delete(bug);
		// DB.commit();
		//
		// //TODO Datum formatieren zentralisieren und in Abhängigkeit der Locale des benutzers formatieren
		// // (AUI) Solche Ausgaben werden über einen Logger gemacht. Der kann konfiguriert werden, dass der Zeitpunkt
		// // mitgeloggt wird. Also kein DateFormatter hier!
		// DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.YYYY HH:mm:ss");
		// System.out.println("Bug with id: " + bug.getId() + " deleted at: " +
		// DateTime.now().toString(formatter));
		// }finally {
		// // db.close();
		// }

	}

}
