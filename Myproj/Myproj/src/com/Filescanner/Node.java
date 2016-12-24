package com.Filescanner;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Node {

	public enum SystemNode{
		NAME,
		CREATIONTIME,
		LASTACCESSTIME,
		LASTMODIFIEDTIME,
		ISDIRECTORY,
		ISREGULARFILE,
		SIZEINBYTES,
		ISFILEABOVE1KB
	}
	public HashMap<SystemNode,String> Attributes=new LinkedHashMap<SystemNode,String>();
	
	
}
