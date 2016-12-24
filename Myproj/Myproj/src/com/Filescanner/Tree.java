package com.Filescanner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;

import com.Filescanner.Node.SystemNode;


public class Tree {
	
	public Node node ;
	public List<Tree> children;
	
	public Tree(String FP) throws IOException{
		node=new Node();
		getfileattribute(FP);
	}
	
	private void getfileattribute(String FP) throws IOException{
		Path Filepath= Paths.get(FP);
		BasicFileAttributes attr =Files.readAttributes(Filepath, BasicFileAttributes.class);

		node.Attributes.put(SystemNode.NAME, Filepath.getFileName().toString());
		node.Attributes.put(SystemNode.CREATIONTIME,attr.creationTime().toString());
		node.Attributes.put(SystemNode.LASTACCESSTIME,attr.lastAccessTime().toString());
		node.Attributes.put(SystemNode.LASTMODIFIEDTIME,attr.lastModifiedTime().toString());
		node.Attributes.put(SystemNode.ISDIRECTORY,Boolean.toString(attr.isDirectory()));
		node.Attributes.put(SystemNode.ISREGULARFILE,Boolean.toString(attr.isRegularFile()));
		node.Attributes.put(SystemNode.SIZEINBYTES,Long.toString(attr.size()));
		
		if(attr.size()>1024){//1KB=1024Bytes
			node.Attributes.put(SystemNode.ISFILEABOVE1KB,"true");			
		}
		else{
			node.Attributes.put(SystemNode.ISFILEABOVE1KB,"false");			
		}
		
		if("true"==node.Attributes.get(SystemNode.ISDIRECTORY)){
			File path=new File(FP);
			File[] files = path.listFiles();
			for (File file : files){
				children=new LinkedList<Tree>();//Creating child Tree only if child(s) is present
				Tree child=new Tree(file.toString());
				this.children.add(child);
			}
		}
	}
}