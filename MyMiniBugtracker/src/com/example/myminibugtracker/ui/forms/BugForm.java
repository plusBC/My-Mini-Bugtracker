package com.example.myminibugtracker.ui.forms;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

import com.example.myminibugtracker.MyminibugtrackerApplication;
import com.example.myminibugtracker.model.Bug;
import com.example.myminibugtracker.model.enums.BugStatus;
import com.example.myminibugtracker.model.enums.BugType;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.UserError;
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

		BugFieldFactory fieldFactory = new BugFieldFactory(bug);
		setFormFieldFactory(fieldFactory);

		setFormDataSource(bug, Arrays.asList(visiblePropertiesArray));
		ComboBox bugTypeBox = fieldFactory.createBugTypeSelect();
		addField("bugType", bugTypeBox);

		ComboBox bugStatusBox = fieldFactory.createBugStatusSelect();
		addField("status", bugStatusBox);

	}

	@Override
	protected boolean validateForm() {
		boolean valid = true;
		valid &= validateTitle();
		valid &= validateDescription();
		valid &= validateStatus();
		valid &= validateBugType();
		return valid;
	}

	private boolean validateTitle() {
		boolean valid = true;
		TextField title = (TextField) getField("title");
		String titleValue = (String) title.getValue();
		if (StringUtils.isBlank(titleValue)) {
			title.setComponentError(new UserError(this.app.getRM().message(
					"ui.form.BugForm.error.titleEmpty")));
			valid = false;
		}

		if (valid) {
			title.setComponentError(null);
		}

		return valid;
	}

	private boolean validateDescription() {
		boolean valid = true;
		TextField description = (TextField) getField("description");
		String descriptionValue = (String) description.getValue();
		if (StringUtils.isBlank(descriptionValue)) {
			description.setComponentError(new UserError(this.app.getRM()
					.message("ui.form.BugForm.error.descriptionEmpty")));
			valid = false;
		}

		if (valid) {
			description.setComponentError(null);
		}

		return valid;
	}

	private boolean validateStatus() {
		boolean valid = true;
		ComboBox status = (ComboBox) getField("status");
		BugStatus statusValue = (BugStatus) status.getValue();
		if (statusValue == null) {
			status.setComponentError(new UserError(this.app.getRM().message(
					"ui.form.BugForm.error.statusEmpty")));
			valid = false;
		}

		if (valid) {
			status.setComponentError(null);
		}
		return valid;
	}

	private boolean validateBugType() {
		boolean valid = true;
		ComboBox bugType = (ComboBox) getField("bugType");
		BugType bugTypeValue = (BugType) bugType.getValue();
		if (bugTypeValue == null) {
			bugType.setComponentError(new UserError(this.app.getRM().message(
					"ui.form.BugForm.error.bugTypeEmpty")));
			valid = false;
		}

		if (valid) {
			bugType.setComponentError(null);
		}
		return valid;
	}

	@Override
	protected void onClickSave() {

		if (validateForm()) {
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
	}

	@Override
	protected void onClickCancel() {
		discard();
		app.getDialogAndFormManager().hideDialog(this.getWindow());
	}

	class BugFieldFactory extends AbstractFieldFactory {

		BugForm bugForm;
		Bug bug;

		// (AUI) Ich meine, du brauchst hier die Referenz auf BugForm nicht, da
		// es ja eine innere Klasse ist.
		// http://openbook.galileodesign.de/javainsel5/javainsel06_011.htm#Rxx747java06011040001EC1F02610D
		public BugFieldFactory(Bug bug) {
			this.bug = bug;
		}

		@Override
		public Field createField(Item item, Object propertyId,
				Component uiContext) {

			if ("title".equals(propertyId)) {
				TextField title = createTextField(
						app.getRM().message("ui.form.BugForm.caption.title"),
						true);
				return title;
			}

			if ("description".equals(propertyId)) {
				TextField textField = createTextField(
						app.getRM().message(
								"ui.form.BugForm.caption.description"), true);
				// ich kann leider hier keine TextArea verwenden, also
				// workaround
				textField.setRows(10);
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
			ComboBox bugTypeSelect = createComboBox(app.getRM().message(
					"ui.form.BugForm.caption.type"));
			BugType[] bugTypes = BugType.values();
			for (BugType bugType : bugTypes) {
				bugTypeSelect.addItem(bugType);
				bugTypeSelect.setItemCaption(bugType,
						app.getRM().enumMessage(bugType));
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
			ComboBox bugStatusSelect = createComboBox(app.getRM().message(
					"ui.form.BugForm.caption.state"));
			BugStatus[] bugStatus = BugStatus.values();
			for (BugStatus bugState : bugStatus) {
				bugStatusSelect.addItem(bugState);
				bugStatusSelect.setItemCaption(bugState, app.getRM()
						.enumMessage(bugState));
				if (this.bug != null && bug.getStatus() != null
						&& bug.getStatus().equals(bugState.toString())) {
					bugStatusSelect.select(bugState);
				}
			}
			return bugStatusSelect;
		}

	}

}
