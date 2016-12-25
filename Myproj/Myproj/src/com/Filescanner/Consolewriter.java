package com.Filescanner;

import java.io.IOException;

import com.Filescanner.Node.Systemprops;

public class Consolewriter implements Filewriter{
	
	public Tree tree;
	
	public Consolewriter() throws IOException{
		tree=new Tree(Filescanner.filepath);
	}
	
	@Override
	public void WriteToFile(){
		consolewriter(tree);
	}
	
	private void consolewriter(Tree tree){
		System.out.println(tree.node.Attributes);
		if(tree.children!=null)
			for(Tree child: tree.children){
				consolewriter(child);
			}
	}
	
	@Override
	public void Search(Tree tree,Systemprops key,String value) {
		if( (tree.node.Attributes.containsKey(key)) && (tree.node.Attributes.get(key).contains(value))){
			System.out.println(tree.node.Attributes);
			if(tree.children!=null)
				for(Tree child: tree.children){
					Search(child,key,value);
				}
		}
	}

}
