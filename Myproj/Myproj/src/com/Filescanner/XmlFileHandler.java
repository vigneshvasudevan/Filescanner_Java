package com.Filescanner;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.Filescanner.Node.Systemprops;

public class XmlFileHandler implements Filewriter{
	
	public Tree tree;
	public Element root;
	
	private DocumentBuilderFactory docFactory;
	private DocumentBuilder docBuilder; 
	private Document doc;
	private Element Attrib;//Node
	
	public XmlFileHandler()
	{
		try {
			tree=new Tree(Filescanner.filepath);
			docFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docFactory.newDocumentBuilder();
			doc= docBuilder.newDocument();
			root = doc.createElement("Filescanner");// root element		
		} catch (IOException|ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void WriteToFile(){
		try{
			doc.appendChild(root);
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");//To make xml look pretty this prop is set
			
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(Filescanner.filepath+File.separator+"Filescanner.xml");
			transformer.transform(source, result);
			System.out.println("File created!");
					
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void createxmldoc(Tree tree,Element parent) throws ParserConfigurationException, TransformerException{
		
		if(tree.children!=null)//Checking for child Tree since only directory has a child
		{
			Element Directory=doc.createElement("Directory");//Directory Element 
			Iterator<Entry<Systemprops, String>> it = tree.node.Attributes.entrySet().iterator();
		    while (it.hasNext()) {
		        Entry<Systemprops, String> pair = it.next();
		        Attrib=doc.createElement(pair.getKey().toString());
		        Attrib.setTextContent(pair.getValue().toString());
		        Directory.appendChild(Attrib);
		        parent.appendChild(Directory);
		    }
			//Recursive call is made only when one child is found
			for(Tree child:tree.children)
			{
				createxmldoc(child,Directory);
			}
			
		}
		else
		{
			Element File=doc.createElement("File");
			Iterator<Entry<Systemprops, String>> it = tree.node.Attributes.entrySet().iterator();
		    while (it.hasNext()) {		    	
		    	Entry<Systemprops, String> pair = it.next();
		        Attrib=doc.createElement(pair.getKey().toString());
		        Attrib.setTextContent(pair.getValue().toString());
		        File.appendChild(Attrib);
		        parent.appendChild(File);
		    }
		}
	}
	
	@Override
	public void Search(Tree tree,Systemprops searchkey,String value){
		
		if( (tree.node.Attributes.containsKey(searchkey)) && (tree.node.Attributes.get(searchkey).contains(value))){
			if(tree.children!=null){//To check whether it is directory or file 
				Element Directory=doc.createElement("Directory");//Directory Element
				Iterator<Entry<Systemprops, String>> it = tree.node.Attributes.entrySet().iterator();
			    while (it.hasNext()) {
			        Entry<Systemprops, String> pair = it.next();
			        Attrib=doc.createElement(pair.getKey().toString());
			        Attrib.setTextContent(pair.getValue().toString());
			        Directory.appendChild(Attrib);
			        root.appendChild(Directory);
			    }
			}
			else{
				Element File=doc.createElement("File");//File Element
				Iterator<Entry<Systemprops, String>> it = tree.node.Attributes.entrySet().iterator();
			    while (it.hasNext()) {
			        Entry<Systemprops, String> pair = it.next();
			        Attrib=doc.createElement(pair.getKey().toString());
			        Attrib.setTextContent(pair.getValue().toString());
			        File.appendChild(Attrib);
			        root.appendChild(File);
			    }
			}
		}
		if(tree.children!=null){
			//Recursive call is made only when one child is found
			for(Tree child:tree.children)
			{
				Search(child,searchkey,value);
			}
		}
	}
}