package com.Mock;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Mock {

	public enum Systemprops{
		NAME,
		CREATIONTIME,
		LASTACCESSTIME,
		LASTMODIFIEDTIME,
		ISDIRECTORY,
		ISREGULARFILE,
		SIZEINBYTES,
		ISFILEABOVE1KB
	}
	public static void main(String[] args) {
		
		
		HashMap<Systemprops,String> var=new LinkedHashMap<Systemprops,String>();
		var.put(Systemprops.NAME, "slkgngl");
		var.put(Systemprops.NAME, "hi");
		var.put(Systemprops.NAME,"hello");
		/*if(var.get("dummmy").toString().contains("mock"))
			System.out.println("Pass");
		else
			System.out.println("Fail");*/
		
		System.out.println(var.get(Systemprops.NAME).contains("Hello"));
	}

}
