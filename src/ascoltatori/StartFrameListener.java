package ascoltatori;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import centrourbano.CentroUrbano;
import gui.StartFrame;

public class StartFrameListener implements ActionListener{

	StartFrame rifer;
	File file = new File("Salvataggio.sav");
	
	public StartFrameListener(StartFrame e) {
		rifer = e;
	}
	
	public void actionPerformed(ActionEvent e) {
		JMenuItem t = (JMenuItem) e.getSource();
		if(t.getText().equalsIgnoreCase("Salva")) {
			salva();
		}
		else if(t.getText().equalsIgnoreCase("Carica")) {
			carica();
			
		}
		else if(t.getText().equalsIgnoreCase("Nuovo")) {
			nuovo(new CentroUrbano());
			
		}
		else if(t.getText().equalsIgnoreCase("Esci")) {
			rifer.setEnabled(false);
			JFrame temp= new JFrame("Chiusura");
			temp.add(new JLabel("Made by Alessandro Zolfanelli, Tiziana Correale, Stefano Nicodemo, Vito Carmine Lanaro e Giuseppe Madonna."));
			temp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			temp.setSize(300,110);
			temp.setResizable(false);
			temp.setVisible(true);
			
		}
	}
	
	private void salva() {
		FileOutputStream writer= null;
		try {
			writer = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
		}
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(writer);
			out.writeObject(rifer.uno);
		} catch (IOException e) {
		}
		
		
		}
	
	private void carica() {
		FileInputStream reader = null;
		ObjectInputStream in = null;
		try {
			reader = new FileInputStream(file);
			in = new ObjectInputStream(reader);
			nuovo((CentroUrbano)in.readObject());
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void nuovo(CentroUrbano c) {
		rifer.setVisible(false);
		StartFrame io = new StartFrame(c);
		io.setLocation(rifer.getX(), rifer.getY());
		JFrame temp= new JFrame("Avviso");
		temp.add(new JLabel("Simulazione riavviata."));
		temp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		temp.setSize(500,110);
		temp.setVisible(true);
		temp.setResizable(false);
		rifer.dispose();
		
	}


}


