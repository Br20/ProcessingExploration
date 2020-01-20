package circlePackingPackage;

import java.awt.Color;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class CirclePacking extends PApplet{
	
	
	//..........CLASE CIRCULO......................//
	
	private class Circle {
		float x;
		float y;
		float r;
		boolean growing = true;
		
		
		Circle(float x_, float y_){
			this.x = x_;
			this.y = y_;
			r = 2;
		}
		
		void show() {
			if (!mid) {
				stroke(255);
			}else {
				float r = random(255);
				float g = random(255);
				float b = random(255);
				stroke(r,g,b);
			}
			strokeWeight(2);
			noFill();
			ellipse(x, y, r*2, r*2);
		}
	
		void grow() {
			if (growing) {
				r = (float) (r + 0.5);
			}
		}
		
		void deGrow() {
			if (r > 0.5)
				r = (float) (r - 0.1);
//			else 
//				cant++;
		}
		
		boolean edges() {
			return (x + r > width || y + r > height || x - r < 0 || y - r < 0);
		}
	}
	//..........CLASE CIRCULO......................//


	
	
	PImage img;
	ArrayList<Circle> circles;
	ArrayList<PVector> spots;
	boolean mid = false;
	int cant = 0;
	
	
	
	public static void main(String[] args) {
		PApplet.main("circlePackingPackage.CirclePacking");
	}
	
	public void settings() {
		size(900,400);
		
	}
	
	public void setup() {
		//frameRate(15);
		cant = 0;
		spots = new ArrayList<PVector>();
		circles = new ArrayList<Circle>();
		if (!mid) {
			img = loadImage("2018.png");

		}
		else {
			img = loadImage("2019.png");

		}
		img.loadPixels();
		for (int i = 0 ; i < img.width; i++) {
			for (int j = 0; j < img.height; j++) {
				int index = i + j * img.width;
				Color col = new Color(img.pixels[index]);
				float bright =  brightness(col.getRGB());
				if (bright > 1 ) {
					spots.add (new PVector(i,j));
				}
			}
		}
	}
	
	

	
	public void draw() {

		background(0);
		int attempts = 0;
		int total = 7;
		int count = 0;

		
		if (!mid) {
			while (count < total) {
				Circle newC = newCircle();
				if (newC != null) {
					circles.add(newC);
					count++;
				}
				attempts++;
				if (attempts > 20) {
					mid = true;
					break;
				}
			}
		
			for (Circle c : circles) {
				if (c.growing) {
					if (c.edges()) {
						c.growing = false;
					}else {
						for (Circle other : circles) {
							if (c != other) {
								float d = dist(c.x,c.y,other.x,other.y);
								if (d < (c.r + other.r + 3)) {
									c.growing = false;
									break;
								}
							}
						}
					}
				}
				c.grow();
				c.show();
	
			}
		}else {
			for (Circle circ : circles) {
				circ.show();
				circ.deGrow();
			}
			for (Circle circ : circles) {
				if (circ.r <= 0.5)
					cant++;
				else 
					cant = 0;
				if (cant == circles.size()) {
					//img = loadImage("2019.png");
					
					setup();
					mid = false;
					
				
					
				}
			}
		}
		
	}
	
	
	private Circle newCircle() {
		
		int rand = (int) (random (0, spots.size()));
		PVector spot = spots.get(rand);
		float x = spot.x;
		float y = spot.y;
		
		boolean valid = true;
		for (Circle c: circles) {
			float d = dist(x,y,c.x, c.y);
			if (d < c.r) {
				valid = false;
				break;
			}
		}
		
		if (valid) {
			return new Circle(x,y);
		}else {
			return null;
		}
		
	}
	

}
