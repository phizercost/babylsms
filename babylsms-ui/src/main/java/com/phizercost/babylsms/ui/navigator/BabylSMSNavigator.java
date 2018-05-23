package com.phizercost.babylsms.ui.navigator;

import com.google.gwt.thirdparty.guava.common.base.Strings;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.SingleComponentContainer;
import com.vaadin.ui.UI;


public class BabylSMSNavigator extends Navigator{

	public BabylSMSNavigator(UI ui, SingleComponentContainer container) {
		super(ui, container);
	}
	
	private static BabylSMSNavigator getNavigator(){
		UI ui = UI.getCurrent();
		Navigator navigator = ui.getNavigator();
		return (BabylSMSNavigator) navigator;
	}
	
	public static void navigate(String path){
		try{
			//System.out.println(path);
			BabylSMSNavigator.getNavigator().navigateTo(path);
		}catch(Exception e){
			return;
		}
	}
	
	@Override
	public void navigateTo(String viewName){
		super.navigateTo(Strings.nullToEmpty(viewName));
	}

}
