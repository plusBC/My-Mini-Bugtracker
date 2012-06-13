package com.example.myminibugtracker.data;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

import com.example.myminibugtracker.model.Bug;
import com.example.myminibugtracker.model.enums.BugStatus;
import com.example.myminibugtracker.model.enums.BugType;
import com.example.myminibugtracker.services.Messages;
import com.vaadin.data.util.BeanItemContainer;

/*
 * The data source is what we in Vaadin call a Container. 
 * In this case we want to use a BeanItemContainer as the container 
 * for the data shown in the table. It can maintain a list of our Person 
 * objects and supply the table with information directly from the objects when needed. 
 */

public class BugContainer extends BeanItemContainer<Bug> implements
		Serializable {

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

	public static BugContainer createWithTestData() {
		
		// (AUI) wenn du mit Testdaten arbeiten möchtest, dann kannst du besser ein entsprechendes
		// 				"BugDaoSampleData" oder so anlegen, wo die Daten von unten verwendet werden
		//				Später tauscht man dann einfach das DAO gegen ein echtes aus und muss hier nix ändern!
		
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
				bug.setStatus(status[r.nextInt(status.length)].getTitle());
				bug.setCreationDate(creationDates[r
						.nextInt(creationDates.length)]);
				bug.setModificationDate(modificationDates[r
						.nextInt(modificationDates.length)]);
				bug.setBugType(bugTypes[r.nextInt(bugTypes.length)].getTitle());
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
