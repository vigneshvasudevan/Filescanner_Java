package com.Filescanner;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.Filescanner.Node.SystemNode;

public class XmlFileHandler implements Filewriter{
	
	private Tree tree;
	private DocumentBuilderFactory docFactory;
	private DocumentBuilder docBuilder; 
	private Document doc;
	private Element root;
	
	//Node
	private Element Attrib;
	
	public XmlFileHandler() throws ParserConfigurationException, IOException
	{
		tree=new Tree(Filescanner.filepath);
		docFactory = DocumentBuilderFactory.newInstance();
		docBuilder = docFactory.newDocumentBuilder();
		doc= docBuilder.newDocument();
		root = doc.createElement("Filescanner");// root element		
	}
	
	public void WriteToFile(){
		try{
			System.out.println(root.toString());
			createxmldoc(tree,root);
			doc.appendChild(root);
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
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
	
	private void createxmldoc(Tree tree,Element parent) throws ParserConfigurationException, TransformerException{
		
		if(tree.children!=null)//Checking for child Tree since only directory has a child
		{
			Element Directory=doc.createElement("Directory");//Directory Element 
			Iterator<Entry<SystemNode, String>> it = tree.node.Attributes.entrySet().iterator();
		    while (it.hasNext()) {
		        Entry<SystemNode, String> pair = it.next();
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
			Iterator<Entry<SystemNode, String>> it = tree.node.Attributes.entrySet().iterator();
		    while (it.hasNext()) {		    	
		    	Entry<SystemNode, String> pair = it.next();
		        Attrib=doc.createElement(pair.getKey().toString());
		        Attrib.setTextContent(pair.getValue().toString());
		        File.appendChild(Attrib);
		        parent.appendChild(File);
		    }
		}
	}
}