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

		// create some dummy data
		Collection<Bug> allBugs = this.app.getBugService().getAll();
		BugContainer bugContainer;
		try {
			bugContainer = new BugContainer();
			bugContainer.addAll(allBugs);
			bugContainer.addListener(this);
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
		//Plus scheint so als gäbe es einen Bug in Vaadin, der Doppelklick wird zweimal aufgerufen...
		app.getDialogAndFormManager().showNotification("bug double clicked",
				Notification.TYPE_TRAY_NOTIFICATION);
		VerticalLayout vl = new VerticalLayout();
		vl.addComponent(new BugForm((Bug) itemId, app));
		app.getDialogAndFormManager().showDialog(
				Messages.getString("ui.form.BugForm.title.addBug"), vl,
				"420px", "390px");
	}

}