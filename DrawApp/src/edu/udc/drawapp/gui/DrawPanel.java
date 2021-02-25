package edu.udc.drawapp.gui;

import javax.swing.JPanel;

import edu.udc.drawapp.model.*;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class DrawPanel extends JPanel {

	private Shape shape;
	
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
					shape.getHandler().mouseClick(e.getX(), e.getY());
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
		
		if(shape != null) {
			shape.getHandler().paint(g);
		}
	}

	public void desenharTipo(Shape shape) {
		this.shape = shape;
	}

}
