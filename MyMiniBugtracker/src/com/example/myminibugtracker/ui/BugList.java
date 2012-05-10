package com.example.myminibugtracker.ui;

import java.util.List;

import com.example.myminibugtracker.MyminibugtrackerApplication;
import com.example.myminibugtracker.data.BugContainer;
import com.example.myminibugtracker.model.Bug;

/**
 * 
 * @author Fiederling Daniel
 * 
 */
public class BugList extends AbstractBugList {

	public BugList(MyminibugtrackerApplication app) {
		super(app);

		// create some dummy data
		List<Bug> allBugs = this.app.getBugService().getAll();
		BugContainer bugContainer;
		try {
			bugContainer = new BugContainer();
			bugContainer.addAll(allBugs);
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getStackTrace().toString());
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e.getStackTrace().toString());
		}
		setContainerDataSource(bugContainer);

		//
		setVisibleColumns(BugContainer.NATURAL_COL_ORDER);
		setColumnHeaders(BugContainer.COL_HEADERS);
		addListener(new MyItemClickListener());

	}

	@Override
	protected void onDoubleClick(Object itemId) {
		// new GameForm((Game) itemId, app);
	}

}