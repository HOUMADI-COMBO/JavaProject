package javadirectory;
import java.util.Scanner;

public class Javadirectory  {
private int security = 0 ;
private int reseaux;//0 pour synchronisation local 1 pour synchrosisation réseaux
private String pathS="C:\\Users\\ccomb\\javaProject\\src";//0
private String pathD="C:\\Users\\ccomb\\javaProject\\target";//1
	private ListFile operator = new ListFile();

Javadirectory(){
		this.pathS=pathS;
		this.pathD=pathD;
	Scanner saisieUtilisateur = new Scanner(System.in);
	System.out.println("Voulez vous synchroniser des dossiers sur deux ordinateurs différents de votre réseau local ? (taper 1 pour oui 0 pour non) :");
	reseaux = saisieUtilisateur.nextInt();
	if(reseaux == 0)System.out.println("Vous avez choisis de synchroniser deux dossiers de votre disc local. ");
	if(reseaux == 1)System.out.println("Vous avez choisis de synchroniser deux dossiers sur des discs distants.  ");
	switch(reseaux){
		case 0 :
			while (security == 0) {
				RunningLocal synch = new RunningLocal(pathS,pathD);
				synch.start();
				try {
					//int une_semaine = 1000*60*60*24*7;
					int dix_secondes =1000*10;
					Thread.sleep(dix_secondes);
					if(operator.mostrecent(pathS,pathD)){
						String path = pathS;
						this.pathS=pathD;
						this.pathD=path;
					}

				} catch (InterruptedException e) {e.printStackTrace();}
					//System.out.println("Voulez vous continuer la synchronisation de vos dossiers? (taper 0 pour oui 1 pour non) :");
					//security = saisieUtilisateur.nextInt();
			}
			System.out.println("Fin de la synchronisation de vos dossiers ");
			break;
		case 1 ://Le réseau reste monodirectionelle et ouvert
			System.out.println(" Etes vous la source ou la destination ? (taper 0 pour vous êtes la source et 1 si vous êtes la destination) :  ");
			int client_serveur = saisieUtilisateur.nextInt();
			if(client_serveur == 0){
				SynchServer source = new SynchServer(pathS);
				source.principal();
			}
			if(client_serveur == 1){
				SynchClient target = new SynchClient(pathD);
				target.principal();
			}
			break;
		default:
			System.out.println("Error!!!!!");
			break;
	}
}
public static void main(String[] args){
	new Javadirectory();
}
}
