package javadirectory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class ListFile {
private OperationAdditionnel operator = new OperationAdditionnel();
private Comparaison comparator = new Comparaison();

public ArrayList<String> listDoss(String path){
	//return a list of directory (directory names) present in considered directory
	ArrayList<String> contentName = new ArrayList<String>();
	File doss = new File(path);
	File[] list = doss.listFiles();
	for(File item : list)
		if( item.isDirectory()) contentName.add(item.getName());
	return contentName ;

}
public ArrayList<String> listNom(String path){
	//return a list of files (files names) present in considered directory
	ArrayList<String> contentName = new ArrayList<String>();
	File doss = new File(path); 
	File[] liste = doss.listFiles();	
	for(File item : liste)
        if( item.isFile()) contentName.add(item.getName());
    return contentName ;
}
public ArrayList<String> ListCopyFile(String p_source,String p_dest) throws IOException{ 
	//Copy dans la destination Les fichier n'existant que dans la source 
	ArrayList<String> contentName = operator.diff_0( listNom(p_source), listNom(p_dest));
	ArrayList<String> to_copy = operator.diff_1( listNom(p_source), contentName);
	return to_copy;
}
public void copyfile(String src, String dest,ArrayList<String>  list) throws IOException {
	for(int i=0 ; i<list.size() ;i++ ) {
		String fileName = list.get(i);
	    File src_to_copy = new File(src+"\\"+fileName);
	    File copy_to_dest = new File(dest+"\\"+fileName);
	    operator.copyFile(src_to_copy, copy_to_dest);
	}		
}
public ArrayList<String> listSuppFile(String p_source,String p_dest) {
	ArrayList<String> target =listNom(p_dest);
	ArrayList<String> contentName = operator.diff_0( listNom(p_source), target);
	ArrayList<String> toSupp = new ArrayList<String>();
	for(int i =0;i<target.size();i++) {
		Boolean v = true ;
		for(int j =0;j<contentName.size();j++) 
			if((target.get(i)).equals(contentName.get(j))) v = false;
		if(v) toSupp.add(target.get(i));
	}
	return toSupp;
}
public void Suppresion(String target_path,ArrayList<String>  list)throws IOException{
	//supprime les fichier dont le nom est présent dans list 
	for(int i=0;i<list.size();i++) {
		String element = list.get(i);
		File A_supprimer = new File(target_path+"\\"+element);
		if(A_supprimer.delete()) System.out.println("suppression_succes");
	}
}
public ArrayList<String> listMaj(String p_source,String p_dest){
	ArrayList<String> nameT =listNom(p_dest);
	ArrayList<String> nameS =listNom(p_source);
	ArrayList<String> maj = new ArrayList<String>();
	for(int i=0;i<nameS.size();i++) {
		for(int j=0;j<nameT.size();j++) {
			if( ( comparator.comparaionNom(nameS.get(i),nameT.get(j)) )) {
				if ((comparator.comparaisonTemps((p_source+"\\"+nameS.get(i)), (p_dest+"\\"+nameT.get(j)))))
					maj.add(nameS.get(i));
				    System.out.println("maj"+nameS.get(i));
			}
		}
	}
	return maj;
}
public void maj(String src, String dest, ArrayList<String>  list) throws IOException {
	copyfile(src,dest,list);
}
private class OperationAdditionnel{
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
		    for(int j=0 ; j<comparaison_result.size() ; j++ ) 
			    if( (src.get(i)).equals(comparaison_result.get(j)) )	V = true ;
		    if( V == false ) cR.add(src.get(i));		
		}
		return cR;
	}
	public void copyFile(File src, File dest) throws IOException {
	    Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
	    System.out.println("copy_succes");
	}
}
}
