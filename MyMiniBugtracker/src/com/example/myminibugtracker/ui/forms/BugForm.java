package com.example.myminibugtracker.ui.forms;

import java.util.Arrays;

import com.example.myminibugtracker.MyminibugtrackerApplication;
import com.example.myminibugtracker.model.Bug;
import com.vaadin.data.util.BeanItem;

/**
 * @author Fiederling Daniel
 * 
 */
public class BugForm extends AbstractForm {
	public BugForm(Bug bug, MyminibugtrackerApplication app) {
		super(app);

		String[] visiblePropertiesArray = { "title", "description", "bugType",
				"status" };
		BugFieldFactory fieldFactory = new BugFieldFactory(this);
		setFormFieldFactory(fieldFactory);
		if (bug == null) {
			bug = new Bug();
		}
		setFormDataSource(bug, Arrays.asList(visiblePropertiesArray));

		// addField("bugType", fieldFactory.createBugTypeSelect());
		// addField("status", fieldFactory.createBugStatusSelect());

	}

	@Override
	protected void onClickSave() {
		// TODO: Hier wird momentan nur eine einzige Fehlermessage angezeigt...
		commit();
		BeanItem<Bug> item = (BeanItem<Bug>) getItemDataSource();
		Bug bug = item.getBean();
//		ComboBox comboBox = (ComboBox) this.getField("bugType");
//		bug.setBugType(((BugType) comboBox.getValue()).getTitle());
//
//		comboBox = (ComboBox) this.getField("status");
//		bug.setStatus(((BugStatus) comboBox.getValue()).getTitle());

		app.getBugService().save(bug);
		app.getDialogAndFormManager().hideDialog(this.getWindow());
		app.addBugToBuglist(bug);
	}

	@Override
	protected void onClickCancel() {
		discard();
		app.getDialogAndFormManager().hideDialog(this.getWindow());
	}

}
