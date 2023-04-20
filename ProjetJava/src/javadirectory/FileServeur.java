package javadirectory;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.String;

public class FileServeur {
private String srcPath = new String();
private Forwarder forwarder = new Forwarder();
public String read(String path) {
	return this.srcPath=path;
}
public void fileEmetor(String fileName) throws IOException {
    //Le client émet les fichers
    Socket sockfile = new ServerSocket(50000).accept();
    forwarder.transfert(
        new FileInputStream(this.srcPath+"//"+fileName),
        sockfile.getOutputStream(),
        true);
    sockfile.close();
}
//public static void main(String [] args){
//    FileServeur serveur = new FileServeur();
//    try{
//        serveur.fileEmetor("file1.txt");
//    }
//    catch(IOException e){e.printStackTrace();}
//}
}