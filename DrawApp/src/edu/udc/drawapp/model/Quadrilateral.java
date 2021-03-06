package edu.udc.drawapp.model;

import edu.udc.drawapp.model.handler.QuadrilateralHandler;
import edu.udc.drawapp.model.handler.ShapeHandler;

public class Quadrilateral implements Shape {
	public Point a;
	public Point b;
	public Point c;
	public Point d;
	
	public Quadrilateral(Point a, Point b, Point c, Point d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	@Override
	public ShapeHandler getHandler() {
		return new QuadrilateralHandler(this);
	}

	@Override
	public Quadrilateral clone() {
		return new Quadrilateral(a.clone(), b.clone(), c.clone(), d.clone());
	}

	@Override
	public String toString() {
		return "Rectangle [a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + "]";
	}
}
