package sunchronise_directory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ContentInformation {

public ArrayList<String> listContent(String path){
	//return an list of files (files names) present in considered directory 
	ArrayList<String> contentName = new ArrayList<String>();
	File doss = new File(path); 
	File[] liste = doss.listFiles();	
	for(File item : liste)
        if( item.isFile()) contentName.add(item.getName());
    return contentName ;
               
	

}
}
