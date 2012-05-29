package com.example.myminibugtracker.ui.forms;

import com.example.myminibugtracker.model.enums.BugStatus;
import com.example.myminibugtracker.model.enums.BugType;
import com.example.myminibugtracker.services.Messages;
import com.vaadin.data.Item;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;

// AUI Nach Überlegung sehe ich mittlerweile eine FieldFactory eigentlich mehr als innere Klasse der entsprechenden Form. Vielleicht kannst du in die Richtung was ausprobieren.
public class BugFieldFactory extends AbstractFieldFactory {

	BugForm bugForm;

	public BugFieldFactory(BugForm bugForm) {
		this.bugForm = bugForm;
	}

	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {

		if ("title".equals(propertyId)) {
			TextField title = createTextField(
					Messages.getString("ui.form.BugForm.caption.title"),
					true);
			title.setRequiredError("Title necessarry");
			return title;
		}

		if ("description".equals(propertyId)) {
			TextField textField = createTextField(
					Messages.getString("ui.form.BugForm.caption.description"), true); 
			// ich kann leider hier keine TextArea verwenden, also workaround
			textField.setRows(10);
			textField.setRequiredError("Description required");
			return textField;
		}

		if ("bugType".equals(propertyId)) {
			return createBugTypeSelect();
		}

		if ("status".equals(propertyId)) {
			return createBugStatusSelect();
		}

		return super.createField(item, propertyId, uiContext);
	}

	public ComboBox createBugTypeSelect() {
		// AUI hier bitte kein native select nehmen.. funktioniert das "normale"
		// nicht?
		// Plus: Welches "normale"? auf http://demo.vaadin.com/sampler wird das
		// doch auch verwendet! Warum sollte das nicht verwendet werden? naja...
		// ich nehm einfach mal die ComboBox
		ComboBox bugTypeSelect = createComboBox(Messages
				.getString("ui.form.BugForm.caption.type"));
		BugType[] bugTypes = BugType.values();
		for (BugType bugType : bugTypes) {
			bugTypeSelect.addItem(bugType);
			bugTypeSelect.setItemCaption(bugType, bugType.getTitle());
		}
		return bugTypeSelect;
	}

	public ComboBox createBugStatusSelect() {
		// AUI hier bitte kein native select nehmen.. funktioniert das "normale"
		// nicht?
		ComboBox bugStatusSelect = createComboBox(Messages
				.getString("ui.form.BugForm.caption.state"));
		BugStatus[] bugStatus = BugStatus.values();
		for (BugStatus bugState : bugStatus) {
			bugStatusSelect.addItem(bugState);
			bugStatusSelect.setItemCaption(bugState, bugState.getTitle());
		}
		return bugStatusSelect;
	}

}
