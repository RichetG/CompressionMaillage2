package Controler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Modele.Compression;
import Modele.Vertex3D;
import Vue.Interface;

public class Action implements ActionListener{	

	private JDialog dialog;
	private JLabel label1, label2, label3, label4, label5, label6, label7, label8;
	private JButton selectionner, valider, annuler;
	private JTextField nbX, nbY, nbZ;
	private JCheckBox grille;
	private JPanel panel1, panel2, panel3, panel4, panel5, panel6, panel7, panel8;
	private JFileChooser fileChooser;
	private Compression compression;
	private ArrayList<Vertex3D>list=new ArrayList<Vertex3D>();

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource()==Interface.compr){
			dialog=new JDialog();
			dialog.setSize(840, 220);
			dialog.setLayout(new BorderLayout());
			dialog.setTitle("compression fichier");
			//parti selection du fichier
			label1=new JLabel("Veuillez sélectionner le fichier à compresser (.obj): ");
			label2=new JLabel();
			selectionner=new JButton("Ouvrir ...");
			selectionner.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					fileChooser=new JFileChooser();
					int retour=fileChooser.showOpenDialog(null);
					if(retour==JFileChooser.APPROVE_OPTION){
						String extension=fileChooser.getSelectedFile().getAbsolutePath();
						if(extension.contains(".obj")){
							label2.setForeground(Color.BLUE);
							label2.setText("fichier: "+extension);
							valider.setEnabled(true);
							nbX.setEnabled(true);
							nbY.setEnabled(true);
							nbZ.setEnabled(true);
							grille.setEnabled(true);
						}else{
							label2.setForeground(Color.RED);
							label2.setText("Erreur: fichier de mauvaise extension");
							valider.setEnabled(false);
							nbX.setEnabled(false);
							nbY.setEnabled(false);
							nbZ.setEnabled(false);
							grille.setEnabled(false);
						}
					}
				}
			});
			panel1=new JPanel(new BorderLayout());
			panel2=new JPanel(new FlowLayout());
			panel3=new JPanel(new FlowLayout());
			panel2.add(label1);
			panel2.add(selectionner);
			panel3.add(label2);
			panel1.add(panel2, "North");
			panel1.add(panel3, "South");
			dialog.add(panel1, "North");
			//parti predifinition de la compression
			label3=new JLabel("Veuillez saisir la grille de compression (nombre entier):");
			label4=new JLabel("En X: ");
			nbX=new JTextField(5);
			nbX.setEnabled(false);
			label5=new JLabel("En Y: ");
			nbY=new JTextField(5);
			nbY.setEnabled(false);
			label6=new JLabel("En Z: ");
			nbZ=new JTextField(5);
			nbZ.setEnabled(false);
			label7=new JLabel("Affichage grille: ");
			grille=new JCheckBox();
			grille.setEnabled(false);
			label8=new JLabel();
			panel4=new JPanel(new FlowLayout());
			panel5=new JPanel(new FlowLayout());
			panel8=new JPanel(new FlowLayout());
			panel6=new JPanel(new BorderLayout());
			panel5.add(label3);
			panel4.add(label4);
			panel4.add(nbX);
			panel4.add(label5);
			panel4.add(nbY);
			panel4.add(label6);
			panel4.add(nbZ);
			panel4.add(label7);
			panel4.add(grille);
			panel8.add(label8);
			panel6.add(panel5, "North");
			panel6.add(panel4, "Center");
			panel6.add(panel8, "South");
			dialog.add(panel6, "Center");
			//bouton validation
			valider=new JButton("Valider");
			valider.setEnabled(false);
			valider.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(nbX.getText().matches("[0-9]+") && nbY.getText().matches("[0-9]+") && nbZ.getText().matches("[0-9]+")){
						String path=label2.getText().replace("fichier: ", "");
						File f = new File(path);
						compression=new Compression(f);
						//bidouille pour recuperer les vertexorigine car duplication des arraylist impossible 
						//fin bidouille
						Interface.objOrigine.setText(compression.printData());
						compression.compressionMaillage(Integer.valueOf(nbX.getText()), Integer.valueOf(nbY.getText()), Integer.valueOf(nbZ.getText()), grille.isSelected());
						Interface.objCompres.setText(compression.printData());
						String tmpName=path.replace(".", "Compressed.");
						try {
							compression.extractInverse(new File(tmpName));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Interface.taux.setText(compression.tauxErreur()+"%");
						label8.setText("");
						dialog.setVisible(false);
					}else{
						nbX.setText("");
						nbY.setText("");
						nbZ.setText("");
						label8.setForeground(Color.RED);
						label8.setText("Erreur: les valeurs de la grille invalides");
					}
				}
			});
			annuler=new JButton("Annuler");
			annuler.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					dialog.setVisible(false);
				}
			});
			panel7=new JPanel(new FlowLayout());
			panel7.add(valider);
			panel7.add(annuler);
			dialog.add(panel7, "South");
			dialog.setVisible(true);
		}
	}


}
