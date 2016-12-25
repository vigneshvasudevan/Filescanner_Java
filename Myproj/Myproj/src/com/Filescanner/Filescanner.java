package com.Filescanner;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import com.Filescanner.Node.Systemprops;

public class Filescanner {
	
	public static String filepath;
	private static String filetype;
	
	public static void main(String[] args) throws InterruptedException, IOException, ParserConfigurationException, TransformerException {
		
		Homepage home=new Homepage();
		UserInputHandler usrinput=new UserInputHandler();
		home.clearscreen();
		usrinput.askfilepath();

		//Check if the File path is valid
		if(usrinput.IsFilepathValid()){
			Systemprops Searchkey=usrinput.getSearchkey();
			
			if(Searchkey==null){
				filetype=usrinput.getFiletype();
				
				if(filetype=="XML"){
					XmlFileHandler handler=new XmlFileHandler();
					handler.createxmldoc(handler.tree,handler.root);
					handler.WriteToFile();
				}
				else if(filetype=="JSON"){
					JSONFileHandler handler=new  JSONFileHandler();
					handler.WriteToFile();
				}	
				else if(filetype=="CSV"){
					CSVFileHandler handler=new CSVFileHandler();
					handler.WriteToFile();
				}
				else{
					Consolewriter handler=new Consolewriter();
					handler.WriteToFile();
				}
			}
			else{
				String value=usrinput.getSearchValue();
				filetype=usrinput.getFiletype();
				
				if(filetype=="XML"){
					XmlFileHandler handler=new XmlFileHandler();
					handler.Search(handler.tree,Searchkey,value);
					handler.WriteToFile();
				}
				else if(filetype=="JSON"){
					JSONFileHandler handler=new  JSONFileHandler();
					handler.Search(handler.tree,Searchkey,value);
				}	
				else if(filetype=="CSV"){
					CSVFileHandler handler=new CSVFileHandler();
					handler.Search(handler.tree,Searchkey,value);
				}
				else{
					Consolewriter handler=new Consolewriter();
					handler.Search(handler.tree,Searchkey,value);
				}
			}
			System.out.println("Completed processing!");
		}

	}

}
