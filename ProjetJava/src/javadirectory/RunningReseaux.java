package javadirectory;
import java.io.IOException;
import java.lang.Thread;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.String;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.io.InputStream;
import java.io.OutputStream;


public class RunningReseaux extends Thread {
private int client_server;// 1 désigne le client et 0 le serveur;
protected String name=new String();
	protected static String path=new String();
	protected static int port = 50000;
	protected FileReseau filer=new	FileReseau();
public RunningReseaux(int position,String name,String path) {
	this.client_server=position;
	this.path=path;
	this.name=name;
}
@Override
public void run() {
	//thread for distant synchronisation
	switch(this.client_server) {
	    case(0)://case serveur
	    	try {
		       // FileServeur serveur = new FileServeur();
	           // serveur.read(this.path);
		        /*serveur.*/filer.fileEmetor(this.name);
		    }
		    catch(IOException e){e.printStackTrace();}
		    break;
	    case(1)://case client
	    	try{
	    		//FileClient client = new FileClient();
	    	    //client.read(this.path);
	    		/*client.*/filer.filereceptor(this.name);
	        }
	        catch(IOException e){e.printStackTrace();}
	    	break;
    	default://error
	    	System.out.println("Error");
		    break;
	}
}
private class FileReseau{
	//private static int port = 50000;
	private Forwarder forwarder = new Forwarder();
	public void filereceptor(String fileName) throws IOException {
		//Le client réceptionne les fichiers
		Socket sockfile = new Socket(InetAddress.getLocalHost(),port);
		forwarder.transfert(
				sockfile.getInputStream(),
				new FileOutputStream(RunningReseaux.path+"//"+fileName),
				true);
		sockfile.close();
		RunningReseaux.port++;
	}
	public void fileEmetor(String fileName) throws IOException {
		//Le serveur émet les fichers
		Socket sockfile = new ServerSocket(port).accept();
		forwarder.transfert(
				new FileInputStream(RunningReseaux.path+"//"+fileName),
				sockfile.getOutputStream(),
				true);
		sockfile.close();
		RunningReseaux.port++;
	}
}
	public class Forwarder {
		public void transfert(InputStream in, OutputStream out, boolean closeOnExit) throws IOException {
			byte buf[] = new byte[1024];
			int n;
			while((n=in.read(buf))!=-1)
				out.write(buf,0,n);
			if (closeOnExit) {
				in.close();
				out.close();
			}
		}
	}
	//Dans Les classes on chage de port à chaque incrémentation de boucle car le fait d'envoyée sur un unique port était problématique.
}
