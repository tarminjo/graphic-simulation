package dz2;

import java.awt.Color;
import java.awt.Graphics;

public class Disk extends Figura {
	private Color boja;
	
	Disk(Vektor vPolozaj, Vektor vPomeraj){
		super(vPolozaj, vPomeraj);	
		boja = Color.BLUE;
	}

	@Override
	public Color getBoja() {
		return boja;
	}
	
	public void paint(Graphics g) {
		int[] xKoord = new int[8], yKoord = new int[8]; 
		crtanjeDiska(xKoord, yKoord);
		
		Color prevColor = g.getColor();
		g.drawPolygon(xKoord, yKoord, 8);
		g.setColor(boja);
		g.fillPolygon(xKoord, yKoord, 8);
	}
	
	private void crtanjeDiska(int[] xKoord, int[] yKoord) {
		double x = this.vektorPolozaja.getX(), y = this.getVektorPolozaja().getY();
		
		xKoord[0] = (int)x;
		yKoord[0] = (int)(y - poluprecnik);
		
		xKoord[1] = (int)(x + poluprecnik*Math.sqrt(2)/2);
		yKoord[1] = (int)(y - poluprecnik*Math.sqrt(2)/2);
		
		xKoord[2] = (int)(x + poluprecnik);
		yKoord[2] = (int)y;
		
		xKoord[3] = (int)(x + poluprecnik*Math.sqrt(2)/2);
		yKoord[3] = (int)(y + poluprecnik*Math.sqrt(2)/2);
		
		xKoord[4] = (int)x;
		yKoord[4] = (int)(y + poluprecnik);
		
		xKoord[5] = (int)(x - poluprecnik*Math.sqrt(2)/2);
		yKoord[5] = (int)(y + poluprecnik*Math.sqrt(2)/2);
		
		xKoord[6] = (int)(x - poluprecnik);
		yKoord[6] = (int)y;
		
		xKoord[7] = (int)(x - poluprecnik*Math.sqrt(2)/2);
		yKoord[7] = (int)(y - poluprecnik*Math.sqrt(2)/2);
	}
}
