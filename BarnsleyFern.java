package barnsleyFernPackage;

import processing.core.PApplet;

public class BarnsleyFern extends PApplet {

	float x;
	float y;

	public static void main(String [] args) {
		PApplet.main("barnsleyFernPackage.BarnsleyFern");
	}

	public void settings() {
		size(600,600);
	}

	public void setup() {
		background(0);
	}
	
	public void nextPoint() {
		double r = Math.random();
		float nextX;
		float nextY;
		
		if (r < 0.01) {
			//Transformation 1
			nextX = 0;
			nextY = (float) 0.16 * y;
		}else if (r < 0.86) {
			//Transformation 2
			nextX = (float) (0.85 * x + 0.04 * y);
			nextY = (float) ((-0.04) * x + 0.85 * y + 1.6);
		}else if (r < 0.93) {	
			//Transformation 3
			nextX = (float) (0.2 * x + (-0.26) * y);
			nextY = (float) (0.23 * x + 0.22 * y + 1.6);
		}else {
			//Transformation 4
			nextX = (float) ((-0.15) * x + 0.28 * y);
			nextY = (float) (0.26 * x + 0.24 * y + 0.44);
		}

		x = nextX;
		y = nextY;
	}
	
	public void drawPoint() {
		strokeWeight(2);
		float px = map(x, (float)-2.1820, (float)2.6558, 0, width);
		float py = map(y, 0, (float)9.9983, height, 0);
		if (py < 100)
			stroke(255,0,0);
		else if (py < 200)
			stroke (0,255,0);
		else if (py< 300) {
			stroke(0,0,255);
		}else if (py < 400) {
			stroke(125,200,20);
		}else if (py < 500) {
			stroke(123,123,123);
		}else 
			stroke(150,255,150);
		point(px,py);
	}
	
	

	public void draw() {
		for (int i = 0; i < 100; i++) {
			drawPoint();
			nextPoint();
		}

	}




}
