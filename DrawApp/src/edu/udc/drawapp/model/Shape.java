package edu.udc.drawapp.model;


import java.io.Serializable;

import edu.udc.drawapp.controler.handler.ShapeHandler;

public interface Shape extends Serializable {
	
	ShapeHandler getHandler(); // método de fábrica para objetos da abstração (que implementam a interface) ShapeHandler

	Shape clone();
	byte[] toArray();
	
	int getIndex();
	
	static int POINT_IND = 1;
	static int LINE_IND = 2;
	static int CIRCLE_IND = 3;
	static int QUADRILATERAL_IND = 4;
	static int TRIANGLE_IND = 5;
	
	static String POINT_NAME = "Point";
	static String LINE_NAME = "Line";
	static String CIRCLE_NAME = "Circle";
	static String QUADRILATERAL_NAME = "Quadrilateral";
	static String TRIANGLE_NAME = "Triangle";

}
