package com.Filescanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


public class UserInputHandler {
	
	
	public void askfilepath() throws IOException {
		BufferedReader input = new BufferedReader (new InputStreamReader(System.in)); // Reading from System.in
		System.out.println("Enter the file/folder path");
		Filescanner.filepath = input.readLine();	
	}
	
	public Boolean IsFilepathValid() throws IOException, InterruptedException{
		
		File file = new File(Filescanner.filepath); 
		
		if (file.exists()){
			System.out.println("Entered File path is valid");
			return true;
		}
		    
		else{
			System.out.println("Entered File path is invalid");
			System.out.println("Press 'R' to retry and any key to exit");
			BufferedReader input = new BufferedReader (new InputStreamReader(System.in)); // Reading from System.in
			//To get char input from user
			char choice = (char) input.read();	
			if((choice=='R' || choice=='r')){
				String[] args = {};
				Filescanner.main(args);//Calling main to repeat the operation again
			}
			return false;
		}
			
	}
	public String Search() throws InterruptedException, IOException{
		
		String os = System.getProperty("os.name");
	    if (os.contains("Windows"))
	        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();//This statement is to clear the output screen in windows
	    else
	        Runtime.getRuntime().exec("clear");//This command is to clear the output screen in Linux/MAC operating system
	    
	    System.out.println("*************************Search Menu******************************");
	    System.out.println("Below are the various system which could be used for searching ");
		System.out.println("Enter1, for listing files based on CreationTime");
		System.out.println("Enter2, for listing files based on lastAccessTime");
		System.out.println("Enter3, for listing files based on lastModifiedTime");
		System.out.println("Enter4, for listing Directory ");
		System.out.println("Enter5, for listing RegularFile(s) under the inputted path");
		System.out.println("Enter6, for listing files based on size");
		System.out.println("Enter7, for listing files greater than 1kb");
		System.out.println("Enter any other for listing all subfolder/files Node");
		
		BufferedReader input = new BufferedReader (new InputStreamReader(System.in)); // Reading from System.in
		//To get input from user
		char choice = (char) input.read();
		switch(choice){
		case '1':return "CreationTime";
		case '2':return "lastAccessTime";
		case '3':return "lastModifiedTime";
		case '4':return "IsDirectory";
		case '5':return "IsRegularFile";
		case '6':return "size";
		case '7':return "IsFileabove1KB";
		default :return "Listall";
		}
	}
	
	public String Filetype() throws IOException, InterruptedException{
		   
		System.out.println("Enter1, for storing output in XML");
		System.out.println("Enter2, for storing output in JSON");
		System.out.println("Enter3, for storing output in CSV");
		System.out.println("Press any other key for viewing output in console");
		BufferedReader input = new BufferedReader (new InputStreamReader(System.in)); // Reading from System.in
		//To get input from user
		char choice = (char) input.read();
		switch(choice){
		case '1':return "XML";
		case '2':return "JSON";
		case '3':return "CSV";
		default :return "Console";
		}
	}
}