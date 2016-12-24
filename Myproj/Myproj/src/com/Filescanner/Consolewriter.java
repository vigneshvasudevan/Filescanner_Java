package com.Filescanner;

import java.io.IOException;

public class Consolewriter implements Filewriter{
	
	private Tree tree;
	
	public Consolewriter() throws IOException{
		tree=new Tree(Filescanner.filepath);
		WriteToFile();
	}
	public void WriteToFile(){
	
	}

}
