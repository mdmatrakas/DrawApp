package edu.udc.drawapp.persistence;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import edu.udc.drawapp.model.Shape;

//Correspond to the ConcreteStrategy class in the Strategy design pattern
public class TextShapeFile implements ShapeFile {
	private File file;

	public TextShapeFile(File file) {
		this.file = file;
	}

	// Correspond to the AlgorithmInterface() method frome the Strategy interface
	@Override
	public void saveFile(List<Shape> list) {
		// TODO Auto-generated method stub

	}

	// Correspond to the AlgorithmInterface() method frome the Strategy interface
	@Override
	public List<Shape> readFile() {
		// TODO Auto-generated method stub
		return new LinkedList<Shape>();
	}

}