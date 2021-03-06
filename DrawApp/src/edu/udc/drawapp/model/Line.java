package edu.udc.drawapp.model;

import java.nio.ByteBuffer;

import edu.udc.drawapp.model.handler.LineHandler;
import edu.udc.drawapp.model.handler.ShapeHandler;

public class Line implements Shape {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8835810285049577841L;
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
		return Shape.LINE_NAME + " " + a.x + "; "  + a.y + "; " + b.x + "; "  + b.y;
	}
	
	@Override
	public byte[] toArray() {
		byte[] bytes = new byte[16];
		ByteBuffer.wrap(bytes,0,4).putFloat(a.x);
	    ByteBuffer.wrap(bytes,4,4).putFloat(a.y);
		ByteBuffer.wrap(bytes,8,4).putFloat(b.x);
	    ByteBuffer.wrap(bytes,12,4).putFloat(b.y);
	    return bytes;
	}

	@Override
	public int getIndex() {
		return Shape.LINE_IND;
	}
}
