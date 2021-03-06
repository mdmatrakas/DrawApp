package edu.udc.drawapp.persistence;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.udc.drawapp.model.*;

//Correspond to the ConcreteStrategy class in the Strategy design pattern
public class BinaryShapeFile extends ShapeFile {

	public BinaryShapeFile(File file) {
		super(file);
	}

	// Correspond to the AlgorithmInterface() method frome the Strategy interface
	@Override
	public void saveFile(List<Shape> list) {
		FileOutputStream output = null;

		try {
			output = new FileOutputStream(file);

			Shape shape;
			Iterator<Shape> it = list.iterator();

			byte[] bytes = new byte[4];

			byte array[];

			while (it.hasNext()) {
				shape = it.next();
				array = shape.toArray();
				ByteBuffer.wrap(bytes).putInt(array.length + 4);
				output.write(bytes);
				ByteBuffer.wrap(bytes).putInt(shape.getIndex());
				output.write(bytes);
				output.write(array);
			}

			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Correspond to the AlgorithmInterface() method frome the Strategy interface
	@Override
	public List<Shape> readFile() {
		List<Shape> list = new LinkedList<Shape>();
		FileInputStream input = null;

		try {
			input = new FileInputStream(file);
			Shape shape;
			byte tam[] = new byte[4];
			byte array[];
			while (input.read(tam) != -1) {
				array = new byte[ByteBuffer.wrap(tam).getInt()];
				input.read(array);
				shape = ShapeFactory(array);
				list.add(shape);
			}
			input.close();
		} catch (EOFException endOfFileException) { // fim do arquivo foi alcançado
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static Shape ShapeFactory(byte bytes[]) {
		int type = ByteBuffer.wrap(bytes, 0, 4).getInt();
		Shape shape = null;

		byte b[] = Arrays.copyOfRange(bytes, 4, bytes.length);

		switch(type) {
		case Shape.POINT_IND:
			shape = PointFactory(b);
			break;
		case Shape.LINE_IND:
			shape = LineFactory(b);
			break;
		case Shape.CIRCLE_IND:
			shape = CircleFactory(b);
			break;
		case Shape.QUADRILATERAL_IND:
			shape = QuadrilateralFactory(b);
			break;
		case Shape.TRIANGLE_IND:
			shape = TriangleFactory(b);
			break;
		case 6:
			break;
		}
		
		return shape;
	}

	// Metodo FactoryMethod() da classe ConcreteCreator do padrão Factory Method
	public static Point PointFactory(byte bytes[]) {
		float x = ByteBuffer.wrap(bytes, 0, 4).getFloat();
		float y = ByteBuffer.wrap(bytes, 4, 4).getFloat();
		return new Point(x, y);
	}

	// Metodo FactoryMethod() da classe ConcreteCreator do padrão Factory Method
	public static Quadrilateral QuadrilateralFactory(byte bytes[]) {
		float x = ByteBuffer.wrap(bytes, 0, 4).getFloat();
		float y = ByteBuffer.wrap(bytes, 4, 4).getFloat();
		Point p1 = new Point(x, y);
		x = ByteBuffer.wrap(bytes, 8, 4).getFloat();
		y = ByteBuffer.wrap(bytes, 12, 4).getFloat();
		Point p2 = new Point(x, y);
		x = ByteBuffer.wrap(bytes, 16, 4).getFloat();
		y = ByteBuffer.wrap(bytes, 20, 4).getFloat();
		Point p3 = new Point(x, y);
		x = ByteBuffer.wrap(bytes, 24, 4).getFloat();
		y = ByteBuffer.wrap(bytes, 28, 4).getFloat();
		Point p4 = new Point(x, y);
		return new Quadrilateral(p1, p2, p3, p4);
	}

	// Metodo FactoryMethod() da classe ConcreteCreator do padrão Factory Method
	public static Line LineFactory(byte bytes[]) {
		float ax = ByteBuffer.wrap(bytes, 0, 4).getFloat();
		float ay = ByteBuffer.wrap(bytes, 4, 4).getFloat();
		float bx = ByteBuffer.wrap(bytes, 8, 4).getFloat();
		float by = ByteBuffer.wrap(bytes, 12, 4).getFloat();
		return new Line(new Point(ax, ay), new Point(bx, by));
	}

	// Metodo FactoryMethod() da classe ConcreteCreator do padrão Factory Method
	public static Circle CircleFactory(byte bytes[]) {
		float ax = ByteBuffer.wrap(bytes, 0, 4).getFloat();
		float ay = ByteBuffer.wrap(bytes, 4, 4).getFloat();
		float r = ByteBuffer.wrap(bytes, 8, 4).getFloat();
		return new Circle(new Point(ax, ay), r);
	}

	// Metodo FactoryMethod() da classe ConcreteCreator do padrão Factory Method
	public static Triangle TriangleFactory(byte bytes[]) {
		float x = ByteBuffer.wrap(bytes, 0, 4).getFloat();
		float y = ByteBuffer.wrap(bytes, 4, 4).getFloat();
		Point p1 = new Point(x, y);
		x = ByteBuffer.wrap(bytes, 8, 4).getFloat();
		y = ByteBuffer.wrap(bytes, 12, 4).getFloat();
		Point p2 = new Point(x, y);
		x = ByteBuffer.wrap(bytes, 16, 4).getFloat();
		y = ByteBuffer.wrap(bytes, 20, 4).getFloat();
		Point p3 = new Point(x, y);
		return new Triangle(p1, p2, p3);
	}

}
