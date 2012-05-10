package com.example.myminibugtracker;

import com.vaadin.Application;
import com.vaadin.ui.*;

public class MyminibugtrackerApplication extends Application {
	@Override
	public void init() {
		Window mainWindow = new Window("Myminibugtracker Application");
		Label label = new Label("Hello Vaadin user");
		mainWindow.addComponent(label);
		setMainWindow(mainWindow);
	}

}
