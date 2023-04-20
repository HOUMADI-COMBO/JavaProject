package javadirectory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class SynchClient {
	private static String targetPath = "C:\\Users\\ccomb\\javaProject\\target";
	private static int port = 50000;
    public String traitementClient(){
    String sortie = new String();
    Socket socket;
    try{
        socket = new Socket("localhost",port);
        // Buffered character input stream
        InputStream is = socket.getInputStream();
        InputStreamReader ir = new InputStreamReader(is); // transformation stream octet
        // en stream de caract
        BufferedReader rd = new BufferedReader(ir);
        String messageSrv = rd.readLine();
        System.out.println(messageSrv);
        sortie =messageSrv;
        socket.close();
    } 
    catch(Exception e){ e.printStackTrace(); }
    port++;
    return sortie;
}
public ArrayList<String> listSuppEccedent(ArrayList<String> srcList, String targetpath) {
	ListFile operator = new ListFile();
	ArrayList<String> targetList = operator.listNom(targetpath);
	for(int i=0;i<srcList.size();i++) {
		for(int j=0;j<targetList.size();j++) 
		    if( (srcList.get(i)).equals(targetList.get(j)) ) targetList.remove(srcList.get(i));
	}
	return targetList;
}
public void suppEccedent(String targetpath,ArrayList<String> suppList) {
	ListFile operator = new ListFile();
	try{ operator.Suppresion(targetpath,suppList); }
	catch(IOException e){e.printStackTrace(); }
	
}
	
public static void main(String[] args) {
    boolean v =true;
    ArrayList<String> nameReceptacle = new ArrayList<String>();
    while(v){
        SynchClient target = new SynchClient();
        String ajout = target.traitementClient();
        if(ajout.equals("fin")) v=false;
        else nameReceptacle.add(ajout);
    }
    nameReceptacle.remove("fin");
    //receptio de fichier 1 par 1
    for(String s : nameReceptacle) {
    	System.out.println("Receiving  "+s);
    	RunningReseaux receiver = new RunningReseaux(1,s,targetPath);
    	try{
    		receiver.start();
    		receiver.sleep(1300);
    	}
    	catch(Exception e){e.printStackTrace(); }
    }
    //Manque à crée une fct qui supprimes les éléments du dossier cible nom présent dans la source et la synchronisation de dossiers sera au point 
    System.out.println("   Suppressin processing   ");
    SynchClient target = new SynchClient();
    ArrayList<String> receptacle = target.listSuppEccedent(nameReceptacle, targetPath);
    target.suppEccedent(targetPath,receptacle);
}
}