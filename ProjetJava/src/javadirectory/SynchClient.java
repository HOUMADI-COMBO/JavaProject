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
private static String targetPath;// Le client possède le dossier cible.
private static int port = 50000;
private int security=0;
private String ip;
public SynchClient(String targetPath,String ip){
    this.targetPath=targetPath;
    this.ip=ip;
    if(this.port>=55000)this.port=50000;
}
public String traitementClient(){
    //Réceptionneur de la liste des fichier de la source.
    String sortie = new String();
    Socket socket;
    try{
        socket = new Socket(ip,port);
        InputStream is = socket.getInputStream();          // Buffered character input stream
        InputStreamReader ir = new InputStreamReader(is);  // transformation stream octet
        BufferedReader rd = new BufferedReader(ir);        // en stream de caract
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
    //Génere une liste des fichiers qui n'ont pas été envoyée par le serveur
	ListFile operator = new ListFile();
	ArrayList<String> targetList = operator.listNom(targetpath);
	for(int i=0;i<srcList.size();i++) {
		for(int j=0;j<targetList.size();j++) 
		    if( (srcList.get(i)).equals(targetList.get(j)) ) targetList.remove(srcList.get(i));
	}
	return targetList;
}
public void suppEccedent(String targetpath,ArrayList<String> suppList) {
    // Réalise la supprésion de la liste de fichier crée ci-dessus.
	ListFile operator = new ListFile();
	try{ operator.Suppresion(targetpath,suppList); }
	catch(IOException e){e.printStackTrace(); }
}
public void principal() {
    //Méthode main du client.
        boolean v = true;
        ArrayList<String> nameReceptacle = new ArrayList<String>();
        while (v) {
            SynchClient target = new SynchClient(targetPath,ip);
            String ajout = target.traitementClient();
            if (ajout.equals("fin")) v = false;
            else nameReceptacle.add(ajout);
        }
        nameReceptacle.remove("fin");
        //receptio de fichier 1 par 1
        for (String s : nameReceptacle) {
            System.out.println("Receiving  " + s);
            RunningReseaux receiver = new RunningReseaux(1, s, targetPath);
            try {
                receiver.start();
                receiver.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //Manque à crée une fct qui supprimes les éléments du dossier cible nom présent dans la source et la synchronisation de dossiers sera au point
        System.out.println("   Suppressin processing   ");
        SynchClient target = new SynchClient(targetPath,ip);
        ArrayList<String> receptacle = target.listSuppEccedent(nameReceptacle, targetPath);
        target.suppEccedent(targetPath, receptacle);
}
}