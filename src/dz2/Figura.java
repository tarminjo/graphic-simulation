package dz2;

import java.awt.Color;
import java.awt.Graphics;

abstract public class Figura {
	protected Vektor vektorPolozaja;
	protected Vektor vektorPomeraja;
	protected int poluprecnik = 20;
	
	Figura(Vektor vPolozaj, Vektor vPomeraj){
		vektorPolozaja = vPolozaj;
		vektorPomeraja = vPomeraj;
	}
	
	public boolean unutarKruznice(Vektor vPol) {
		return (Math.pow(this.vektorPolozaja.getX() - vPol.getX(), 2) +
					Math.pow(this.vektorPolozaja.getY() - vPol.getY(), 2)) 
					<= Math.pow(this.poluprecnik, 2);
	}
	
	public Vektor getVektorPolozaja() {
		return vektorPolozaja;
	}

	public Vektor getVektorPomeraja() {
		return vektorPomeraja;
	}

	public int getPoluprecnik() {
		return poluprecnik;
	}

	public boolean preklapanjeFigura(Figura argFigura) {
		return (Math.pow(this.vektorPolozaja.getX() - argFigura.vektorPolozaja.getX(), 2) + 
				Math.pow(this.vektorPolozaja.getY() - argFigura.vektorPolozaja.getY(), 2)) < 
				Math.pow(this.poluprecnik +argFigura.poluprecnik, 2);
	}
	
	abstract public Color getBoja();
	
	abstract public void paint(Graphics g);
}
