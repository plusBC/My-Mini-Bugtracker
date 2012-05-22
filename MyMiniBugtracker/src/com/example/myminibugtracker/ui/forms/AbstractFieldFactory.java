package com.example.myminibugtracker.ui.forms;

import java.awt.TextArea;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.TextField;

public abstract class AbstractFieldFactory extends DefaultFieldFactory {

	protected TextField createTextField(String caption, boolean isRequired) {
		TextField field = new TextField(caption);
		field.setNullRepresentation("");
		field.setWidth("300px");
		if (isRequired){
			field.setRequired(isRequired);
		}
		return field;
	}

	protected TextArea createTextArea(String caption) {
		TextArea area = new TextArea(caption);
		area.setSize(300, 500);
		return area;
	}

	protected TextField createIntegerField(String caption) {
		TextField field = new TextField(caption);
		field.setWidth("40px");
		return field;
	}

	public ComboBox createComboBox(String caption) {
		ComboBox comboBox = new ComboBox(caption);
		comboBox.setImmediate(true);
		comboBox.setWidth("300px");
		comboBox.setNullSelectionAllowed(false);
		comboBox.setNewItemsAllowed(false);
		comboBox.setRequired(true);
		return comboBox;
	}

}
