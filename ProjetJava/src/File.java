package JavaProject;

import java.io.File;
import java.nio.file.StandardCopyOption;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class File {
private static String src_path ="C:\\Users\\ccomb\\javaProject\\src";
private static String target_path ="C:\\Users\\ccomb\\javaProject\\target";
private int  prioritarydate;
// Va nous permettre de determiner le repertoire modifié le plus récement pour le dupplique vers le plus encien

public void printContent(String path) throws IOException {
	//print content of each files in directory considered
		File doss = new File(path); 
		File[] liste = doss.listFiles();	
		for(File item : liste){
	        System.out.println( Files.readString(Path.of(item.getPath())));        
	    }
}
public ArrayList<String> diff_0(ArrayList<String> src,ArrayList<String> target){
	//returns list of document, present in both directory, source and target   
	ArrayList<String> comparaison_result = new ArrayList<String>();
	for( int i=0; i < src.size(); i++) {
		for( int j=0; j< target.size();j++) {
			if( (src.get(i)).equals(target.get(j)) ) {	comparaison_result.add( src.get(i) ) ; }
		}
	}
	return comparaison_result;
}
public ArrayList<String> diff_1(ArrayList<String> src,ArrayList<String> comparaison_result){
	//returns list of document, present in src but not in target
	ArrayList<String> cR = new ArrayList<String>();
	for(int i=0 ; i<src.size() ; i++ ) {
		Boolean V = false ;
		for(int j=0 ; j<comparaison_result.size() ; j++ ) {
			if( (src.get(i)).equals(comparaison_result.get(j)) )	V = true ;
		}
		if( V == false ) cR.add(src.get(i));		
	}
	return cR;
}
public void copyFile(File src, File dest) throws IOException {
    Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
}
public void completeTarget(ArrayList<String> cR,String p_source,String p_dest) throws IOException{ 
	//Copy dans la destination Les fichier n'existant que dans la source 
	for(int i=0 ; i<cR.size() ;i++ ) {
		String fileName = cR.get(i);
	    File src_to_copy = new File(p_source+"\\"+fileName);
	    File copy_to_dest = new File(p_dest+"\\"+fileName);
	    copyFile(src_to_copy, copy_to_dest);
	    System.out.println("succes");
	}		
}
public void printDateName(String path) throws IOException {
	//print name and date of each files in directory considered
	File doss = new File(path); 
	File[] liste = doss.listFiles();	
	for(File item : liste){
        if( item.isFile()) {
            System.out.format( "Nom du fichier: %s%n",item.getName());
            System.out.println( item.lastModified()); 
        }
    }
}
public ArrayList<String> sup_to_apply(ArrayList<String> comparaison_result, ArrayList<String> target) {
	//return a list of file to delete
	ArrayList<String> toSupp = new ArrayList<String>();
	for(int i =0;i<target.size();i++) {
		Boolean v = true ;
		for(int j =0;j<comparaison_result.size();j++) 
			if((target.get(i)).equals(comparaison_result.get(j))) v = false;
		if(v) toSupp.add(target.get(i));
	}
	return toSupp;
}
public void suppFile(String target_path,ArrayList<String>  toSupp)throws IOException{
	//delete files 
	for(int i=0;i<toSupp.size();i++) {
		String element = toSupp.get(i);
		File A_supprimer = new File(target_path+"\\"+element);
		if(A_supprimer.delete()) System.out.println("succes");
	}
}
public void maj(ArrayList<String> comparaisonResult,String dest ,String src)throws IOException {
	//met à jour les fichier 
		for(int i=0;i<comparaisonResult.size();i++) {
			String fileName = comparaisonResult.get(i);
		    File src_to_copy = new File(src+"\\"+fileName);
		    File copy_to_dest = new File(dest+"\\"+fileName);
		    copyFile(src_to_copy, copy_to_dest);
		    System.out.println("Maj_succes");
		}
}
public static void main(String args[]) {
    File temporal = new File();
    ContentInformation content = new ContentInformation();
	ArrayList<String> src = content.listContent(src_path);
	ArrayList<String> dest = content.listContent(target_path);
	ArrayList<String> comparaisonResult = temporal.diff_0(src,dest);
	ArrayList<String> cR = temporal.diff_1(src, comparaisonResult);
    try {temporal.completeTarget(cR, src_path, target_path);  }
    catch( Exception e) { e.printStackTrace();}  
    ArrayList<String> toDelete = temporal.sup_to_apply( comparaisonResult , dest);
    try {
    	temporal.suppFile(target_path, toDelete); 
    	temporal.maj(comparaisonResult,target_path,src_path);
    	}
    catch(Exception e){e.printStackTrace();}
	}
}
