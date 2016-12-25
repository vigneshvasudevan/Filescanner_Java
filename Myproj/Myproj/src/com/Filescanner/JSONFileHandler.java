package com.Filescanner;

import java.io.IOException;
import org.json.JSONObject;

import com.Filescanner.Node.Systemprops;

public class JSONFileHandler implements Filewriter {
	public Tree tree;
	private JSONObject jsonobj;
	
	public JSONFileHandler() throws IOException{
		tree=new Tree(Filescanner.filepath);
		jsonobj=new JSONObject();
	}
	@Override
	public void WriteToFile(){
		CreateJSONResponse();
	}
	
	private void CreateJSONResponse(){
		
	}
	@Override
	public void Search(Tree tree,Systemprops Searchkey,String value){
		
	}
}


