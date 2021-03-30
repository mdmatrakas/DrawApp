package edu.udc.drawapp.model;


import java.nio.ByteBuffer;

import edu.udc.drawapp.controler.handler.QuadrilateralHandler;
import edu.udc.drawapp.controler.handler.ShapeHandler;

public class Quadrilateral implements Shape {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8354494735400827637L;
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
		return Shape.QUADRILATERAL_NAME + " " + a.x + "; "  + a.y + "; " + b.x + "; "  + b.y + "; " + c.x + "; "  + c.y + "; " + d.x + "; "  + d.y;
	}
	
	@Override
	public byte[] toArray() {
		byte[] bytes = new byte[32];
		ByteBuffer.wrap(bytes,0,4).putFloat(a.x);
	    ByteBuffer.wrap(bytes,4,4).putFloat(a.y);
		ByteBuffer.wrap(bytes,8,4).putFloat(b.x);
	    ByteBuffer.wrap(bytes,12,4).putFloat(b.y);
		ByteBuffer.wrap(bytes,16,4).putFloat(c.x);
	    ByteBuffer.wrap(bytes,20,4).putFloat(c.y);
		ByteBuffer.wrap(bytes,24,4).putFloat(d.x);
	    ByteBuffer.wrap(bytes,28,4).putFloat(d.y);
	    return bytes;
	}

	@Override
	public int getIndex() {
		return Shape.QUADRILATERAL_IND;
	}
}
