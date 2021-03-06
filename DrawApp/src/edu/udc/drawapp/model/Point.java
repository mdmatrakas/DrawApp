package edu.udc.drawapp.model;

import java.nio.ByteBuffer;

import edu.udc.drawapp.model.handler.PointHandler;
import edu.udc.drawapp.model.handler.ShapeHandler;

public class Point implements Shape {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1813253821028017448L;
	public float x;
	public float y;
	
	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float distance(Point p) {
		return (float)Math.sqrt( (p.x - x)*(p.x - x) + (p.y - y)*(p.y - y) ); 
	}

	public float distance(float x, float y) {
		return (float)Math.sqrt( (this.x - x)*(this.x - x) + (this.y - y)*(this.y - y) ); 
	}

	@Override
	public ShapeHandler getHandler() {
		return new PointHandler(this);
	}

	
	@Override
	public Point clone() {
		return new Point(x, y);
	}

	@Override
	public String toString() {
		return Shape.POINT_NAME + " " + x + "; "  + y;
	}
	
	@Override
	public byte[] toArray() {
		byte[] bytes = new byte[8];
		ByteBuffer.wrap(bytes,0,4).putFloat(x);
	    ByteBuffer.wrap(bytes,4,4).putFloat(y);
	    return bytes;
	}

	@Override
	public int getIndex() {
		return Shape.POINT_IND;
	}
}
