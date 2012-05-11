package com.example.myminibugtracker.ui.forms;

import com.example.myminibugtracker.data.BugContainer;
import com.example.myminibugtracker.model.enums.BugStatus;
import com.example.myminibugtracker.model.enums.BugType;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.NativeSelect;
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
			return createTextField(BugContainer.COL_HEADERS[0], true);
		}
		
		if("description".equals(propertyId)){
			TextField textField = createTextField("Description", true);
			// ich kann leider hier keine TextArea verwenden, also workaround
			textField.setRows(10);
			return textField;
		}


		return super.createField(item, propertyId, uiContext);
	}

	public NativeSelect createBugTypeSelect() {
		// AUI hier bitte kein native select nehmen.. funktioniert das "normale" nicht?
		NativeSelect bugTypeSelect = createNativeSelect(BugContainer.COL_HEADERS[1]);
		BugType[] bugTypes = BugType.values();
		for (BugType bugType : bugTypes) {
			bugTypeSelect.addItem(bugType);
			bugTypeSelect.setItemCaption(bugType, bugType.getTitle());
		}
		return bugTypeSelect;
	}

	public NativeSelect createBugStatusSelect() {
		// AUI hier bitte kein native select nehmen.. funktioniert das "normale" nicht?
		NativeSelect bugStatusSelect = createNativeSelect(BugContainer.COL_HEADERS[2]);
		BugStatus[] bugStatus = BugStatus.values();
		for (BugStatus bugState : bugStatus) {
			bugStatusSelect.addItem(bugState);
			bugStatusSelect.setItemCaption(bugState, bugState.getTitle());
		}
		return bugStatusSelect;
	}

}
