package com.phizercost.babylsms.ui.factory;

import org.springframework.beans.factory.annotation.Autowired;

import com.phizercost.babylsms.ui.commons.BabylSMSMainUI;
import com.phizercost.babylsms.utils.LoadFileUtils;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = TestBroadcastLayoutFactory.NAME, ui = BabylSMSMainUI.class)
public class TestBroadcastLayoutFactory extends VerticalLayout implements View {
	public static final String NAME = "test";

	@Autowired
	private TestBroadcastMainLayoutFactory sendBroadcastMainLayoutFactory;

	private void addLayout() {
		setMargin(true);
		Component sendMessage = sendBroadcastMainLayoutFactory.createComponent();
		addComponent(sendMessage);
	}

	public void enter(ViewChangeEvent event) {
		removeAllComponents();
		addLayout();
	}

}
