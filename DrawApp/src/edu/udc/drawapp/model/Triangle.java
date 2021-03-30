package edu.udc.drawapp.model;

import java.nio.ByteBuffer;

import edu.udc.drawapp.controler.handler.ShapeHandler;
import edu.udc.drawapp.controler.handler.TriangleHandler;

public class Triangle implements Shape {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2302441377860477775L;
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
		return Shape.TRIANGLE_NAME + " " + a.x + "; "  + a.y + "; " + b.x + "; "  + b.y + "; " + c.x + "; "  + c.y;
	}
	
	@Override
	public byte[] toArray() {
		byte[] bytes = new byte[24];
		ByteBuffer.wrap(bytes,0,4).putFloat(a.x);
	    ByteBuffer.wrap(bytes,4,4).putFloat(a.y);
		ByteBuffer.wrap(bytes,8,4).putFloat(b.x);
	    ByteBuffer.wrap(bytes,12,4).putFloat(b.y);
		ByteBuffer.wrap(bytes,16,4).putFloat(c.x);
	    ByteBuffer.wrap(bytes,20,4).putFloat(c.y);
	    return bytes;
	}

	@Override
	public int getIndex() {
		return Shape.TRIANGLE_IND;
	}
}
