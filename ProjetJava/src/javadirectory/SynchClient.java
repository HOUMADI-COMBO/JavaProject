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

public class SynchClient {

    public String traitementClient(){
        String sortie = new String();
        Socket socket;

        /*
        BufferedReader in;
        PrintWriter out;
        */
        int repetition;
        try{

            socket = new Socket("localhost",50000);

            // Buffered character input stream
            InputStream is = socket.getInputStream();
            InputStreamReader ir = new InputStreamReader(is); // transformation stream octet
            // en stream de caract
            BufferedReader rd = new BufferedReader(ir);

            String messageSrv = rd.readLine();
            System.out.println(messageSrv);
            sortie =messageSrv;
            socket.close();

        } catch(Exception e){
            e.printStackTrace();
        }
        return sortie;
    }
    public static void main(String[] args) {
        boolean v =true;
        ArrayList<String> receptacle = new ArrayList<String>();
        while(v){
            SynchClient target = new SynchClient();
            String ajout = target.traitementClient();
            if(ajout.equals("fin")) v=false;
            else receptacle.add(ajout);
        }

    }

}