package com.example.myminibugtracker.ui;

import java.util.Collection;

import org.joda.time.DateTimeZone;

import com.example.myminibugtracker.MyminibugtrackerApplication;
import com.example.myminibugtracker.data.BugContainer;
import com.example.myminibugtracker.model.Bug;
import com.example.myminibugtracker.model.enums.BugStatus;
import com.example.myminibugtracker.model.enums.BugType;
import com.example.myminibugtracker.ui.forms.BugForm;
import com.vaadin.data.Property;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.terminal.gwt.server.WebBrowser;
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
		final String[] VISIBLE_COL = new String[] { "title", "bugType",
				"status", "creationTimeStamp", "modificationTimeStamp" };
		setVisibleColumns(VISIBLE_COL);

		// set column headers
		final String[] COL_HEADERS = new String[] {
				this.app.getRM().message("ui.mainwindow.colHeaders.title"),
				this.app.getRM().message("ui.mainwindow.colHeaders.type"),
				this.app.getRM().message("ui.mainwindow.colHeaders.state"),
				this.app.getRM().message(
						"ui.mainwindow.colHeaders.creationDate"),
				this.app.getRM().message(
						"ui.mainwindow.colHeaders.modificationDate") };
		setColumnHeaders(COL_HEADERS);

	}

	@Override
	protected void onDoubleClick(Object itemId) {

		// show editBug dialog
		VerticalLayout vl = new VerticalLayout();
		vl.addComponent(new BugForm((Bug) itemId, app));
		app.getDialogAndFormManager().showDialog(
				this.app.getRM().message("ui.form.BugForm.title.addBug"), vl,
				"420px", "390px");

		// show notification
		app.getDialogAndFormManager().showNotification("bug double clicked",
				Notification.TYPE_TRAY_NOTIFICATION);

	}

	@Override
	protected void onSelectionChange(Object value) {

		if (value != null) {
			this.app.getEditBugButton().setEnabled(true);
			this.app.getRemoveBugButton().setEnabled(true);
		} else {
			this.app.getEditBugButton().setEnabled(false);
			this.app.getRemoveBugButton().setEnabled(false);
		}

	}

	@Override
	protected String formatPropertyValue(Object rowId, Object colId,
			Property property) {
		if (colId.equals("bugType")) {
			for (BugType bugTypeValue : BugType.values()) {
				if (property.getValue() != null
						&& property.getValue().equals(bugTypeValue.toString())) {

					return this.app.getRM().enumMessage(bugTypeValue);
				}
			}
			return null;
		}

		if (colId.equals("status")) {
			for (BugStatus statusValue : BugStatus.values()) {
				if (property.getValue() != null
						&& property.getValue().equals(statusValue.toString())) {

					return this.app.getRM().enumMessage(statusValue);
				}
			}
			return null;
		}

		if (colId.equals("creationTimeStamp")) {
			Long creationTimeStamp = (Long) property.getValue();
			return getLocaleDateForTimeStamp(creationTimeStamp);

		}

		if (colId.equals("modificationTimeStamp")) {
			Long modificationTimeStamp = (Long) property.getValue();
			return getLocaleDateForTimeStamp(modificationTimeStamp);
		}

		return super.formatPropertyValue(rowId, colId, property);
	}

	//TODO: in einen Service umziehen
	public String getLocaleDateForTimeStamp(Long timestamp) {

		if (timestamp == 0) {
			timestamp = null;
		}

		return this.app.getRM().formatDateTime(timestamp, "unknown",
				this.app.getLocale(), DateTimeZone.forID("Europe/Berlin"));
	}

}