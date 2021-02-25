package edu.udc.drawapp.model;

import edu.udc.drawapp.model.handler.ShapeHandler;

public interface Shape {
	
	ShapeHandler getHandler(); // método de fábrica para objetos da abstração (que implementam a interface) ShapeHandler

}
