package dz2;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class Simulacija extends Frame {
	private Scena scena = new Scena(this);
	
	private void dodajKomponente() {
		setLayout(new BorderLayout());
		add(scena, BorderLayout.CENTER);
	}
	
	Simulacija(){
		dodajKomponente();
		pack();
		setResizable(true);
		dodajOsluskivace();
		setLocation(500, 250);
		setTitle("Simulacija");
		setVisible(true);
	}
	
	private void dodajOsluskivace() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				scena.interrupt();
				dispose();
			}
		});
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				int KEY = ke.getKeyCode();
				if(KEY == KeyEvent.VK_SPACE){
					if(scena.daLiRadi()){
						scena.pauzirajScenu();
					} else{
						scena.aktivirajScenu();
						scena.pocela();
					}
				}
				
				if(KEY == KeyEvent.VK_ESCAPE){
					scena.interrupt();
					dispose();
				}
			}
		});
	}

	public static void main(String[] args) {
		new Simulacija();
	}
}
