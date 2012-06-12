package com.example.myminibugtracker;

import org.springframework.beans.factory.annotation.Configurable;
import org.vaadin.dialogs.ConfirmDialog;

import com.example.myminibugtracker.model.Bug;
import com.example.myminibugtracker.services.BugService;
import com.example.myminibugtracker.services.BugServiceImpl;
import com.example.myminibugtracker.services.Messages;
import com.example.myminibugtracker.ui.BugList;
import com.example.myminibugtracker.ui.forms.BugForm;
import com.example.myminibugtracker.ui.managers.DialogAndFormManager;
import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;

@Configurable(preConstruction = true)
public class MyminibugtrackerApplication extends Application implements
		ClickListener {

	private DialogAndFormManager dialogManager;
	private Button addBugButton;
	private Button editBugButton;
	private Button removeBugButton;
	private BugService bugService;
	private BugList bugList;

	@Override
	public void init() {

		// prepare
		setLocale(((WebApplicationContext) getContext()).getBrowser()
				.getLocale());

		this.dialogManager = new DialogAndFormManager(this);
		// TODO: irgendwie mit Spring und Autowiring lösen
		this.bugService = new BugServiceImpl();

		Window mainWindow = new Window(
				Messages.getString("ui.mainwindow.title"));

		this.bugList = new BugList(this);
		mainWindow.addComponent(bugList);

		// create actions buttonbar
		HorizontalLayout actionsButtonBar = new HorizontalLayout();
		actionsButtonBar.setSpacing(true);

		addBugButton = new Button(
				Messages.getString("ui.mainwindow.button.addBug"));
		addBugButton.addListener(this);
		removeBugButton = new Button(
				Messages.getString("ui.mainwindow.button.removeBug"));
		removeBugButton.addListener(this);
		removeBugButton.setEnabled(false);
		editBugButton = new Button(
				Messages.getString("ui.mainwindow.button.editBug"));
		editBugButton.setEnabled(false);
		editBugButton.addListener(this);

		actionsButtonBar.addComponent(addBugButton);
		actionsButtonBar.addComponent(removeBugButton);
		actionsButtonBar.addComponent(editBugButton);

		mainWindow.addComponent(actionsButtonBar);
		setMainWindow(mainWindow);

	}
	
	public Button getEditBugButton(){
		return editBugButton;
	}
	
	public Button getRemoveBugButton(){
		return removeBugButton;
	}

	public DialogAndFormManager getDialogAndFormManager() {
		return this.dialogManager;
	}

	public BugService getBugService() {
		return this.bugService;
	}

	public void addBugToBuglist(Bug bug) {
		if (bugList.containsId(bug)) {
			bugList.removeItem(bug);
		}
		this.bugList.addItem(bug);
	}

	public void buttonClick(ClickEvent event) {

		if (event.getButton() == addBugButton) {
			// show add bug dialog
			VerticalLayout vl = new VerticalLayout();
			vl.addComponent(new BugForm(null, this));
			this.dialogManager.showDialog(
					Messages.getString("ui.form.BugForm.title.addBug"), vl,
					"420px", "390px");

			// show notification
			this.dialogManager.showNotification(Messages
					.getString("ui.mainwindow.notification.addBugClicked"),
					Notification.TYPE_TRAY_NOTIFICATION);

		} else if (event.getButton() == removeBugButton) {
			// show confirm dialog
			final Bug bug = (Bug) bugList.getValue();
			if (bug != null) {
				ConfirmDialog.show(getMainWindow(),

				Messages.getString("ui.confirm.deleteBug.caption"), Messages
						.getString("ui.confirm.deleteBug.message",
								new Object[] { bug.getTitle() }), Messages
						.getString("ui.confirm.deleteBug.okCaption"), Messages
						.getString("ui.confirm.deleteBug.cancelCaption"),
						new ConfirmDialog.Listener() {

							public void onClose(ConfirmDialog dialog) {
								if (dialog.isConfirmed()) {
									// Confirmed to continue
									getBugService().delete(bug);
									bugList.removeItem(bug);
								} else {
									// User did not confirm
								}
							}
						});
				// show notification
				this.dialogManager
						.showNotification(
								Messages.getString("ui.mainwindow.notification.removeBugClicked"),
								Notification.TYPE_TRAY_NOTIFICATION);
			}

		} else if (event.getSource() == editBugButton) {

			// show editBug Dialog
			Bug bug = (Bug) bugList.getValue();
			VerticalLayout vl = new VerticalLayout();
			vl.addComponent(new BugForm(bug, this));
			this.dialogManager.showDialog(
					Messages.getString("ui.form.BugForm.title.addBug"), vl,
					"420px", "390px");

			// show notification
			this.dialogManager.showNotification(Messages
					.getString("ui.mainwindow.notification.editBugClicked"),
					Notification.TYPE_TRAY_NOTIFICATION);
		}

	}
}
