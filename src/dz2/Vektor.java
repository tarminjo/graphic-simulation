package dz2;

public class Vektor {
	private double x, y;
	
	Vektor(double argX, double argY){
		x = argX;
		y = argY;
	}
	
	//Konstruktor za pseudoslucajan vektor(vektor pomeraja)
	Vektor(){
		while(true) {
			double x1 = Math.random(), y1 = Math.random();
			double xx = Math.random(), yy = Math.random();
			if(xx >= 0.5) {
				x = -1 * x1;
			} else {
				x = x1;
			}
			if(yy >= 0.5) {
				y = -1 * y1;
			} else {
				y = y1;
			}
			
			if(x != 0 && y != 0) break;
		}
	}
	
	//Izracunavanje ort vektora
	public Vektor ortVektor() {
		Vektor ort = new Vektor
				(x / (Math.pow(Math.pow(x, 2) + Math.pow(y, 2), 0.5)), y / (Math.pow(Math.pow(x, 2) + Math.pow(y, 2), 0.5)));
		
		return ort;
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
