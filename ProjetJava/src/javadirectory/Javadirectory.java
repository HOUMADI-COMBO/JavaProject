package javadirectory;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class Javadirectory  implements ActionListener {
private int security = 0 ;
private int reseaux;//0 pour synchronisation local 1 pour synchrosisation réseaux
private String pathS="C:\\Users\\ccomb\\javaProject\\src";//0
private String pathD="C:\\Users\\ccomb\\javaProject\\target";//1
private String ip="localhost";
private ListFile operator = new ListFile();
private JFrame  frame ;
private JPanel panel;
private JTextField txtCheminDossier_1;
private JTextField txtCheminDossier_2;
private JTextField txtIpDestination;
private JTextField txtCheminDossier;
private JButton btnNewButton;
private JButton btnNewButton_1;
private JButton btnNewButton_2;
private JButton btnNewButton_3;
private JButton btnNewButton_4;
private JButton btnNewButton_5;
private JButton btnNewButton_6;
private JButton btnNewButton_7;
private JButton btnNewButton_8;
private int client_serveur;

Javadirectory(){
	JFrame  frame = new JFrame();
	frame.setBounds(100, 100, 1000, 600);
	frame.setResizable(false);
	frame.setLocation(200, 200);
	frame.setTitle("Javadirectory");
	panel = new JPanel();
	FlowLayout flowLayout = (FlowLayout) panel.getLayout();
	flowLayout.setVgap(10);
	flowLayout.setHgap(40);
	frame.getContentPane().add(panel, BorderLayout.CENTER);

	btnNewButton_2 = new JButton("Réseaux \r\n");
	btnNewButton_2.setPreferredSize(new Dimension(300, 60));
	btnNewButton_2.setBackground(new Color(209, 173, 224));
	btnNewButton_2.addActionListener(this);
	panel.add(btnNewButton_2);

	btnNewButton_3 = new JButton("Local");
	btnNewButton_3.setPreferredSize(new Dimension(300, 60));
	btnNewButton_3.setBackground(new Color(209, 173, 224));
	btnNewButton_3.addActionListener(this);
	panel.add(btnNewButton_3);

	btnNewButton_1 = new JButton("Client\r\n");
	btnNewButton_1.setPreferredSize(new Dimension(300, 60));
	btnNewButton_1.setBackground(new Color(209, 173, 224));
	btnNewButton_1.addActionListener(this);
	panel.add(btnNewButton_1);

	btnNewButton = new JButton("Serveur");
	btnNewButton.setPreferredSize(new Dimension(300, 60));
	btnNewButton.setBackground(new Color(209, 173, 224));
	btnNewButton.addActionListener(this);
	panel.add(btnNewButton);

	btnNewButton_4 = new JButton("Security");
	btnNewButton_4.setPreferredSize(new Dimension(300, 60));
	btnNewButton_4.setBackground(new Color(209, 173, 224));
	btnNewButton_4.addActionListener(this);
	panel.add(btnNewButton_4);

	txtCheminDossier = new JTextField();
	txtCheminDossier.setPreferredSize(new Dimension(800, 50));
	txtCheminDossier.setSize(new Dimension(800, 60));
	txtCheminDossier.setText("Chemin dossier 1");
	panel.add(txtCheminDossier);
	txtCheminDossier.setColumns(80);

	btnNewButton_5 = new JButton("Valider");
	btnNewButton_5.setPreferredSize(new Dimension(85, 50));
	btnNewButton_5.setBackground(new Color(209, 173, 224));
	btnNewButton_5.addActionListener(this);
	panel.add(btnNewButton_5);

	txtCheminDossier_1 = new JTextField();
	txtCheminDossier_1.setPreferredSize(new Dimension(800, 50));
	txtCheminDossier_1.setMinimumSize(new Dimension(800, 50));
	txtCheminDossier_1.setText("Chemin dossier 2");
	panel.add(txtCheminDossier_1);
	txtCheminDossier_1.setColumns(80);

	btnNewButton_6 = new JButton("Valider\r\n");
	btnNewButton_6.setPreferredSize(new Dimension(85, 50));
	btnNewButton_6.setBackground(new Color(209, 173, 224));
	btnNewButton_6.addActionListener(this);
	panel.add(btnNewButton_6);

	txtIpDestination = new JTextField();
	txtIpDestination.setPreferredSize(new Dimension(300, 40));
	txtIpDestination.setText("Ip destination ( IPv4 )");
	panel.add(txtIpDestination);
	txtIpDestination.setColumns(50);

	btnNewButton_7 = new JButton("Valider");
	btnNewButton_7.setPreferredSize(new Dimension(85, 50));
	btnNewButton_7.setBackground(new Color(209, 173, 224));
	btnNewButton_7.addActionListener(this);
	panel.add(btnNewButton_7);

	txtCheminDossier_2 = new JTextField();
	txtCheminDossier_2.setPreferredSize(new Dimension(800, 50));
	txtCheminDossier_2.setMinimumSize(new Dimension(800, 50));
	txtCheminDossier_2.setEditable(false);
	panel.add(txtCheminDossier_2);
	txtCheminDossier_2.setColumns(95);

	btnNewButton_8 = new JButton("Start");
	btnNewButton_8.setPreferredSize(new Dimension(85, 50));
	btnNewButton_8.setBackground(new Color(209, 173, 224));
	btnNewButton_8.addActionListener(this);
	panel.add(btnNewButton_8);

	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}
	public void actionPerformed(ActionEvent e) {
		String name = txtCheminDossier.getText();
		String name1 = txtCheminDossier_1.getText();
		String name2 = txtIpDestination.getText();
		String result ="";
		if(e.getSource() == btnNewButton_5){
			this.pathS = name;
			txtCheminDossier_2.setText(this.pathS);
		}
		if(e.getSource() == btnNewButton_6){
			this.pathD = name1;
			txtCheminDossier_2.setText(this.pathD);
		}
		if(e.getSource() == btnNewButton_7){
			this.ip = name2;
			txtCheminDossier_2.setText(this.ip);
		}
		if(e.getSource() == btnNewButton){
			this.client_serveur=0;
			txtCheminDossier_2.setText("Serveur mode");
		}
		if(e.getSource() == btnNewButton_1){
			this.client_serveur=1;
			txtCheminDossier_2.setText("Client mode");
		}
		if(e.getSource() == btnNewButton_2){
			this.reseaux=1;
			txtCheminDossier_2.setText("Synchronisation Réseau ");
		}
		if(e.getSource() == btnNewButton_3){
			this.reseaux=0;
			txtCheminDossier_2.setText("Synchronisation Local ");
		}
		if(e.getSource() == btnNewButton_4){
			this.security=1;
			txtCheminDossier_2.setText("Annulation");
		}
		if(e.getSource() == btnNewButton_8){
			txtCheminDossier_2.setText("Synchronisation en cours ");
			Synchronisation();
		}
	}
public void Synchronisation(){
	switch (reseaux) {
		case 0:
			System.out.println("Synchronisation local lancée");
			while (security == 0) {
				RunningLocal synch = new RunningLocal(pathS, pathD);
				synch.start();
				try {
					//int une_semaine = 1000*60*60*24*7;
					int dix_secondes = 1000 * 10;
					Thread.sleep(dix_secondes);
					if (operator.mostrecent(pathS, pathD)) {
						String path = pathS;
						this.pathS = pathD;
						this.pathD = path;
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//System.out.println("Voulez vous continuer la synchronisation de vos dossiers? (taper 0 pour oui 1 pour non) :");
				//security = saisieUtilisateur.nextInt();
			}
			break;
		case 1://Le réseau reste monodirectionelle et ouvert
			System.out.println("Synchronisation réseaux lancée");
			if (client_serveur == 0) {
				while (security == 0) {
					SynchServer source = new SynchServer(pathS);
					source.principal();
					try {
						//int une_semaine = 1000*60*60*24*7;
						int dix_secondes = 1000 * 10;
						Thread.sleep(dix_secondes);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//System.out.println("Voulez vous continuer la synchronisation de vos dossiers? (taper 0 pour oui 1 pour non) :");
					//security = saisieUtilisateur.nextInt();
				}
			}
			if (client_serveur == 1) {
				while (security == 0) {
					SynchClient target = new SynchClient(pathD,ip);
					target.principal();
					try {
						//int une_semaine = 1000*60*60*24*7;
						int dix_secondes = 1000 * 10;
						Thread.sleep(dix_secondes + 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//System.out.println("Voulez vous continuer la synchronisation de vos dossiers? (taper 0 pour oui 1 pour non) :");
					//security = saisieUtilisateur.nextInt();
				}
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
