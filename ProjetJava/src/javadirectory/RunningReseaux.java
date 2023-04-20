package javadirectory;
import java.lang.String;
import java.io.IOException;
import java.lang.Thread;

public class RunningReseaux extends Thread {
private int client_server;// 1 désigne le client et 0 le serveur;
private String name=new String();
private String path=new String();
public RunningReseaux(int position,String name,String path) {
	this.client_server=position;
	this.path=path;
	this.name=name;
}

@Override
public void run() {
	switch(this.client_server) {
	    case(0):
	    	try {
		        FileServeur serveur = new FileServeur();
	            serveur.read(this.path);
		        serveur.fileEmetor(this.name);
		    }
		    catch(IOException e){e.printStackTrace();}
		    break;
	    case(1):
	    	try{
	    		FileClient client = new FileClient();
	    	    client.read(this.path);
	    		client.filereceptor(this.name);
	        }
	        catch(IOException e){e.printStackTrace();}
	    	break;
    	default:
	    	System.out.println("Error");
		    break;
	}
}

}
