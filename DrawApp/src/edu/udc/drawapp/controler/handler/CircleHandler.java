package edu.udc.drawapp.controler.handler;

import java.awt.Graphics;

import edu.udc.drawapp.model.Circle;

public class CircleHandler implements ShapeHandler {
	
	private Circle circle;
	/*
	 * Drawing state
	 * 0 - not drawing
	 * 1 - center defined
	 */
	private static int state;

	public CircleHandler(Circle circle) {
		this.circle = circle;
	}

	@Override
	public void mouseMove(int x, int y) {
		if(state == 1) {
			circle.radius = circle.center.distance(x, y);
		}
	}

	@Override
	public boolean mouseClick(int x, int y) {
		switch(state) {
		case 0:
			circle.center.x = x;
			circle.center.y = y;
			circle.radius = 0F;
			state = 1;
			return false;
		case 1:
			circle.radius = circle.center.distance(x, y);
			state = 0;
			return true;
		}
		return false;
	}

	@Override
	public void paint(Graphics g) {
		g.drawOval((int)(circle.center.x - circle.radius), 
				   (int)(circle.center.y - circle.radius), 
				   (int)(circle.radius*2), 
				   (int)(circle.radius*2));
	}

}
