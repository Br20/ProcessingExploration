package fourierSeriesPackage;

import java.util.ArrayList;

import processing.core.PApplet;

public class FourierSerie extends PApplet {

	private float angulo;
	public ArrayList<Float> onda = new ArrayList<Float>();
	
	
	
	public static void main (String [] args) {
		PApplet.main("fourierSeriesPackage.FourierSerie");
	}
	
	public void settings() {
		size(600,400);
	}
	
	public void setup() {

	}
	
	public void draw() {
		background(0);
		translate(150,200);	
		float x = 0;
		float y = 0;
		
		//aumnetando el valor 50 se logra mas precision en la onda cuadrada
		for(float i = 0; i < 50 ; i++) {
			float prevX = x;
			float prevY = y;
			float n = i * 2 + 1;
			float radio = 75 * (4 / (n * PI));
			x += radio * cos( n * angulo);
			y += radio * sin( n * angulo);
			stroke(255,100);
			noFill();
			ellipse(prevX, prevY, radio*2 ,radio *2);
			fill(255);
			stroke(255);
			line(prevX,prevY,x,y);
		}
		onda.add(0,y);
		translate(200,0);
		line(x-200,y,0,onda.get(0));
		beginShape();
		noFill();
		for( int i = 0; i < onda.size( ); i++ ){
	       vertex(i, onda.get(i));
	    }
		endShape();
		
		if (onda.size() > 250)
			onda.remove(onda.size() - 1 );
			
		angulo += 0.05;
		
	}
	

	
	
}
