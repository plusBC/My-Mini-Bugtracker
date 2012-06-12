package com.example.myminibugtracker.ui.forms;

import java.util.Arrays;

import com.example.myminibugtracker.MyminibugtrackerApplication;
import com.example.myminibugtracker.model.Bug;
import com.example.myminibugtracker.model.enums.BugStatus;
import com.example.myminibugtracker.model.enums.BugType;
import com.example.myminibugtracker.services.Messages;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;

/**
 * @author Fiederling Daniel
 * 
 */
public class BugForm extends AbstractForm {
	public BugForm(Bug bug, MyminibugtrackerApplication app) {
		super(app);

		String[] visiblePropertiesArray = { "title", "description" };
		if (bug == null) {
			bug = new Bug();
		}

		BugFieldFactory fieldFactory = new BugFieldFactory(this, bug);
		setFormFieldFactory(fieldFactory);

		setFormDataSource(bug, Arrays.asList(visiblePropertiesArray));
		ComboBox bugTypeBox = fieldFactory.createBugTypeSelect();
		addField("bugType", bugTypeBox);

		ComboBox bugStatusBox = fieldFactory.createBugStatusSelect();
		addField("status", bugStatusBox);

	}

	@Override
	protected void onClickSave() {
		// TODO: Hier wird momentan nur eine einzige Fehlermessage angezeigt...
		commit();
		BeanItem<Bug> item = (BeanItem<Bug>) getItemDataSource();
		Bug bug = item.getBean();

		ComboBox comboBox = (ComboBox) this.getField("status");
		bug.setStatus(comboBox.getValue().toString());

		comboBox = (ComboBox) this.getField("bugType");
		bug.setBugType(comboBox.getValue().toString());

		app.getBugService().save(bug);
		app.getDialogAndFormManager().hideDialog(this.getWindow());
		app.addBugToBuglist(bug);
	}

	@Override
	protected void onClickCancel() {
		discard();
		app.getDialogAndFormManager().hideDialog(this.getWindow());
	}

	class BugFieldFactory extends AbstractFieldFactory {

		BugForm bugForm;
		Bug bug;

		public BugFieldFactory(BugForm bugForm, Bug bug) {
			this.bugForm = bugForm;
			this.bug = bug;
		}

		@Override
		public Field createField(Item item, Object propertyId,
				Component uiContext) {

			if ("title".equals(propertyId)) {
				TextField title = createTextField(
						Messages.getString("ui.form.BugForm.caption.title"),
						true);
				title.setRequiredError("Title necessarry");
				return title;
			}

			if ("description".equals(propertyId)) {
				TextField textField = createTextField(
						Messages.getString("ui.form.BugForm.caption.description"),
						true);
				// ich kann leider hier keine TextArea verwenden, also
				// workaround
				textField.setRows(10);
				textField.setRequiredError("Description required");
				return textField;
			}

			return super.createField(item, propertyId, uiContext);
		}

		public ComboBox createBugTypeSelect() {
			// AUI hier bitte kein native select nehmen.. funktioniert das
			// "normale" nicht?
			// Plus: Welches "normale"? auf http://demo.vaadin.com/sampler wird
			// das doch auch verwendet! Warum sollte das nicht verwendet werden?
			// naja... ich nehm einfach mal die ComboBox
			ComboBox bugTypeSelect = createComboBox(Messages
					.getString("ui.form.BugForm.caption.type"));
			BugType[] bugTypes = BugType.values();
			for (BugType bugType : bugTypes) {
				bugTypeSelect.addItem(bugType);
				bugTypeSelect.setItemCaption(bugType, bugType.getTitle());
				if (this.bug != null && bug.getBugType() != null
						&& bug.getBugType().equals(bugType.toString())) {
					bugTypeSelect.select(bugType);
				}
			}
			return bugTypeSelect;
		}

		public ComboBox createBugStatusSelect() {
			// AUI hier bitte kein native select nehmen.. funktioniert das
			// "normale" nicht?
			// Plus: Welches "normale"? auf http://demo.vaadin.com/sampler wird
			// das doch auch verwendet! Warum sollte das nicht verwendet werden?
			// naja... ich nehm einfach mal die ComboBox
			ComboBox bugStatusSelect = createComboBox(Messages
					.getString("ui.form.BugForm.caption.state"));
			BugStatus[] bugStatus = BugStatus.values();
			for (BugStatus bugState : bugStatus) {
				bugStatusSelect.addItem(bugState);
				bugStatusSelect.setItemCaption(bugState, bugState.getTitle());
				if (this.bug != null && bug.getStatus() != null
						&& bug.getStatus().equals(bugState.toString())) {
					bugStatusSelect.select(bugState);
				}
			}
			return bugStatusSelect;
		}

	}

}
