package com.example.myminibugtracker.ui;

import java.util.Collection;

import com.example.myminibugtracker.MyminibugtrackerApplication;
import com.example.myminibugtracker.data.BugContainer;
import com.example.myminibugtracker.model.Bug;
import com.example.myminibugtracker.services.Messages;
import com.example.myminibugtracker.ui.forms.BugForm;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;

/**
 * 
 * @author Fiederling Daniel
 * 
 */
public class BugList extends AbstractBugList {

	public BugList(MyminibugtrackerApplication app) {
		super(app);

		// load data and add it to datacontainer
		Collection<Bug> allBugs = this.app.getBugService().getAll();
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

		// set visible columns
		final String[] VISIBLE_COL = new String[] { "title", "bugTypeTitle",
				"statusTitle", "creationDateAsString", "modificationDateAsString" };
		setVisibleColumns(VISIBLE_COL);

		// set column headers
		final String[] COL_HEADERS = new String[] {
				Messages.getString("ui.mainwindow.colHeaders.title"),
				Messages.getString("ui.mainwindow.colHeaders.type"),
				Messages.getString("ui.mainwindow.colHeaders.state"),
				Messages.getString("ui.mainwindow.colHeaders.creationDate"),
				Messages.getString("ui.mainwindow.colHeaders.modificationDate") };
		setColumnHeaders(COL_HEADERS);

	}

	@Override
	protected void onDoubleClick(Object itemId) {

		//show editBug dialog
		VerticalLayout vl = new VerticalLayout();
		vl.addComponent(new BugForm((Bug) itemId, app));
		app.getDialogAndFormManager().showDialog(
				Messages.getString("ui.form.BugForm.title.addBug"), vl,
				"420px", "390px");
		
		//show notification
		app.getDialogAndFormManager().showNotification("bug double clicked",
				Notification.TYPE_TRAY_NOTIFICATION);

	}

}