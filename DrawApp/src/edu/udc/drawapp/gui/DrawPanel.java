package edu.udc.drawapp.gui;

import javax.swing.JPanel;

import edu.udc.drawapp.DrawApp;
import edu.udc.drawapp.model.*;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ListIterator;

public class DrawPanel extends JPanel implements DrawView {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Create the panel.
	 */
	public DrawPanel() {
		
		
		
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				Shape shape = DrawApp.getApp().getDocument().getDrwingShape();
				if(shape != null) {
					shape.getHandler().mouseMove(e.getX(), e.getY());
					repaint();
				}
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				Shape shape = DrawApp.getApp().getDocument().getDrwingShape();
				if(shape != null) {
					if(shape.getHandler().mouseClick(e.getX(), e.getY())) {
						DrawApp.getApp().getDocument().endDrawing();
					}
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
		
		ListIterator<Shape> it = DrawApp.getApp().getDocument().getShapeIterator();
		while(it.hasNext()) {
			it.next().getHandler().paint(g);
		}
		Shape shape = DrawApp.getApp().getDocument().getDrwingShape();
		if(shape != null) {
			shape.getHandler().paint(g);
		}
	}

	@Override
	public void update() {
		repaint();
	}

}
