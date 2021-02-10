package edu.udc.drawapp.gui;

import javax.swing.JPanel;

import edu.udc.drawapp.model.*;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class DrawPanel extends JPanel {

	/*
	 * Determins the geometry object type
	 * 0 - none
	 * 1 - point
	 * 2 - line
	 * 3 - circle
	 * 4 - rectangle
	 * 5 - triangle
	 */
	private int geometryType = 0;
	
	private Point point;
	private Line line;	
	
	/**
	 * Create the panel.
	 */
	public DrawPanel() {
		point = new Point(-10, -10);
		line = new Line(point, point);
		
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				switch(geometryType) {
				case 1:
					break;
				case 2:
					line.b.x = e.getX();
					line.b.y = e.getY();
					repaint();
					break;
				case 3:
				case 4:
				case 5:
				}
			}
			@Override
			public void mouseMoved(MouseEvent e) {
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {			
				switch(geometryType) {
				case 1:
					point = new Point(e.getX(), e.getY());
					repaint();
					break;
				case 2:
					Point a = new Point(e.getX(), e.getY());
					Point b = new Point(e.getX(), e.getY());
					line = new Line(a, b);
					repaint();
					break;
				case 3:
				case 4:
				case 5:
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				switch(geometryType) {
				case 1:
					break;
				case 2:
					Point a = new Point(e.getX(), e.getY());
					Point b = new Point(e.getX(), e.getY());
					//line = new Line(a, b);
					line.a = a;
					line.b = b;
					repaint();
					break;
				case 3:
				case 4:
				case 5:
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				switch(geometryType) {
				case 1:
					break;
				case 2:
					line.b.x = e.getX();
					line.b.y = e.getY();
					repaint();
					break;
				case 3:
				case 4:
				case 5:
				}
			}
		});
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.drawOval((int)point.x, (int)point.y, 2, 2);
		g.drawLine((int)line.a.x, (int)line.a.y, (int)line.b.x, (int)line.b.y);
	}

	public void desenharTipo(int tipo) {
		this.geometryType = tipo;
	}

}
