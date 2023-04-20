package javadirectory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
//DANS LES classes on chage de port à chaque incrémentation de boucle car le fait d'envoyée sur un unique port posser des d'addresse deja utiliser 
