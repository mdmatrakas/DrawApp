package edu.udc.drawapp.model;

import edu.udc.drawapp.controler.handler.LineHandler;
import edu.udc.drawapp.controler.handler.ShapeHandler;

public class Line implements Shape {
	public Point a;
	public Point b;
	
	public Line(Point a, Point b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public ShapeHandler getHandler() {
		return new LineHandler(this);
	}
	
	@Override
	public Line clone() {
		return new Line(a.clone(), b.clone());
	}

	@Override
	public String toString() {
		return "Line [a=" + a + ", b=" + b + "]";
	}
}
