package javadirectory;

import java.io.File;
import java.util.ArrayList;

public class Javadirectory{
private String pathS;
private String PathD;
Javadirectory(String pathS,String pathD){
    this.pathS=pathS;
    this.PathD=pathD;
}
public String readPath(char directory){
	if(directory == 's') return PathS;
	if(directory == 't') return PathD;
	return null;
}
public static void main(String[] args) {
	
}
}
