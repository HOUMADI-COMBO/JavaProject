package javadirectory;

import java.util.ArrayList;
import java.util.Scanner;

public class Javadirectory{
private static int security = 0 ;
private static int reseaux=0;
//0 pour synchronisation local 1 pour synchrosisation réseaux
private String pathS;//0
private String pathD;//1
Javadirectory(String pathS,String pathD){
    this.pathS=pathS;
    this.pathD=pathD;
}
public String readPath(int directory){
	String S = this.pathS;
	String D = this.pathD;
	switch(directory) {
	case(0):
	    return S;
	case(1):
	    return D;
	default:
		System.out.println("ECHEC");
		return null;
	}
}
public static void main(String[] args) {
	Scanner saisieUtilisateur = new Scanner(System.in);
	reseaux = saisieUtilisateur.nextInt();
	switch(reseaux){
		case 0 :
			while (security == 0) {
				Running synch = new Running();
				synch.start();
				try {
					//int une_semaine = 1000*60*60*24*7;
					int dix_secondes =1000*10;
					Thread.sleep(dix_secondes);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Veuillez saisir un entier :");
				security = saisieUtilisateur.nextInt();
			}
			System.out.println("Fin de la synchronisation de vos dossiers ");
			break;
		case 1 :
			break;
		default:
			System.out.println("Error!!!!!");
			break;
	}
}
}
