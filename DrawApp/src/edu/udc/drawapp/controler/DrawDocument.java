package edu.udc.drawapp.controler;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import edu.udc.drawapp.gui.DrawView;
import edu.udc.drawapp.model.Shape;
import edu.udc.drawapp.persistence.BinaryShapeFile;
import edu.udc.drawapp.persistence.SerialShapeFile;
import edu.udc.drawapp.persistence.ShapeFile;
import edu.udc.drawapp.persistence.TextShapeFile;

public class DrawDocument {
	private ShapeFile shapeFile = null;
	private List<Shape> shapeList;
	private List<DrawView> observerList;
	private Shape drawingShape;
	
	public DrawDocument() {
		shapeList = new LinkedList<Shape>();
		observerList = new LinkedList<DrawView>();
	}
	
	public void addObserver(DrawView view) {
		observerList.add(view);
	}
	
	public void removeObserver(DrawView view) {
		observerList.remove(view);
	}
	
	private void updateObservers() {
		for(DrawView view:observerList ) {
			view.update();
		}
	}
	
	public void drawShape(Shape shape) {
		drawingShape = shape;
	}
	
	public Shape getDrwingShape() {
		return drawingShape;
	}
	
	public void endDrawing() {
		shapeList.add(drawingShape);
		drawingShape = drawingShape.clone();
		updateObservers();
	}
	
	public void novoArquivo() {
		shapeList.clear();;
		updateObservers();
	}
	
	public void lerArquivo(File file) {
		chooseFileType(file);
		
		shapeList.clear();
		shapeList = shapeFile.readFile();
		updateObservers();
	}

	public void salvarArquivo(File file) {
		chooseFileType(file);
		
		shapeFile.saveFile(shapeList);
	}
	
	private void chooseFileType(File f) {
		String name = f.getName();
		String ext = name.substring(name.lastIndexOf('.') + 1);
		
		if(ext.compareTo("ser") == 0) 
			shapeFile = new SerialShapeFile(f);
		if(ext.compareTo("txt") == 0) 
			shapeFile = new TextShapeFile(f);
		if(ext.compareTo("bin") == 0) 
			shapeFile = new BinaryShapeFile(f);	
	}
	
	public void addShape(Shape shape) {
		shapeList.add(shape);
		updateObservers();
	}
	
	public ListIterator<Shape> getShapeIterator() {
		return shapeList.listIterator();
	}

}
