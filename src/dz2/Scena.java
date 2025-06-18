package dz2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class Scena extends Canvas implements Runnable{
	private Simulacija vlasnik;
	private boolean radi;
	private boolean tekPocela;
	private Thread nit = new Thread(this);
	private ArrayList<Figura> listaFigura = new ArrayList<Figura>();;
	
	public void paint(Graphics g) {
		for(Figura f:listaFigura) f.paint(getGraphics());
		if(!radi && !tekPocela) {
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
			g.drawString("PAUZA", 180, 200);
		}
	}
	
	@Override
	public void run() {
		try {
			while (!nit.isInterrupted()) {
				synchronized (this) {
					while (!radi) {
						wait();
					}
				}
				
				azurirajPozicije();
				repaint();
				Thread.sleep(10);
			}
		} catch (InterruptedException e) {}
	}
	
	public Scena(Simulacija sim){
		vlasnik = sim;
		radi = false;
		tekPocela = true;
		
		dodajOsluskivace();
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(500, 400));
		
		nit.start();
	}
	
	private void azurirajPozicije() {
		for(Figura fig: listaFigura) {
			double fig_x = fig.getVektorPolozaja().getX(), fig_y = fig.getVektorPolozaja().getY();
			Vektor fig_ort = fig.getVektorPomeraja().ortVektor();
			
			fig.getVektorPolozaja().setX(fig_x + fig_ort.getX()*3);
			fig.getVektorPolozaja().setY(fig_y + fig_ort.getY()*3);
			
			if(fig.getVektorPolozaja().getX() < fig.getPoluprecnik() ||
					fig.getVektorPolozaja().getX() >(getWidth() - fig.getPoluprecnik())) {
				double x = fig.getVektorPomeraja().getX();
				
				fig.getVektorPomeraja().setX(-1 * x);
				
			}
			if(fig.getVektorPolozaja().getY() < fig.getPoluprecnik()||
					fig.getVektorPolozaja().getY() >(getHeight() - fig.getPoluprecnik())) {
				
				double y = fig.getVektorPomeraja().getY();
				
				fig.getVektorPomeraja().setY(-1 * y);
			}
		}
		
		for(Figura fig: listaFigura) {
			for(int i = listaFigura.indexOf(fig) + 1; i < listaFigura.size(); i++) {
				if(fig.preklapanjeFigura(listaFigura.get(i)) && listaFigura.indexOf(fig) != i) {
					double xPol1 = fig.getVektorPolozaja().getX();
					double yPol1 = fig.getVektorPolozaja().getY();
					double xPom1 = fig.getVektorPomeraja().getX();
					double yPom1 = fig.getVektorPomeraja().getY();
					
					double xPol2 = listaFigura.get(i).getVektorPolozaja().getX();
					double yPol2 = listaFigura.get(i).getVektorPolozaja().getY();
					double xPom2 = listaFigura.get(i).getVektorPomeraja().getX();
					double yPom2 = listaFigura.get(i).getVektorPomeraja().getY();
				
					//update za x
					fig.getVektorPomeraja().setX(
							xPom1 - (((xPom1 - xPom2)*(xPol1 - xPol2) + (yPom1 - yPom2) * (yPol1 - yPol2)) / 
							(Math.pow(xPol1 - xPol2, 2) + Math.pow(yPol1 - yPol2, 2)) * (xPol1 - xPol2))
							);
					listaFigura.get(i).getVektorPomeraja().setX(
							xPom2 - (((xPom2 - xPom1)*(xPol2 - xPol1) + (yPom2 - yPom1) * (yPol2 - yPol1)) / 
									(Math.pow(xPol2 - xPol1, 2) + Math.pow(yPol2 - yPol1, 2)) * (xPol2 - xPol1))
							);
					
					//update za y
					fig.getVektorPomeraja().setY(
							yPom1 - (((xPom1 - xPom2)*(xPol1 - xPol2) + (yPom1 - yPom2) * (yPol1 - yPol2)) / 
							(Math.pow(xPol1 - xPol2, 2) + Math.pow(yPol1 - yPol2, 2)) * (yPol1 - yPol2))
							);
					listaFigura.get(i).getVektorPomeraja().setY(
							yPom2 - (((xPom2 - xPom1)*(xPol2 - xPol1) + (yPom2 - yPom1) * (yPol2 - yPol1)) / 
									(Math.pow(xPol2 - xPol1, 2) + Math.pow(yPol2 - yPol1, 2)) * (yPol2 - yPol1))
							);
				}
			}
		}
	}
	
	private void dodajOsluskivace() {
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if(me.getButton() == MouseEvent.BUTTON1) {
					if(!radi){
						int argX = me.getX(), argY = me.getY();
						Vektor argPolozaja = new Vektor(argX, argY);
						Vektor argPomeraja = new Vektor();
						
						Figura novaFigura = new Disk(argPolozaja, argPomeraja);
						
						boolean ispis = true;
						for(Figura f: listaFigura) {
							if(novaFigura.preklapanjeFigura(f)) ispis = false;
						}
						
						double x = novaFigura.getVektorPolozaja().getX();
						double y = novaFigura.getVektorPolozaja().getY();
						
						if(x < novaFigura.getPoluprecnik() ||
								x > (getWidth() - novaFigura.getPoluprecnik()) ||
								y < novaFigura.getPoluprecnik() ||
								y >(getHeight() - novaFigura.getPoluprecnik())) 
							ispis = false;
						
						if(ispis) listaFigura.add(novaFigura);
						
						repaint();
					}
				}
				vlasnik.requestFocus();
			}
		});
		
	}
	
	public synchronized void interrupt() {
		nit.interrupt();
	}
	
	synchronized boolean daLiRadi() {
		return radi;
	}

	public synchronized void aktivirajScenu() {
		radi = true;
		notify();
	}
	
	public synchronized void pauzirajScenu() {
		radi = false;
		repaint();
	}
	
	public synchronized void pocela() {
		tekPocela = false;
	}
} 
