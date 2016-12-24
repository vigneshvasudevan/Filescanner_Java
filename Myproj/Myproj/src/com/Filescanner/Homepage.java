package com.Filescanner;

import java.io.IOException;

public class Homepage {

	public void clearscreen() throws InterruptedException, IOException{
	
		String os = System.getProperty("os.name");
	    if (os.contains("Windows"))
	        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();//This statement is to clear the output screen in windows
	    else
	        Runtime.getRuntime().exec("clear");//This command is to clear the output screen in Linux/MAC operating system
	    
	    System.out.println("************************************************************************");
	    System.out.println("*************************Welcome,Dear User******************************");
	    System.out.println("************************************************************************");
	
	}
}
	
