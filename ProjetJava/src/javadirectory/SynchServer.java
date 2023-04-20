package javadirectory;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.lang.String;
import java.io.IOException;

public class SynchServer {
private static String srcPath = "C:\\Users\\ccomb\\javaProject\\src";
public void traitementServer(String name) {
    int port = 50000;
    ServerSocket socketserver = null;
    Socket socket = null;
    try {
        socketserver = new ServerSocket(port); // defines port
        System.out.println("SRV - Le serveur est à l'écoute du port " + socketserver.getLocalPort());
        socket = socketserver.accept();
        System.out.println("Prêt à envoyé");
        Accepter_clients t = new Accepter_clients(socket);
        t.read(name);
        t.start();
        socketserver.close();
    }
    catch (Exception e) { e.printStackTrace();  }
}
public void senderfile(String name) {
	try {
	    FileServeur serveur = new FileServeur();
        serveur.read(srcPath);
	    serveur.fileEmetor(name);
	}
	catch(IOException e){e.printStackTrace();}
}
public static void main(String[] args) {
    ListFile mediator = new ListFile();
    ArrayList<String> fileList= mediator.listNom(srcPath);
    fileList.add("fin");
    for (String s : fileList) {
        SynchServer source = new SynchServer();
        source.traitementServer(s);
    }
    //Emission de fichier 1 par 1
    
    for(String s :fileList) {
    	SynchServer source = new SynchServer();
    	source.senderfile(s);
    }
}

private static class Accepter_clients extends Thread {
    private Socket socket;
    private String name;
    public Accepter_clients(Socket socket) {
        this.socket = socket;
    }
    public String read(String s) {
        return this.name = s;
    }
    public void run() {
        try {
            // Buffered character output stream
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter ow = new OutputStreamWriter(os);
            BufferedWriter wr = new BufferedWriter(ow);
            wr.write(this.name + "\n");
            wr.flush();
        } 
        catch (Exception e) { e.printStackTrace();}
    }
}
}
