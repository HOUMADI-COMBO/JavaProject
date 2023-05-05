package javadirectory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.OutputStream;

public class ListFile {
private OperationAdditionnel operator = new OperationAdditionnel();
//private Comparaison comparator = new Comparaison();
public boolean mostrecent(String p_source,String p_dest){
	if( operator.comparaisonTemps(p_source,p_dest) ) return true;//désigne le dossier source
	else{ return false; }//designe le dossier cible
}
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
	//files presents in list are copied from src to dest .
	for(int i=0 ; i<list.size() ;i++ ) {
		String fileName = list.get(i);
		File src_to_copy = new File(src+"\\"+fileName);
		File copy_to_dest = new File(dest+"\\"+fileName);
		operator.copyFile(src_to_copy, copy_to_dest);
	}
}
public ArrayList<String> listSuppFile(String p_source,String p_dest) {
	//files present in target but not in src are erased.
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
	//delete files présents in list
	for(int i=0;i<list.size();i++) {
		String element = list.get(i);
		File A_supprimer = new File(target_path+"\\"+element);
		if(A_supprimer.delete()) System.out.println("Suppression of : "+element+" succed. ");
	}
}
public ArrayList<String> listMaj(String p_source,String p_dest){
	//return an list of file deeding to be up to date.
	ArrayList<String> nameT =listNom(p_dest);
	ArrayList<String> nameS =listNom(p_source);
	ArrayList<String> maj = new ArrayList<String>();
	for(int i=0;i<nameS.size();i++) {
		for(int j=0;j<nameT.size();j++) {
			if( ( operator.comparaionNom(nameS.get(i),nameT.get(j)) )) {
				if ( (operator.comparaisonTemps((p_source+"\\"+nameS.get(i)), (p_dest+"\\"+nameT.get(j)))) && (operator.comparaisonTaille((p_source+"\\"+nameS.get(i)), (p_dest+"\\"+nameT.get(j)))) )
					maj.add(nameS.get(i));
				System.out.println("Up to date : "+nameS.get(i));
			}
		}
	}
	return maj;
}
public void maj(String src, String dest, ArrayList<String>  list) throws IOException {
	// réalise la mise à jour avec copyfile qui écrase les fichiers existants
	copyfile(src,dest,list);
}
private class OperationAdditionnel{
	//Regroupement d'opération additionnels pour ne pas avoir à la répéter dans les classes.
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
		//copy of src from srcdirectory to dest directory
		Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
		System.out.println("copy_succes");
	}
	public boolean comparaisonTaille(String Name1, String Name2){
		File file1 = new File(Name1);
		File file2 = new File(Name2);
		if (file1.length() != file2.length())
			return true ;
		else
			return false ;
	}
	public boolean comparaisonTemps(String Name1, String Name2){
		File file1 = new File(Name1);
		File file2 = new File(Name2);
		if (file1.lastModified()> file2.lastModified())
			return true;
		else
			return false;
	}
	public boolean comparaionNom(String name1,String Name2) {
		if(name1.equals(Name2))
			return true;
		else
			return false ;
	}
}
}