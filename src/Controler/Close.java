package Controler;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Close extends WindowAdapter{

	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}
