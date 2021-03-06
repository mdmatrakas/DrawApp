package edu.udc.drawapp.persistence;

import java.util.List;

import edu.udc.drawapp.model.Shape;


// Correspond to the Strategy abstraction in the Strategy design pattern
public interface ShapeFile {
	void saveFile(List<Shape> list);
	List<Shape> readFile();
}
