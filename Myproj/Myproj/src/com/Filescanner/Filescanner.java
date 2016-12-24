package com.Filescanner;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

public class Filescanner {
	
	public static String filepath;
	public static String searchkey;
	private static String filetype;
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		Homepage home=new Homepage();
		UserInputHandler usrinput=new UserInputHandler();
		
		home.clearscreen();
		usrinput.askfilepath();
		//Check if the File path is valid
		if(usrinput.IsFilepathValid()){
			//searchkey=usrinput.Search();
			filetype=usrinput.Filetype();
			
			if(filetype=="XML"){
				try {
					new XmlFileHandler().WriteToFile();
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(filetype=="JSON"){
				new  JSONFileHandler().WriteToFile();
				
			}	
			else if(filetype=="CSV"){
				new CSVFileHandler().WriteToFile();
			}
			
			else{
				new Consolewriter().WriteToFile();
			}
			System.out.println("Completed processing!");
		}

	}

}
