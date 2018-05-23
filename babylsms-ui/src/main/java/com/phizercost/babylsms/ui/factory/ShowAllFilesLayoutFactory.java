package com.phizercost.babylsms.ui.factory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.phizercost.babylsms.model.file.FileObject;
import com.phizercost.babylsms.service.showallfiles.ShowAllFilesService;
import com.phizercost.babylsms.ui.commons.UIComponentBuilder;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class ShowAllFilesLayoutFactory implements UIComponentBuilder{

	private List<FileObject> files;
	private BeanItemContainer<FileObject> container;
	
	private class ShowAllFilesLayout extends VerticalLayout{
		private Grid filesTable;
		
		public ShowAllFilesLayout init(){
			setMargin(true);
			
			container = new BeanItemContainer<FileObject>(FileObject.class, files);
			filesTable = new Grid(container);
			filesTable.setColumnOrder("fileName");
			filesTable.removeColumn("id");
			filesTable.removeColumn("status");
			filesTable.setImmediate(true);
			filesTable.setHeight("100%");
			filesTable.setWidth("100%");
	
			return this;
		}
		
		public ShowAllFilesLayout load(){
			files = showAllFilesService.getAllFiles();
			return this;
		}
		
		public ShowAllFilesLayout layout(){
			addComponent(filesTable);
			return this;
		}
	}
	
	@Autowired
	private ShowAllFilesService showAllFilesService;
	
	public Component createComponent(){
		return new ShowAllFilesLayout().init().load().layout();
	}

	public void refreshTable() {
		files = showAllFilesService.getAllFiles();
		container.removeAllItems();
		container.addAll(files);
	}
	

}
