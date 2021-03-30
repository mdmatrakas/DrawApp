package edu.udc.drawapp.controler.handler;

import java.awt.Graphics;

import edu.udc.drawapp.model.Point;

public class PointHandler implements ShapeHandler {

	private Point point;

	public PointHandler(Point point) {
		this.point = point;
	}

	@Override
	public void mouseMove(int x, int y) {
	}

	@Override
	public boolean mouseClick(int x, int y) {
		point.x = x;
		point.y = y;
		return true;
	}

	@Override
	public void paint(Graphics g) {
		// Draw the point
		g.drawOval((int)point.x, (int)point.y, 2, 2);
	}

}
