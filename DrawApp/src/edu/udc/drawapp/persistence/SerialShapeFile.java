package edu.udc.drawapp.persistence;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.LinkedList;
import java.util.List;

import edu.udc.drawapp.model.Shape;

//Correspond to the ConcreteStrategy class in the Strategy design pattern
public class SerialShapeFile extends ShapeFile {

	public SerialShapeFile(File file) {
		super(file);
	}

	// Correspond to the AlgorithmInterface() method frome the Strategy interface
	@Override
	public void saveFile(List<Shape> list) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
			
			for(Shape s: list) {
				oos.writeObject(s);
			}
		}
		catch(IOException e) {
			// error writing to file
			// show the stack trace and continue
			
			//TODO: Show error message to usr
			e.printStackTrace();
			try {
				oos.close();
			}
			catch(IOException ioe) {
				ioe.printStackTrace();
			}
			return;
		}
		try {
			oos.close();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	// Correspond to the AlgorithmInterface() method frome the Strategy interface
	@Override
	public List<Shape> readFile() {
		List<Shape> list = new LinkedList<Shape>();
		ObjectInputStream ois = null;
		
		Shape shape = null;
		
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			while(true) {
				shape = (Shape) ois.readObject();
				list.add(shape);
			}				
		}
		catch(EOFException eof) {
			// all the file contents where read
			
			//TODO: Show error message to usr
			try {
				ois.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			
			return list;
		}
		catch(IOException e) {
			// error reading the file, show the stack trace and continue
			
			//TODO: Show error message to usr - remove stack trace
			e.printStackTrace();
			try {
				ois.close();
			}
			catch(IOException ioe) {
				ioe.printStackTrace();
			}
			return null;
		}
		catch(ClassCastException e) {
			// problem in the file content, show stack trace and continue
			
			//TODO: Show error message to usr - remove stack trace
			e.printStackTrace();
			try {
				ois.close();
			}
			catch(IOException ioe) {
				ioe.printStackTrace();
			}
			return null;
		}
		catch(ClassNotFoundException e) {
			// error reading class from file - not correct class vertion?
			// show the stack trace and continue
			
			//TODO: Show error message to usr - remove stack trace
			e.printStackTrace();
			try {
				ois.close();
			}
			catch(IOException ioe) {
				ioe.printStackTrace();
			}
			return null;
		}
	}

}