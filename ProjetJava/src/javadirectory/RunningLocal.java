package javadirectory;

import java.util.ArrayList;

public class RunningLocal extends Thread{
@Override
public void run() {
    Javadirectory synchroniser = new Javadirectory("C:\\Users\\ccomb\\javaProject\\src","C:\\Users\\ccomb\\javaProject\\target");
    ListFile realisator = new ListFile();
    String src_p = synchroniser.readPath(0);
    String target_p = synchroniser.readPath(1);
    try{        ArrayList<String> to_copy = realisator.ListCopyFile(src_p,target_p);
        realisator.copyfile(src_p, target_p, to_copy);
    }
    catch(Exception e){e.printStackTrace();}
    try {       ArrayList<String> delete = realisator.listSuppFile(src_p, target_p);
        realisator.Suppresion(target_p, delete);
    }
    catch(Exception e){e.printStackTrace();}
    try { 	  ArrayList<String> to_maj = realisator.listMaj(src_p, target_p);
        realisator.maj(src_p, target_p, to_maj);
    }
    catch(Exception e){e.printStackTrace();}
}
}
