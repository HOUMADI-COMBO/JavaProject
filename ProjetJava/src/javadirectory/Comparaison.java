package javadirectory;
import java.io.File;


public class Comparaison {
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