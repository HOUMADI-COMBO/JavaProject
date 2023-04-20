package javadirectory;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.lang.String;

public class FileClient {
private Forwarder forwarder = new Forwarder();
private String targetPath = new String();
public String read(String path) {
	return this.targetPath=path;
}
public void filereceptor(String fileName) throws IOException {
    //Le client réceptionne les fichiers
    Socket sockfile = new Socket(InetAddress.getLocalHost(),50000);
    forwarder.transfert(
        sockfile.getInputStream(),
        new FileOutputStream(this.targetPath+"//"+fileName),
        true);
    sockfile.close();
}
// public static void main(String[] args){
//     FileClient client = new FileClient();
//     try{
//         client.filereceptor("file1.txt");
//     }
//     catch(IOException e){e.printStackTrace();}
// }
}
