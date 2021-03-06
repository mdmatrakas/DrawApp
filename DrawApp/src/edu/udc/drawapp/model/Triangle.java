package edu.udc.drawapp.model;

import edu.udc.drawapp.model.handler.TriangleHandler;
import edu.udc.drawapp.model.handler.ShapeHandler;

public class Triangle implements Shape {
	public Point a;
	public Point b;
	public Point c;
	
	public Triangle(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	public ShapeHandler getHandler() {
		return new TriangleHandler(this);
	}

	@Override
	public Triangle clone() {
		return new Triangle(a.clone(), b.clone(), c.clone());
	}

	@Override
	public String toString() {
		return "Triangle [a=" + a + ", b=" + b + ", c=" + c + "]";
	}
}
