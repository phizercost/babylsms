package com.phizercost.babylsms.ui.commons;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.phizercost.babylsms.model.user.User;
import com.phizercost.babylsms.service.getuserbyusername.GetUserByUsernameService;
import com.phizercost.babylsms.ui.factory.LoadFileLayoutFactory;
import com.phizercost.babylsms.ui.navigator.BabylSMSNavigator;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


@SuppressWarnings("serial")
@SpringUI(path=BabylSMSMainUI.NAME)
@Title("Babyl SMS")
@Theme("valo")
public class BabylSMSMainUI extends UI{
	
	public static final String NAME = "/ui";
	
	private User currentUser;
	private Panel changeTab = new Panel();
	
	@Autowired
	private BabylSMSLogoLayoutFactory babylSMSLogoLayoutFactory;
	
	@Autowired
	private BabylSMSMenuFactory babylSMSMenuFactory;
	
	@Autowired
	private BabylSMSAdminMenuFactory babylSMSAdminMenuFactory;
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	private SpringViewProvider viewProvider;
	
	@Autowired
	private GetUserByUsernameService getUserByUsernameService;
	
	@Override
	protected void init(VaadinRequest request) {
		
		changeTab.setHeight("100%");
		
		
		String currentUserName ="";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken))
			currentUserName = authentication.getName();
		
		VerticalLayout rootLayout = new VerticalLayout();
		rootLayout.setSizeFull();
		rootLayout.setMargin(true);
		
		Panel contentPanel = new Panel();
		contentPanel.setWidth("75%");
		contentPanel.setHeight("100%");
		
		Panel logoPanel = new Panel();
		logoPanel.setWidth("75%");
		logoPanel.setHeightUndefined();
		
		HorizontalLayout uiLayout = new HorizontalLayout();
		uiLayout.setSizeFull();
		uiLayout.setMargin(true);
		
		Component logo = babylSMSLogoLayoutFactory.createComponent();
		Component menu  = null;
		
		currentUser = getUserByUsernameService.getUserByUsername(currentUserName);
		
		if(currentUser.getUsertype().equalsIgnoreCase("Regular"))
			menu = babylSMSMenuFactory.createComponent();
		else
			menu = babylSMSAdminMenuFactory.createComponent();
		
		uiLayout.addComponent(menu);
		uiLayout.addComponent(changeTab);
		
		uiLayout.setComponentAlignment(changeTab, Alignment.TOP_CENTER);
		uiLayout.setComponentAlignment(menu, Alignment.TOP_CENTER);
		uiLayout.setExpandRatio(menu, 1);
		uiLayout.setExpandRatio(changeTab, 5);
		
		logoPanel.setContent(logo);
		
		contentPanel.setContent(uiLayout);
		
		rootLayout.addComponent(logoPanel);
		rootLayout.addComponent(contentPanel);
		rootLayout.setComponentAlignment(contentPanel, Alignment.MIDDLE_CENTER);
		rootLayout.setComponentAlignment(logoPanel, Alignment.TOP_CENTER);
		rootLayout.setExpandRatio(contentPanel, 1);
		
		initNavigator();
		
		setContent(rootLayout);
		
	}
	
	private void initNavigator(){
		BabylSMSNavigator navigator = new BabylSMSNavigator(this, changeTab);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(navigator);
		navigator.addProvider(viewProvider);
		//Initial navigation
		navigator.navigateTo(LoadFileLayoutFactory.NAME);
	}

}
