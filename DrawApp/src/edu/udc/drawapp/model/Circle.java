package edu.udc.drawapp.model;


import java.nio.ByteBuffer;

import edu.udc.drawapp.controler.handler.CircleHandler;
import edu.udc.drawapp.controler.handler.ShapeHandler;


public class Circle implements Shape {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8522211607656722145L;
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
		return Shape.CIRCLE_NAME + " " + center.x + "; "  + center.y + "; " + radius;
	}

	@Override
	public byte[] toArray() {
		byte[] bytes = new byte[12];
		ByteBuffer.wrap(bytes,0,4).putFloat(center.x);
	    ByteBuffer.wrap(bytes,4,4).putFloat(center.y);
	    ByteBuffer.wrap(bytes,8,4).putFloat(radius);
	    return bytes;
	}

	@Override
	public int getIndex() {
		return Shape.CIRCLE_IND;
	}

}
