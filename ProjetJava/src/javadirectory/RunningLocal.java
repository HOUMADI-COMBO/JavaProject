package javadirectory;
import java.util.ArrayList;

public class RunningLocal extends Thread{
    private String target_p;
    private String src_p;
    public RunningLocal(String pathS,String pathD) {
        this.src_p = pathS;
        this.target_p = pathD;
    }
@Override
public void run() {
    //thread for local synchronisation
    ListFile realisator = new ListFile();
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
