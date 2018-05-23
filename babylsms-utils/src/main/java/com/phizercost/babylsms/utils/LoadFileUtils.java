package com.phizercost.babylsms.utils;

public enum LoadFileUtils {
	
	LOAD_FILE("Load a New File"),
	SHOW_LOADED_FILES("Loaded Files"),
	
	FILE_NAME("File"),
	SAVE_BUTTON("Save"),
	CLEAR_BUTTON("Clear"), SHOW_LOADED_CUSTOMERS("Customers");
	
	private final String string;
	
	private LoadFileUtils(String string) {
		this.string = string;
	}
	
	public String getString() {
		return this.string;
	}

}
