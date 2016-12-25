package com.Filescanner;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Node {

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
	public HashMap<Systemprops,String> Attributes=new LinkedHashMap<Systemprops,String>();
	
	
}
