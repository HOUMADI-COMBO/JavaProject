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
private static String srcPath ;// Le serveur possede le dossier source.
private static int port = 50000;
private int security =0;
    public SynchServer(String srcPath){
        this.srcPath=srcPath;
        if(this.port>=52000)this.port=50000;
    }
public void traitementServer(String name) {
    //Emeteur de la liste des fichiers posséder par la source
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
    port++;
}
public  void principal() {
    //Méthode main du serveur.
        ListFile mediator = new ListFile();
        ArrayList<String> fileList = mediator.listNom(srcPath);
        fileList.add("fin");
        for (String s : fileList) {
            SynchServer source = new SynchServer(srcPath);
            source.traitementServer(s);
        }
        //Emission de fichier 1 par 1
        fileList.remove("fin");
        for (String s : fileList) {
            System.out.println("Sending" + s);
            RunningReseaux sender = new RunningReseaux(0, s, srcPath);
            try {
                sender.start();
                sender.sleep(1000);
                Thread.currentThread().interrupted();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

private static class Accepter_clients extends Thread {
    //Gère les sockets de la transmission de la liste d nom.
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


