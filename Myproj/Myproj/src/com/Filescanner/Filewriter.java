package com.Filescanner;

import com.Filescanner.Node.Systemprops;

public interface Filewriter {
	void WriteToFile() ;
	void Search(Tree tree,Systemprops key,String value);
}
