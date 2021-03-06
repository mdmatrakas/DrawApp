package edu.udc.drawapp.persistence;

import java.io.File;
import java.util.List;

import edu.udc.drawapp.model.Shape;


// Correspond to the Strategy abstraction in the Strategy design pattern
public abstract class ShapeFile {
	protected File file;

	public ShapeFile(File file) {
		this.file = file;
	}
	
	abstract public void saveFile(List<Shape> list);
	abstract public List<Shape> readFile();
}
