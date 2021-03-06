package edu.udc.drawapp.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import edu.udc.drawapp.model.Circle;
import edu.udc.drawapp.model.Line;
import edu.udc.drawapp.model.Point;
import edu.udc.drawapp.model.Quadrilateral;
import edu.udc.drawapp.model.Shape;
import edu.udc.drawapp.model.Triangle;

//Correspond to the ConcreteStrategy class in the Strategy design pattern
public class TextShapeFile extends ShapeFile {

	public TextShapeFile(File file) {
		super(file);
	}

	// Correspond to the AlgorithmInterface() method frome the Strategy interface
	@Override
	public void saveFile(List<Shape> list) {
		FileWriter output; // objeto utilizado para gerar saída de texto no arquivo

		try {
			output = new FileWriter(file);

			Shape shape;
			Iterator<Shape> it = list.iterator();

			while (it.hasNext()) {
				shape = it.next();
				output.append(shape.toString() + "\n");
			}

			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Correspond to the AlgorithmInterface() method frome the Strategy interface
	@Override
	public List<Shape> readFile() {
		List<Shape> list = new LinkedList<Shape>();
		Scanner input = null;

		try {
			input = new Scanner(file);
			while (input.hasNextLine()) {
				String str = input.nextLine();
				Shape shape = ShapeFactory(str);
				
				list.add(shape);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static Shape ShapeFactory(String shapeStr) {
		int i = shapeStr.indexOf(' ');
		String name = shapeStr.substring(0, i);
		Shape shape= null;
		
		if(name.toLowerCase().equals(Shape.POINT_NAME.toLowerCase()))
			shape = PointFactory(shapeStr.substring(i));
		else if(name.toLowerCase().equals(Shape.LINE_NAME.toLowerCase()))
			shape = LineFactory(shapeStr.substring(i));
		else if(name.toLowerCase().equals(Shape.CIRCLE_NAME.toLowerCase()))
			shape = CircleFactory(shapeStr.substring(i));
		else if(name.toLowerCase().equals(Shape.QUADRILATERAL_NAME.toLowerCase()))
			shape = QuadrilateralFactory(shapeStr.substring(i));
		else if(name.toLowerCase().equals(Shape.TRIANGLE_NAME.toLowerCase()))
			shape = TriangleFactory(shapeStr.substring(i));
		
		return shape;
	}

	// Metodo FactoryMethod() da classe ConcreteCreator do padrão Factory Method
	public static Point PointFactory(String shapeStr) {
		int i = shapeStr.indexOf(';');
		float x = Float.parseFloat(shapeStr.substring(1, i).replace(',', '.'));
		float y = Float.parseFloat(shapeStr.substring(i+1, shapeStr.length()-1).replace(',', '.'));
		return new Point(x, y);
	}

	// Metodo FactoryMethod() da classe ConcreteCreator do padrão Factory Method
	public static Quadrilateral QuadrilateralFactory(String shapeStr) {
		int i = shapeStr.indexOf(';');
		int ii = shapeStr.indexOf(';', i+1);
		float x = Float.parseFloat(shapeStr.substring(1, i).replace(',', '.'));
		float y = Float.parseFloat(shapeStr.substring(i+1, ii).replace(',', '.'));
		Point a = new Point(x, y);
		
		int o = ii+1;
		i = shapeStr.indexOf(';', o);
		ii = shapeStr.indexOf(';', i+1);
		x = Float.parseFloat(shapeStr.substring(o, i).replace(',', '.'));
		y = Float.parseFloat(shapeStr.substring(i+1, ii).replace(',', '.'));
		Point b = new Point(x, y);
		
		o = ii+1;
		i = shapeStr.indexOf(';', o);
		ii = shapeStr.indexOf(';', i+1);
		x = Float.parseFloat(shapeStr.substring(o, i).replace(',', '.'));
		y = Float.parseFloat(shapeStr.substring(i+1, ii).replace(',', '.'));
		Point c = new Point(x, y);
		
		o = ii+1;
		i = shapeStr.indexOf(';', o);
		x = Float.parseFloat(shapeStr.substring(o, i).replace(',', '.'));
		y = Float.parseFloat(shapeStr.substring(i+1, shapeStr.length()-1).replace(',', '.'));
		Point d = new Point(x, y);
		
		return new Quadrilateral(a, b, c, d);
	}

	// Metodo FactoryMethod() da classe ConcreteCreator do padrão Factory Method
	public static Line LineFactory(String shapeStr) {
		int i = shapeStr.indexOf(';');
		int ii = shapeStr.indexOf(';', i+1);
		float x = Float.parseFloat(shapeStr.substring(1, i).replace(',', '.'));
		float y = Float.parseFloat(shapeStr.substring(i+1, ii).replace(',', '.'));
		Point a = new Point(x, y);
		
		int o = ii+1;
		i = shapeStr.indexOf(';', o);
		x = Float.parseFloat(shapeStr.substring(o, i).replace(',', '.'));
		y = Float.parseFloat(shapeStr.substring(i+1, shapeStr.length()-1).replace(',', '.'));
		Point b = new Point(x, y);
		
		return new Line(a, b);
	}

	// Metodo FactoryMethod() da classe ConcreteCreator do padrão Factory Method
	public static Circle CircleFactory(String shapeStr) {
		int i = shapeStr.indexOf(';');
		int ii = shapeStr.indexOf(';', i+1);
		float x = Float.parseFloat(shapeStr.substring(1, i).replace(',', '.'));
		float y = Float.parseFloat(shapeStr.substring(i+1, ii).replace(',', '.'));
		Point a = new Point(x, y);
		
		float r = Float.parseFloat(shapeStr.substring(ii+1, shapeStr.length()-1).replace(',', '.'));

		return new Circle(a, r);
	}

	// Metodo FactoryMethod() da classe ConcreteCreator do padrão Factory Method
	public static Triangle TriangleFactory(String shapeStr) {
		int i = shapeStr.indexOf(';');
		int ii = shapeStr.indexOf(';', i+1);
		float x = Float.parseFloat(shapeStr.substring(1, i).replace(',', '.'));
		float y = Float.parseFloat(shapeStr.substring(i+1, ii).replace(',', '.'));
		Point a = new Point(x, y);
		
		int o = ii+1;
		i = shapeStr.indexOf(';', o);
		ii = shapeStr.indexOf(';', i+1);
		x = Float.parseFloat(shapeStr.substring(o, i).replace(',', '.'));
		y = Float.parseFloat(shapeStr.substring(i+1, ii).replace(',', '.'));
		Point b = new Point(x, y);
		
		o = ii+1;
		i = shapeStr.indexOf(';', o);
		x = Float.parseFloat(shapeStr.substring(o, i).replace(',', '.'));
		y = Float.parseFloat(shapeStr.substring(i+1, shapeStr.length()-1).replace(',', '.'));
		Point c = new Point(x, y);
		
		return new Triangle(a, b, c);
	}
}