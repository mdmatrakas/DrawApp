package edu.udc.drawapp.model;

import edu.udc.drawapp.controler.handler.CircleHandler;
import edu.udc.drawapp.controler.handler.ShapeHandler;

public class Circle implements Shape {
	public Point center;
	public float radius;
	
	public Circle(Point center, float radius) {
		this.center = center;
		this.radius = radius;
	}

	@Override
	public ShapeHandler getHandler() {
		return new CircleHandler(this);
	}

	@Override
	public Circle clone() {
		return new Circle(center.clone(), radius);
	}

	@Override
	public String toString() {
		return "Circle [center=" + center + ", radius=" + radius + "]";
	}

}
