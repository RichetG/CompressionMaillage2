package Vue;

import java.awt.BorderLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Interface extends JFrame{
	
	public Interface(){
		setTitle("Compression de maillage");
		setLayout(new BorderLayout());
		setSize(840, 480);
		setVisible(true);
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args){
		Interface interface1=new Interface();
	}
}
