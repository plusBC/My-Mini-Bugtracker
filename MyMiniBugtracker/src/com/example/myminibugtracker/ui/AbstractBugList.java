package com.example.myminibugtracker.ui;

import com.example.myminibugtracker.MyminibugtrackerApplication;
import com.vaadin.data.Property;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Table;

/**
 * @author Fiederling Daniel
 * 
 */
public abstract class AbstractBugList extends Table {
	MyminibugtrackerApplication app;

	public AbstractBugList(MyminibugtrackerApplication app) {
		this.app = app;
		setSizeFull();

		/*
		 * Make table selectable, react immediatedly to user events, and pass
		 * events to the controller (our main application)
		 */
		setSelectable(true);
		setImmediate(true);
		// addListener((Property.ValueChangeListener) app);

		/* We don't want to allow users to de-select a row */
		setNullSelectionAllowed(false);

		// allow disabling column headers
		setColumnCollapsingAllowed(true);

		// allow disabling column headers
		setColumnReorderingAllowed(true);
		addListener(new MyItemClickListener());
		addListener(new MyValueChangeListener());
	}

	protected abstract void onDoubleClick(Object itemId);

	protected abstract void onSelectionChange(Object value);

	class MyItemClickListener implements ItemClickListener {
		public void itemClick(ItemClickEvent event) {
			if (event.isDoubleClick()) {
				onDoubleClick(event.getItemId());
			}

		}
	}

	class MyValueChangeListener implements ValueChangeListener {

		public void valueChange(Property.ValueChangeEvent event) {
			Object value = getValue();
			onSelectionChange(value);
		}

	}

}
