package edu.udc.drawapp.gui;

import javax.swing.JPanel;

import edu.udc.drawapp.model.*;
import edu.udc.drawapp.persistence.ShapeFile;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.LinkedList;
import java.util.List;

public class DrawPanel extends JPanel {

	private Shape shape;

	private List<Shape> shapeList;
	
	/**
	 * Create the panel.
	 */
	public DrawPanel() {
		
		shapeList = new LinkedList<Shape>();
		
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				if(shape != null) {
					shape.getHandler().mouseMove(e.getX(), e.getY());
					repaint();
				}
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {			
				if(shape != null) {
					if(shape.getHandler().mouseClick(e.getX(), e.getY())) {
						shapeList.add(shape);
						shape = shape.clone();
					}
					repaint();
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for(Shape s: shapeList) {
			s.getHandler().paint(g);
		}
		if(shape != null) {
			shape.getHandler().paint(g);
		}
	}

	public void desenharTipo(Shape shape) {
		this.shape = shape;
	}

	public void lerArquivo(ShapeFile shapeFile) {
		shapeList.clear();
		shapeList = shapeFile.readFile();
		repaint();
	}

	public void salvarArquivo(ShapeFile shapeFile) {
		shapeFile.saveFile(shapeList);
	}

	public void novoArquivo() {
		shapeList.clear();;
		repaint();
	}

}
