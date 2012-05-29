package com.example.myminibugtracker.ui.managers;

import com.example.myminibugtracker.MyminibugtrackerApplication;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Window;

/**
 * @author Fiederling Daniel
 * 
 */
public class DialogAndFormManager {

	MyminibugtrackerApplication app;

	public DialogAndFormManager(MyminibugtrackerApplication app) {
		this.app = app;
	}

	public void showDialog(String caption, Layout layout, String width,
			String height) {
		Window window = new Window(caption, layout);
		window.setWidth(width);
		window.setHeight(height);
		window.center();
		this.app.getMainWindow().addWindow(window);

	}
	
	public void showYesNoDialog(String caption){
		
	}

	public void hideDialog(Window windowToRemove) {
		app.getMainWindow().removeWindow(windowToRemove);
	}

	public void showNotification(String caption, final int notificationType) {
		this.app.getMainWindow().showNotification(caption, notificationType);
	}

}
