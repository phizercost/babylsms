package com.phizercost.babylsms.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DownloadFile {
	
	public static void download(ArrayList<String> array, String filename) throws IOException{
		
		BufferedWriter br;

		br = new BufferedWriter(new FileWriter(System.getProperty("user.home")+"/"+filename));
		
		StringBuilder sb = new StringBuilder();
		
		for (String element : array) {
		 sb.append(element);
		 sb.append("\n");
		}

		br.write(sb.toString());
		br.close();
		
	}

}
