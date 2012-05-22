package com.example.myminibugtracker;

import org.springframework.beans.factory.annotation.Configurable;

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

		mainWindow.addComponent(new BugList(this));

		// create actions buttonbar
		HorizontalLayout actionsButtonBar = new HorizontalLayout();
		actionsButtonBar.setSpacing(true);

		addBugButton = new Button(
				Messages.getString("ui.mainwindow.button.addBug"));
		addBugButton.addListener(this);
		removeBugButton = new Button(
				Messages.getString("ui.mainwindow.button.removeBug"));
		removeBugButton.addListener(this);
		editBugButton = new Button(
				Messages.getString("ui.mainwindow.button.editBug")); //$NON-NLS-1$
		editBugButton.addListener(this);

		actionsButtonBar.addComponent(addBugButton);
		actionsButtonBar.addComponent(removeBugButton);
		actionsButtonBar.addComponent(editBugButton);

		mainWindow.addComponent(actionsButtonBar);
		setMainWindow(mainWindow);
	}

	public DialogAndFormManager getDialogAndFormManager() {
		return this.dialogManager;
	}

	public BugService getBugService() {
		return this.bugService;
	}

	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		if (event.getButton() == addBugButton) {
			this.dialogManager.showNotification(Messages
					.getString("ui.mainwindow.notification.editBugClicked"),
					Notification.TYPE_TRAY_NOTIFICATION);
			VerticalLayout vl = new VerticalLayout();
			vl.addComponent(new BugForm(null, this));
			this.dialogManager
					.showDialog(
							Messages.getString("ui.mainwindow.notification.addBugClicked"), vl, "420px", "390px"); //$NON-NLS-1$
		} else if (event.getButton() == removeBugButton) {
			getDialogAndFormManager()
					.showNotification(
							Messages.getString("ui.mainwindow.notification.removeBugClicked"),
							Notification.TYPE_TRAY_NOTIFICATION);
		} else if (event.getSource() == editBugButton) {
			getDialogAndFormManager()
					.showNotification(
							Messages.getString("ui.mainwindow.notification.editBugClicked"),
							Notification.TYPE_TRAY_NOTIFICATION);
		}

	}

}
