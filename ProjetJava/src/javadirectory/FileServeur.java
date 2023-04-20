package javadirectory;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.String;

public class FileServeur {
private String srcPath = new String();
private Forwarder forwarder = new Forwarder();
private static int port = 50000;
public String read(String path) {
	return this.srcPath=path;
}
public void fileEmetor(String fileName) throws IOException {
    //Le serveur émet les fichers
    Socket sockfile = new ServerSocket(port).accept();
    forwarder.transfert(
        new FileInputStream(this.srcPath+"//"+fileName),
        sockfile.getOutputStream(),
        true);
    sockfile.close();
    port++;
}
}