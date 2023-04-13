package javadirectory;

import java.io.File;
import java.util.ArrayList;

public class Javadirectory{
private String pathS;//0
private String pathD;//1
Javadirectory(String pathS,String pathD){
    this.pathS=pathS;
    this.pathD=pathD;
}
public String readPath(int directory){
	String S = this.pathS;
	String D = this.pathD;
	
	switch(directory) {
	case(0):
	    return S;
	case(1):
	    return D;
	default:
		System.out.println("ECHEC");
		return null;
	
	}
}
public static void main(String[] args) {
	Javadirectory synchroniser = new Javadirectory("C:\\Users\\ccomb\\javaProject\\src","C:\\Users\\ccomb\\javaProject\\target");
	ListFile realisator = new ListFile();
	String src_p = synchroniser.readPath(0);
	String target_p = synchroniser.readPath(1);
	try{        ArrayList<String> to_copy = realisator.ListCopyFile(src_p,target_p);
		        realisator.copyfile(src_p, target_p, to_copy);
	}
	catch(Exception e){e.printStackTrace();}

	try {       ArrayList<String> delete = realisator.listSuppFile(src_p, target_p);
		        realisator.Suppresion(target_p, delete);
	}
	catch(Exception e){e.printStackTrace();}
	try { 	    ArrayList<String> to_maj = realisator.listMaj(src_p, target_p);
                realisator.maj(src_p, target_p, to_maj);
    }
	catch(Exception e){e.printStackTrace();}
}
}

