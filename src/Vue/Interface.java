package Vue;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import Controler.Action;
import Controler.Close;

@SuppressWarnings("serial")
public class Interface extends JFrame
{
	private JMenuBar menuBar;
	public static JMenuItem compr;
	public static JTextArea objOrigine, objCompres;
	private JScrollPane scrool1, scrool2;
	private JLabel origin, compres, result;
	public static JLabel taux;
	private JPanel panel1, panel2, panel3;
	private Action action=new Action();
	private Close close=new Close();
	
	public Interface(){
		setTitle("Compression Maillage");
		setSize(840, 480);
		
		menuBar=new JMenuBar();
		compr=new JMenuItem("Compression");
		compr.addActionListener(action);
		menuBar.add(compr);
		setJMenuBar(menuBar);
		
		addWindowListener(close);
		
		panel2=new JPanel(new GridLayout(1, 2));
		origin=new JLabel("Afficheur fichier origine:");
		origin.setHorizontalAlignment(SwingConstants.CENTER);
		compres=new JLabel("Afficheur fichier compressé:");
		compres.setHorizontalAlignment(SwingConstants.CENTER);
		panel2.add(origin);
		panel2.add(compres);
		add(panel2, "North");
		
		panel1=new JPanel(new GridLayout(1, 2));		
		objOrigine=new JTextArea();
		objOrigine.setEditable(false);
		scrool1=new JScrollPane(objOrigine);
		scrool1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrool1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		objCompres=new JTextArea();
		objCompres.setEditable(false);
		scrool2=new JScrollPane(objCompres);
		scrool2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrool2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel1.add(scrool1);
		panel1.add(scrool2);
		add(panel1, "Center");
		
		panel3=new JPanel(new FlowLayout());
		result=new JLabel("Résultat du taux d'erreur: ");
		taux=new JLabel("ici");
		panel3.add(result);
		panel3.add(taux);
		add(panel3, "South");
		
		setVisible(true);
	}
	
	public static void main(String[] args)
	{	
		@SuppressWarnings("unused")
		Interface interface1=new Interface();
	}
}
