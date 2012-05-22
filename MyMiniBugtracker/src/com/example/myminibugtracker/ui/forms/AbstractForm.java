package com.example.myminibugtracker.ui.forms;

import com.example.myminibugtracker.MyminibugtrackerApplication;
import com.example.myminibugtracker.services.Messages;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;

public abstract class AbstractForm extends Form {
	protected MyminibugtrackerApplication app;
	Button save;
	Button cancel;

	public AbstractForm(MyminibugtrackerApplication app) {
		this.app = app;

		ClickListener myButtonClickListener = new MyButtonClickListener();
		save = new Button(Messages.getString("ui.button.save"), myButtonClickListener); 
		cancel = new Button(Messages.getString("ui.button.cancel"), myButtonClickListener); 

		HorizontalLayout hl = new HorizontalLayout();
		hl.setSpacing(true);
		hl.addComponent(save);
		hl.addComponent(cancel);
		setFooter(hl);

		setWriteThrough(false);
	}

	protected abstract void onClickSave();

	protected abstract void onClickCancel();

	class MyButtonClickListener implements ClickListener {

		public void buttonClick(ClickEvent event) {
			if (event.getSource() == save) {
				onClickSave();
			} else if (event.getSource() == cancel) {
				onClickCancel();
			}

		}

	}

}
