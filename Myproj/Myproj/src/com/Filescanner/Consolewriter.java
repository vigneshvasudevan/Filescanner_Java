package com.Filescanner;

import java.io.IOException;

public class Consolewriter implements Filewriter{
	
	private Tree tree;
	
	public Consolewriter() throws IOException{
		tree=new Tree(Filescanner.filepath);
		WriteToFile();
	}
	public void WriteToFile(){
		consolewriter(tree);
	}
	
	private void consolewriter(Tree tree){
		System.out.println(tree.node.Attributes);
		if(tree.children!=null)
			for(Tree child: tree.children)
				consolewriter(child);
	}

}
