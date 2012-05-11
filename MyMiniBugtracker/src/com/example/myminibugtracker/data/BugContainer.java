package com.example.myminibugtracker.data;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

import com.example.myminibugtracker.model.Bug;
import com.example.myminibugtracker.model.enums.BugStatus;
import com.example.myminibugtracker.model.enums.BugType;
import com.vaadin.data.util.BeanItemContainer;

/*
 * The data source is what we in Vaadin call a Container. 
 * In this case we want to use a BeanItemContainer as the container 
 * for the data shown in the table. It can maintain a list of our Person 
 * objects and supply the table with information directly from the objects when needed. 
 */

public class BugContainer extends BeanItemContainer<Bug> implements
		Serializable {

	/**
	 * Natural property order for Person bean. Used in tables and forms. Name
	 * the variables as in Person.class!
	 */
	// AUI Diese Auflistung hast du von Zdenko übernommen, oder? Das gefiel mir bei ihm schon nicht
	// so, weil die Reihenfolge der Felder etc ist ja Aufgabe der Form, nicht das Datencontainers!
	public static final Object[] NATURAL_COL_ORDER = new Object[] { "title",
			"bugType", "status", "creationDate", "modificationDate" };

	/**
	 * "Human readable" captions for properties in same order as in NATURAL_COL_ORDER.
	 */
	// AUI kann man die Bereitstellung dieser Texte verbessern? Hard-coded Strings sind nicht so mein
	// Ding und ich finde die Annahme, dass die Reihenfolge der Felder mit der Reiohenfolge der
	// Übersetzungen übereinstimmt zu fehleranfällig
	public static final String[] COL_HEADERS = new String[] { "Title", "Type", "State", "Creation Date",
			"Modification Date" };

	public BugContainer() throws InstantiationException, IllegalAccessException {
		super(Bug.class);
	}

	public static BugContainer create() {
		try {
			return new BugContainer();
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getStackTrace().toString());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getStackTrace().toString());
		}

	}

//	public static BugContainer create(List<Bug> bugs) {
//		try {
//			BugContainer bugContainer = new BugContainer();
//			bugContainer.addAll(bugs);
//			return bugContainer;
//
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e.getStackTrace().toString());
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e.getStackTrace().toString());
//		}
//
//	}

	public static BugContainer createWithTestData() {
		final String[] titles = { "Titel 1", "Titel 2", "Titel 3", "Titel 4",
				"Titel 5" };
		// final String[] descriptions = { "description 1", "description 2",
		// "description 3", "description 4", "description 5" };

		final BugStatus[] status = BugStatus.values();
		final BugType[] bugTypes = BugType.values();

		long currentTime = System.currentTimeMillis();
		final long creationDates[] = { currentTime, currentTime + 86400000,
				currentTime + 172800000, currentTime + 259200000,
				currentTime + 345600000, currentTime + 432000000 };

		final long modificationDates[] = { currentTime + 518400000,
				currentTime + 604800000, currentTime + 691200000,
				currentTime + 777600000, currentTime + 864000000,
				currentTime + 950400000 };

		BugContainer c = null;
		Random r = new Random(0);
		try {
			c = new BugContainer();
			for (int i = 0; i < 100; i++) {
				Bug bug = new Bug();
				bug.setTitle(titles[r.nextInt(titles.length)]);
				// bug.setDescription(descriptions[r.nextInt(descriptions.length)]);
				bug.setStatus(status[r.nextInt(status.length)]);
				bug.setCreationDate(creationDates[r
						.nextInt(creationDates.length)]);
				bug.setModificationDate(modificationDates[r
						.nextInt(modificationDates.length)]);
				bug.setBugType(bugTypes[r.nextInt(bugTypes.length)]);
				c.addItem(bug);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getStackTrace().toString());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getStackTrace().toString());
		}

		return c;
	}

}
